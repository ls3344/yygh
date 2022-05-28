package com.learn.yygh.cmn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.yygh.cmn.listener.DictListener;
import com.learn.yygh.cmn.mapper.DictMapper;
import com.learn.yygh.cmn.service.DictService;
import com.learn.yygh.common.util.CommonUtil;
import com.learn.yygh.model.cmn.Dict;
import com.learn.yygh.vo.cmn.DictEeVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author longnj
 * @date 2022/3/29
 * @description
 **/
@Service
            public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public List<Dict> findChildData(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        List<Dict> dictList = baseMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(dictList)) {
            return new ArrayList<>();
        }
        List<Long> dictIdList = dictList.stream().map(Dict::getId).distinct().collect(Collectors.toList());
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.in("parent_id", dictIdList);
        List<Dict> childList = baseMapper.selectList(wrapper);
        Map<Long, List<Dict>> dictMap = childList.stream().collect(Collectors.groupingBy(Dict::getParentId));
        dictList.forEach(dict -> dict.setHasChildren(dictMap.containsKey(dict.getId())));
        return dictList;
    }

    @Override
    public void export(HttpServletResponse response) throws Exception {
        //设置下载信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "dict";
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        //查询数据库
        List<Dict> dictList = baseMapper.selectList(new QueryWrapper<>());
        //组装
        List<DictEeVo> exVOList = new ArrayList<>();
        dictList.forEach(dict -> exVOList.add(CommonUtil.copyBean(dict, DictEeVo.class)));
        EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("dict").doWrite(exVOList);
    }

    @Override
    public void importDictData(MultipartFile file) throws Exception {
        EasyExcel.read(file.getInputStream(),DictEeVo.class,new DictListener(baseMapper)).sheet().doRead();
    }

}
