package io.github.vino42.seckill.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import io.github.vino42.seckill.domain.entity.UserInfoEntity;
import io.github.vino42.seckill.service.IActivityInfoService;
import io.github.vino42.seckill.service.RedisLuaService;
import io.github.vino42.seckill.service.SecKillService;
import io.github.vino42.seckill.support.ServiceResultWrapper;
import io.github.vino42.seckill.support.WrapMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static cn.hutool.core.text.CharPool.UNDERLINE;
import static io.github.vino42.seckill.support.Constant.*;

/**
 * =====================================================================================
 *
 * @Created :   2021/9/24 22:37
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email :
 * @Copyright : VINO
 * @Decription :
 * =====================================================================================
 */
@Service
public class SecKillServiceImpl implements SecKillService {

    @Autowired
    RedisLuaService redisLuaService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    IActivityInfoService activityInfoService;

    @Override
    public boolean checkPath(String path, UserInfoEntity user, Long goodsId) {
        String redisPathKey = String.format(SECKILL_PATH_KEY, user.getId() + goodsId);
        String o = stringRedisTemplate.opsForValue().get(redisPathKey);
        if (!path.equals(o.toString())) {
            return false;
        }
        return true;
    }

    @Override
    public String generatePath(UserInfoEntity user, Long goodsId) {
        String pathMd5Key = String.format("%s_%s", goodsId, UUID.randomUUID());
        String pathValue = SecureUtil.md5(pathMd5Key);
        String redisPathKey = String.format(SECKILL_PATH_KEY, user.getId() + goodsId);
        stringRedisTemplate.opsForValue().set(redisPathKey, pathValue);
        return pathValue;
    }

    @Override
    public boolean checkCapCha(UserInfoEntity user, String capcha, long timstamp) {
        if (StringUtils.isNotBlank(capcha) && !DEFAULT_CAPCHA_FLAG.equals(capcha)) {

            String capchaKey = String.format(REDIS_CAPCHA_KEY, user.getId() + UNDERLINE + user.getPhone() + UNDERLINE + timstamp);
            String o = stringRedisTemplate.opsForValue().get(capchaKey);
            if (o == null) {
                return false;
            }
            if (!stringRedisTemplate.hasKey(capchaKey)) {
                return false;
            }
            if (!o.equals(capcha)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ServiceResultWrapper secKill(UserInfoEntity user, Long goodsId, Long activityId, int buyNum, int perSkuLim, int perActLim) {

        /**
         * 1.检查库存
         * 2.库存没有返回失败错误码
         * 3.库存有进行减库存
         * 4.生成订单跳转支付
         * 5.支付超时 库存回滚+1 秒杀状态置为失败
         * 6.支付成功 进行后续流程
         */

        //减库存
        int START = 100000;
        int END = 900000;
        int rand_num = ThreadLocalRandom.current().nextInt(END - START + 1) + START;
        String order_time = System.currentTimeMillis() + StrUtil.DASHED + rand_num;

        List<String> keyList = new ArrayList();
        keyList.add(String.valueOf(user.getId()));
        keyList.add(String.valueOf(buyNum));
        keyList.add(String.valueOf(goodsId));
        keyList.add(String.valueOf(perSkuLim));
        keyList.add(String.valueOf(activityId));
        keyList.add(String.valueOf(perActLim));
        keyList.add(order_time);

        String result = redisLuaService.runLuaScript(LUA_SCRIPT_FILE_NAME, keyList);
        LUA_EXCUTE_RESULT resultEnum = LUA_EXCUTE_RESULT.getByCode(result);
        if (resultEnum != null) {
            //TODO:异步生成订单进行后续流程
            return WrapMapper.ok();
        }
        return WrapMapper.error(resultEnum.codeEnum);
    }

    @Override
    public Boolean addSkuToRedis(String activityId, String skuId, int amount) {
        String nameAmount = "sec_" + activityId + "_sku_amount_hash";
        redisTemplate.opsForHash().put(nameAmount, skuId, amount);
        return true;
    }


}
