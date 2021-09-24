package io.github.vino42.seckill.service;

import io.github.vino42.seckill.domain.entity.ActivityInfoEntity;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * =====================================================================================
 *
 * @Created :   2021/09/24 21:34:01
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Copyright :
 * @Decription : 秒杀活动信息表 服务类
 * =====================================================================================
 */
public interface IActivityInfoService extends IService<ActivityInfoEntity> {


    ActivityInfoEntity getActivitytByGoodIdAndActivityId(Long goodsId, Long activityId);
}
