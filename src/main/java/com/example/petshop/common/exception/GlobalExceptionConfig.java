package com.example.petshop.common.exception;

import com.example.petshop.common.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionConfig {

    private Result result=new Result();
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result error(HttpServletRequest req, HttpServletResponse resp, Exception e) {
        result.fail(e.toString());
        return result;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result error(HttpServletRequest req, HttpServletResponse resp, MyException e) {
        MyException ce = (MyException)e;
        return ce.getResult();
    }
}

