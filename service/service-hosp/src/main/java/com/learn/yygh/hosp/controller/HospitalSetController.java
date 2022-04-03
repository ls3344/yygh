package com.learn.yygh.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.yygh.common.exception.YyghException;
import com.learn.yygh.common.result.Result;
import com.learn.yygh.common.util.MD5;
import com.learn.yygh.hosp.service.HospitalSetService;
import com.learn.yygh.model.hosp.HospitalSet;
import com.learn.yygh.vo.hosp.HospitalSetQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @author longnj
 * @date 2022/3/29
 * @description
 **/
@Api(tags = "医院设置")
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;

    @ApiOperation("查询所有医院设置")
    @GetMapping("findAll")
    public Result<List<HospitalSet>> findAllHospitalSet() {
        return Result.ok(hospitalSetService.list());
    }

    @ApiOperation("删除医院设置")
    @DeleteMapping("{id}")
    public Result<Boolean> deleteHospitalSet(@PathVariable Long id) {
        boolean flag = hospitalSetService.removeById(id);
        return flag ? Result.ok() : Result.fail();
    }

    @ApiOperation("分页查询医院列表")
    @PostMapping("findPage/{pageNo}/{pageSize}")
    public Result<Page<HospitalSet>> findPage(@PathVariable long pageNo, @PathVariable long pageSize, @RequestBody HospitalSetQueryVo queryVo) {
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(queryVo.getHosname())) {
            queryWrapper.like("hosname", queryVo.getHosname());
        }
        if (!StringUtils.isEmpty(queryVo.getHoscode())) {
            queryWrapper.eq("hoscode", queryVo.getHoscode());
        }
        Page<HospitalSet> page = new Page<>(pageNo, pageSize);
        Page<HospitalSet> result = hospitalSetService.page(page, queryWrapper);
        return Result.ok(result);
    }

    @ApiOperation("根据id查询医院设置")
    @GetMapping("getHospSet/{id}")
    public Result<HospitalSet> getHospSet(@PathVariable long id) {
        try {
            int a=1/0;
        }catch (Exception e){
            throw new YyghException("失败了吧",200);
        }

        return Result.ok(hospitalSetService.getById(id));
    }

    @ApiOperation("创建医院设置")
    @PostMapping("createHospSet")
    public Result<Boolean> createHospSet(@RequestBody HospitalSet hospitalSet) {
        //设置状态1可以使用0不能使用
        hospitalSet.setStatus(1);
        //设置签名秘钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));
        return hospitalSetService.save(hospitalSet) ? Result.ok() : Result.fail();
    }

    @ApiOperation("修改医院设置")
    @PostMapping("updateHospSet")
    public Result<Boolean> updateHospSet(@RequestBody HospitalSet hospitalSet) {
        return hospitalSetService.updateById(hospitalSet) ? Result.ok() : Result.fail();
    }

    @ApiOperation("批量删除设置")
    @PostMapping("deleteBatchHospSet")
    public Result<Boolean> deleteBatchSet(@RequestBody List<Long> idList) {
        return hospitalSetService.removeByIds(idList) ? Result.ok() : Result.fail();
    }

    @ApiOperation("医院设置解锁和锁定")
    @PutMapping("lock/{id}/{status}")
    public Result<Boolean> lockOrUnlock(@PathVariable Long id, @PathVariable Integer status) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        hospitalSet.setStatus(status);
        return hospitalSetService.updateById(hospitalSet) ? Result.ok() : Result.fail();
    }

    @ApiOperation("发送签名秘钥")
    @PutMapping("sendKey/{id}")
    public Result<Boolean> sendKey(@PathVariable Long id) {
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        String key = hospitalSet.getSignKey();
        String code = hospitalSet.getHoscode();
        //TODO
        return Result.ok();
    }
}
