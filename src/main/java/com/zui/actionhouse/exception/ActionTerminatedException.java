package com.zui.actionhouse.exception;

import com.zui.actionhouse.config.ResultCodeEnum;
import lombok.Data;


@Data
public class ActionTerminatedException extends RuntimeException {
    private Integer code;

    public ActionTerminatedException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ActionTerminatedException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "ActionTerminatedException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }
}
