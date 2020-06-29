package com.zui.actionhouse.exception;

import com.zui.actionhouse.config.ResultCodeEnum;
import lombok.Data;


@Data
public class ActionNotTerminatedException extends RuntimeException {
    private Integer code;

    public ActionNotTerminatedException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ActionNotTerminatedException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "ActionNotTerminatedException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }
}
