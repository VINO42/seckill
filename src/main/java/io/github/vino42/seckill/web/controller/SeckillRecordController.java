package io.github.vino42.seckill.web.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.vino42.seckill.domain.entity.SeckillRecordEntity;
import io.github.vino42.seckill.service.ISeckillRecordService;
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
 * @Created :   2021/09/24 21:32:18
 * @Compiler :  jdk 11
 * @Author :    VINO
 * @Copyright :
 * @Decription :  控制器
 * =====================================================================================
 */
@RestController
@RequestMapping("/seckillRecord")
public class SeckillRecordController {

    @Autowired
    private ISeckillRecordService seckillRecordService;


    @GetMapping(value = "/page")
    public ServiceResultWrapper<IPage> getSeckillRecordEntityPage(Page<SeckillRecordEntity> page, SeckillRecordEntity seckillRecord) {
        return WrapMapper.ok(seckillRecordService.page(page, Wrappers.query(seckillRecord)));
    }


    @PostMapping(value = "/add")
    public ServiceResultWrapper create(@Validated @RequestBody SeckillRecordEntity seckillRecord, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return WrapMapper.error(JSONUtil.toJsonStr(fieldErrors));
        }
        return WrapMapper.ok(seckillRecordService.save(seckillRecord));
    }


    @PutMapping(value = "/update")
    public ServiceResultWrapper update(@Validated @RequestBody SeckillRecordEntity seckillRecord, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return WrapMapper.error(JSONUtil.toJsonStr(fieldErrors));
        }
        return WrapMapper.ok(seckillRecordService.updateById(seckillRecord));
    }


    @DeleteMapping(value = "/delete/{id}")
    public ServiceResultWrapper delete(@PathVariable Long id) {
        return WrapMapper.ok(seckillRecordService.removeById(id));
    }
}
