package com.learn.yygh.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.yygh.model.cmn.Dict;

import java.util.List;

/**
 * @author longnj
 * @date 2022/3/29
 * @description
 **/
public interface DictService extends IService<Dict> {

    List<Dict> findChildData(Long id);

}
