package io.github.vino42.seckill.web.controller;

import cn.hutool.core.date.LocalDateTimeUtil;
import io.github.vino42.seckill.domain.entity.ActivityInfoEntity;
import io.github.vino42.seckill.domain.entity.UserInfoEntity;
import io.github.vino42.seckill.service.IActivityInfoService;
import io.github.vino42.seckill.service.SecKillService;
import io.github.vino42.seckill.support.ServiceResultWrapper;
import io.github.vino42.seckill.support.WrapMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static io.github.vino42.seckill.support.ServiceResponseCodeEnum.*;

/**
 * =====================================================================================
 *
 * @Created :   2021/9/24 22:22
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Copyright : VINO
 * @Decription : 秒杀控制器
 * =====================================================================================
 */
@RestController
@RequestMapping("/seckill")
public class SecKillController {
    @Autowired
    SecKillService secKillService;
    @Autowired
    IActivityInfoService activityInfoService;


    /**
     * 添加活动商品到redis TODO:完善库表
     *
     * @param activityId       活动id
     * @param skuId            商品id
     * @param perSkuLimitCount 当前商品针对单个用户的购买数量限制
     * @return
     */
    @GetMapping("/skuadd")
    @ResponseBody
    public Object skuAdd(@RequestParam(value = "activityId", defaultValue = "") String activityId,
                         @RequestParam(value = "skuId", defaultValue = "") String skuId,
                         @RequestParam(value = "skuLimit", defaultValue = "0") int perSkuLimitCount) {
        if (StringUtils.isBlank(activityId)) {
            return WrapMapper.illegalArgument("活动id不能为空");
        }
        if (StringUtils.isBlank(skuId)) {
            return WrapMapper.illegalArgument("商品id不能为空");
        }
        if (perSkuLimitCount <= 0) {
            return WrapMapper.illegalArgument("sku库存必須大于0");
        }
        return WrapMapper.ok(secKillService.addSkuToRedis(activityId, skuId, perSkuLimitCount));
    }

    /**
     * @param path       秒杀路径
     * @param user       用户实体封装
     * @param goodsId    商品id
     * @param buyNum     购买数量
     * @param perSkuLim  每个用户购买当前sku的数量限制
     * @param perActLim  每个用户购买当前活动内所有sku的数量限制
     * @param activityId 活动id
     * @return
     */
    @RequestMapping(value = "/{path}/doSeckill", method = RequestMethod.POST)
    public ServiceResultWrapper doSecKill(@PathVariable String path,
                                          @RequestBody UserInfoEntity user,
                                          @RequestParam("goodsId") Long goodsId,
                                          @RequestParam("buyNum") int buyNum,
                                          @RequestParam("perSkuLim") int perSkuLim,
                                          @RequestParam("perActLim") int perActLim,
                                          @RequestParam("activityId") Long activityId) {
        //判断是否登录
        if (null == user) {
            return WrapMapper.illegalArgument();
        }

        if (!secKillService.checkPath(path, user, goodsId)) {
            return WrapMapper.error(ILLEGAL_ACCESS);
        }

        return secKillService.secKill(user, goodsId, activityId, buyNum, perSkuLim, perActLim);
    }

    /**
     * 秒杀前先获取路径
     *
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/genPath", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResultWrapper<String> generatePath(@RequestBody UserInfoEntity user,
                                                     @RequestParam("goodsId") Long goodsId,
                                                     @RequestParam("activityId") Long activityId,
                                                     @RequestParam(value = "capcha", defaultValue = "42", required = false) String capcha,
                                                     @RequestParam("timstamp") long timstamp) {
        //判断是否登录
        if (user == null) {
            return WrapMapper.error(AUTH_401_TOKEN_INVALID);
        }
        ActivityInfoEntity activity = activityInfoService.getActivitytByGoodIdAndActivityId(goodsId, activityId);
        if ((LocalDateTimeUtil.now().isBefore(activity.getStartTime()))
        ) {
            return WrapMapper.error(ACTIVITY_NOT_BEGIN);
        }
        if ((LocalDateTimeUtil.now().isAfter(activity.getEndTime()))) {

            return WrapMapper.error(ACTIVITY_ALREADY_END);
        }
        if (!secKillService.checkCapCha(user, capcha, timstamp)) {
            return WrapMapper.error(ILLEGAL_CAPCHA);
        }

        String path = secKillService.generatePath(user, goodsId);

        return WrapMapper.ok(path);
    }

}
