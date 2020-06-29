package com.zui.actionhouse.exception;

import com.zui.actionhouse.config.ResultCodeEnum;
import lombok.Data;


@Data
public class PriceLowException extends RuntimeException {
    private Integer code;

    public PriceLowException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public PriceLowException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "PriceLowException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }
}
