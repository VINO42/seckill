package io.github.vino42.seckill.service;

import io.github.vino42.seckill.domain.entity.UserInfoEntity;
import io.github.vino42.seckill.support.ServiceResultWrapper;

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
public interface SecKillService {
    boolean checkPath(String path, UserInfoEntity user, Long goodsId);

    String generatePath(UserInfoEntity user, Long goodsId);

    boolean checkCapCha(UserInfoEntity user, String capcha, long timstamp);

    ServiceResultWrapper secKill(UserInfoEntity user, Long goodsId, Long activityId, int buyNum, int perSkuLim, int perActLim);


    Boolean addSkuToRedis(String activityId, String skuId, int amount);
}
