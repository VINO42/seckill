package io.github.vino42.seckill.web.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.vino42.seckill.domain.entity.ActivityInfoEntity;
import io.github.vino42.seckill.service.IActivityInfoService;
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
 * @Created :   2021/09/24 21:42:09
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Copyright :
 * @Decription : 秒杀活动信息表 控制器
 * =====================================================================================
 */
@RestController
@RequestMapping("/activityInfoEntity")
public class ActivityInfoController {

    @Autowired
    private IActivityInfoService activityInfoService;


    @GetMapping(value = "/page")
    public ServiceResultWrapper<IPage> getActivityInfoEntityPage(Page<ActivityInfoEntity> page, ActivityInfoEntity activityInfo) {
        return WrapMapper.ok(activityInfoService.page(page, Wrappers.query(activityInfo)));
    }


    @PostMapping(value = "/add")
    public ServiceResultWrapper create(@Validated @RequestBody ActivityInfoEntity activityInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return WrapMapper.error(JSONUtil.toJsonStr(fieldErrors));
        }
        return WrapMapper.ok(activityInfoService.save(activityInfo));
    }


    @PutMapping(value = "/update")
    public ServiceResultWrapper update(@Validated @RequestBody ActivityInfoEntity activityInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return WrapMapper.error(JSONUtil.toJsonStr(fieldErrors));
        }
        return WrapMapper.ok(activityInfoService.updateById(activityInfo));
    }


    @DeleteMapping(value = "/delete/{id}")
    public ServiceResultWrapper delete(@PathVariable Long id) {
        return WrapMapper.ok(activityInfoService.removeById(id));
    }
}
