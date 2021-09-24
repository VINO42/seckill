package io.github.vino42.seckill.service.impl;

import io.github.vino42.seckill.domain.entity.UserInfoEntity;
import io.github.vino42.seckill.domain.mapper.UserInfoMapper;
import io.github.vino42.seckill.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * =====================================================================================
 *
 * @Created :   2021/09/24 22:27:20
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Copyright : vino
 * @Decription : 用户表 服务实现类
 * =====================================================================================
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfoEntity> implements IUserInfoService {

}
