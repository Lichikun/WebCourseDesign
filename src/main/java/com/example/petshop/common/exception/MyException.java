package com.example.petshop.common.exception;

import com.example.petshop.common.utils.Result;
import lombok.Data;

public class MyException extends Exception {
    private Result result = new Result();

    public MyException(Result result) {
        this.result = result;
    }

    public MyException(String s) {this.result.againLogin(s);}

    public MyException() {this.result.fail();}

    //打印错误信息
    public Result getResult() {
        System.out.println(result.getMessage());
        return result;
    }
}