package com.learn.yygh.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longnj
 * @date 2022/4/6
 * @description
 **/
public class CommonUtil {

    public static <T> T copyBean(Object source, Class<T> target) {
        if (target == null || source == null) {
            return null;
        }
        T t;
        try {
            t = target.newInstance();
            BeanUtils.copyProperties(source, t);
        } catch (Exception e) {
            t = null;
        }
        return t;
    }

    public static <T> List<T> copyBeanList(List<Object> sourceList, Class<T> target) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        List<T> tList;
        try {
            tList = new ArrayList<>();
            for (Object object : sourceList) {
                T t = target.newInstance();
                BeanUtils.copyProperties(object, t);
                tList.add(t);
            }
        } catch (Exception e) {
            return null;
        }
        return tList;
    }

}
