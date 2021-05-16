package com.orange.registration.common.exception;

import com.orange.registration.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Orange
 * @create 2021-05-16 22:17
 *
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail();
    }

    /**
     * 自定义异常处理方法
     * @param e
     * @return
     */
    @ExceptionHandler(RegistrationException.class)
    @ResponseBody
    public Result error(RegistrationException e){
        return Result.fail();
    }
}
