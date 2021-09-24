package io.github.vino42.seckill.domain.mapper;

import io.github.vino42.seckill.domain.entity.UserInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * =====================================================================================
 *
 * @Created :   2021/09/24 22:27:20
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Email :
 * @Copyright : vino
 * @Decription : 用户表 Mapper 接口
 * =====================================================================================
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoEntity> {

}
