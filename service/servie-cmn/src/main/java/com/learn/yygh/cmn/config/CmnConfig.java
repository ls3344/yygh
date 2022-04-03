package com.learn.yygh.cmn.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author longnj
 * @date 2022/3/29
 * @description
 **/
@Configuration
@MapperScan("com.learn.yygh.cmn.mapper")
public class CmnConfig {
}
