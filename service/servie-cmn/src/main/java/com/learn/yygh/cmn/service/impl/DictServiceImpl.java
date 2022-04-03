package com.learn.yygh.cmn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.yygh.cmn.mapper.DictMapper;
import com.learn.yygh.cmn.service.DictService;
import com.learn.yygh.model.cmn.Dict;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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

}
