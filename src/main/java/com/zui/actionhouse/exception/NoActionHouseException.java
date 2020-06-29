package com.zui.actionhouse.exception;

import com.zui.actionhouse.config.ResultCodeEnum;
import lombok.Data;

@Data
public class NoActionHouseException extends RuntimeException {
    private Integer code;

    public NoActionHouseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public NoActionHouseException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "NoActionHouseException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }
}
