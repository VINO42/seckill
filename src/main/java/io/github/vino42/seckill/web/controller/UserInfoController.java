package io.github.vino42.seckill.web.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.vino42.seckill.domain.entity.UserInfoEntity;
import io.github.vino42.seckill.service.IUserInfoService;
import io.github.vino42.seckill.support.ServiceResultWrapper;
import io.github.vino42.seckill.support.WrapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * =====================================================================================
 *
 * @Created :   2021/09/24 22:27:20
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Copyright : vino
 * @Decription : 用户表 控制器
 * =====================================================================================
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;


    @GetMapping(value = "/page")
    public ServiceResultWrapper<IPage> getUserInfoEntityPage(Page<UserInfoEntity> page, UserInfoEntity userInfo) {
        return WrapMapper.ok(userInfoService.page(page, Wrappers.query(userInfo)));
    }


    @PostMapping(value = "/add")
    public ServiceResultWrapper create(@Validated @RequestBody UserInfoEntity userInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return WrapMapper.error(JSONUtil.toJsonStr(fieldErrors));
        }
        return WrapMapper.ok(userInfoService.save(userInfo));
    }


    @PutMapping(value = "/update")
    public ServiceResultWrapper update(@Validated @RequestBody UserInfoEntity userInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return WrapMapper.error(JSONUtil.toJsonStr(fieldErrors));
        }
        return WrapMapper.ok(userInfoService.updateById(userInfo));
    }


    @DeleteMapping(value = "/delete/{id}")
    public ServiceResultWrapper delete(@PathVariable Long id) {
        return WrapMapper.ok(userInfoService.removeById(id));
    }
}
