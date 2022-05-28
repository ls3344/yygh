package com.learn.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;

import java.util.Map;

/**
 * @author longnj
 * @date 2022/4/4
 * @description
 **/
public class ExcelListener extends AnalysisEventListener<UserData> {


    @Override
    public void invoke(UserData userData, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
