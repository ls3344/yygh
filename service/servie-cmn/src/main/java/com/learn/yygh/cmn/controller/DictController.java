package com.learn.yygh.cmn.controller;

import com.learn.yygh.cmn.service.DictService;
import com.learn.yygh.common.result.Result;
import com.learn.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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


    @ApiOperation("导入数据字典")
    @PostMapping("importDictData")
    public Result<Boolean> importDictData(MultipartFile file) throws Exception {
        dictService.importDictData(file);
        return Result.ok();
    }


        /**
         * 这个接口前端请求的时候请求头里面要加Content-Type:application/json,否则会打不开
         * @param response
         * @return
         * @throws Exception
         */
    @ApiOperation("导出数据字典")
    @GetMapping("exportDictData")
    public void exportDictData(HttpServletResponse response) throws Exception {
        dictService.export(response);
    }

    @ApiOperation("根据数据id查询子集")
    @GetMapping("findChildData/{id}")
    public Result<List<Dict>> findChildData(@PathVariable Long id) {
        List<Dict> dictList = dictService.findChildData(id);
        return Result.ok(dictList);
    }

}
