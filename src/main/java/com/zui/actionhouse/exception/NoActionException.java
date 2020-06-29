package com.zui.actionhouse.exception;

import com.zui.actionhouse.config.ResultCodeEnum;
import lombok.Data;

@Data
public class NoActionException extends RuntimeException {
    private Integer code;

    public NoActionException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public NoActionException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "CMSException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }
}
