package io.github.vino42.seckill.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.vino42.seckill.domain.entity.ActivityInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * =====================================================================================
 *
 * @Created :   2021/09/24 21:34:01
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email :
 * @Copyright :
 * @Decription : 秒杀活动信息表 Mapper 接口
 * =====================================================================================
 */
@Mapper
public interface ActivityInfoMapper extends BaseMapper<ActivityInfoEntity> {


    ActivityInfoEntity getActivitytByGoodIdAndActivityId(@Param("goodsId") Long goodsId, @Param("activityId") Long activityId);
}
