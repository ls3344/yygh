package com.learn.yygh.common.exception;

import com.learn.yygh.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author longnj
 * @date 2022/4/3
 * @description
 **/

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }


    @ExceptionHandler(YyghException.class)
    @ResponseBody
    public Result customError(YyghException e){
        e.printStackTrace();
        return Result.fail();
    }

}
