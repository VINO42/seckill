package io.github.vino42.seckill.web.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.vino42.seckill.domain.entity.ActivityGoodEntity;
import io.github.vino42.seckill.service.IActivityGoodService;
import io.github.vino42.seckill.support.ServiceResultWrapper;
import io.github.vino42.seckill.support.WrapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * =====================================================================================
 *
 * @Created :   2021/09/24 21:34:01
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Copyright :
 * @Decription : 秒杀商品表 控制器
 * =====================================================================================
 */
@RestController
@RequestMapping("/activityGoodEntity")
public class ActivityGoodController {

    @Autowired
    private IActivityGoodService activityGoodService;


    @GetMapping(value = "/page")
    public ServiceResultWrapper<IPage> getActivityGoodEntityPage(Page<ActivityGoodEntity> page, ActivityGoodEntity activityGood) {
        return WrapMapper.ok(activityGoodService.page(page, Wrappers.query(activityGood)));
    }


    @PostMapping(value = "/add")
    public ServiceResultWrapper create(@Validated @RequestBody ActivityGoodEntity activityGood, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return WrapMapper.error(JSONUtil.toJsonStr(fieldErrors));
        }
        return WrapMapper.ok(activityGoodService.save(activityGood));
    }


    @PutMapping(value = "/update")
    public ServiceResultWrapper update(@Validated @RequestBody ActivityGoodEntity activityGood, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return WrapMapper.error(JSONUtil.toJsonStr(fieldErrors));
        }
        return WrapMapper.ok(activityGoodService.updateById(activityGood));
    }


    @DeleteMapping(value = "/delete/{id}")
    public ServiceResultWrapper delete(@PathVariable Long id) {
        return WrapMapper.ok(activityGoodService.removeById(id));
    }
}
