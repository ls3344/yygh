package com.learn.yygh.hosp.controller;

import com.learn.yygh.hosp.service.HospitalSetService;
import com.learn.yygh.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author longnj
 * @date 2022/3/29
 * @description
 **/
@RestController
@RequestMapping("/admin/hosp/hospitalSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;


    @GetMapping("findAll")
    public List<HospitalSet> findAllHospitalSet(){
        return hospitalSetService.list();
    }


}
