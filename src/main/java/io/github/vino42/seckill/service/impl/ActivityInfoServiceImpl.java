package io.github.vino42.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.vino42.seckill.domain.entity.ActivityInfoEntity;
import io.github.vino42.seckill.domain.mapper.ActivityInfoMapper;
import io.github.vino42.seckill.service.IActivityInfoService;
import org.springframework.stereotype.Service;

/**
 * =====================================================================================
 *
 * @Created :   2021/09/24 21:34:01
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Copyright :
 * @Decription : 秒杀活动信息表 服务实现类
 * =====================================================================================
 */
@Service
public class ActivityInfoServiceImpl extends ServiceImpl<ActivityInfoMapper, ActivityInfoEntity> implements IActivityInfoService {
    /**
     * TODO:加缓存
     *
     * @param goodsId
     * @return
     */
    @Override
    public ActivityInfoEntity getActivitytByGoodIdAndActivityId(Long goodsId, Long activityId) {
        return this.baseMapper.getActivitytByGoodIdAndActivityId(goodsId, activityId);
    }
}
