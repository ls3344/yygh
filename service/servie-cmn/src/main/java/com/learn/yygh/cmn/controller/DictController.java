package com.learn.yygh.cmn.controller;

import com.learn.yygh.cmn.service.DictService;
import com.learn.yygh.common.result.Result;
import com.learn.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longnj
 * @date 2022/4/3
 * @description
 **/
@Api(tags = "数据字典")
@RestController
@RequestMapping("/admin/cmn/dict")
public class DictController {


    @Autowired
    private DictService dictService;

    @ApiOperation("根据数据id查询子集")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id){
        List<Dict> dictList=dictService.findChildData(id);
        return Result.ok(dictList);
    }




}
