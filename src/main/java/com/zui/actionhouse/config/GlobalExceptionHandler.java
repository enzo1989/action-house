package com.zui.actionhouse.config;

import com.zui.actionhouse.exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public R error(NullPointerException e) {
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.NULL_POINT);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public R error(IndexOutOfBoundsException e) {
        e.printStackTrace();
        return R.setResult(ResultCodeEnum.HTTP_CLIENT_ERROR);
    }

    @ExceptionHandler(ActionNotTerminatedException.class)
    @ResponseBody
    public R error(ActionNotTerminatedException e) {
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }

    @ExceptionHandler(ActionTerminatedException.class)
    @ResponseBody
    public R error(ActionTerminatedException e) {
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }

    @ExceptionHandler(NoActionException.class)
    @ResponseBody
    public R error(NoActionException e) {
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }

    @ExceptionHandler(NoActionHouseException.class)
    @ResponseBody
    public R error(NoActionHouseException e) {
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }

    @ExceptionHandler(PriceLowException.class)
    @ResponseBody
    public R error(PriceLowException e) {
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }
}

