package com.learn.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author longnj
 * @date 2022/4/4
 * @description
 **/
@Data
public class UserData {

    @ExcelProperty(value = "用户id",index = 0)
    private Integer id;
    @ExcelProperty(value ="用户名",index = 0)
    private String userName;




}
