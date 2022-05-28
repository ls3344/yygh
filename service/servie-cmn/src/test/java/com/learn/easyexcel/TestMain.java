package com.learn.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.ExcelBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longnj
 * @date 2022/4/4
 * @description
 **/
public class TestMain {

    public static void main(String[] args) {
        String fileName="/Users/admin/Desktop/0.xlsx";

        List<UserData> userDataList=new ArrayList<>();
        for(int i=0;i<10;i++){
            UserData userData=new UserData();
            userData.setId(i);
            userData.setUserName("Lucy"+i);
            userDataList.add(userData);
        }
        EasyExcel.write(fileName,UserData.class).sheet("用户信息").doWrite(userDataList);
    }

}
