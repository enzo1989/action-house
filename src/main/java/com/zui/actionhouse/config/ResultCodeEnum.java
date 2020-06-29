package com.zui.actionhouse.config;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(true,20000,"success"),
    UNKNOWN_ERROR(false,20001,"unknown error"),
    PARAM_ERROR(false,20002,"param error"),
    NULL_POINT(false,20003,"null point"),
    HTTP_CLIENT_ERROR(false,20004,"http client error"),
    No_ACTION_HOUSE_ERROR(false,20010,"this action house not exist"),
    No_ACTION_ERROR(false,20011,"this action not exist"),
    ACTION_TERMINATED_ERROR(false,20012,"this action is terminated"),
    ACTION_NOT_TERMINATED_ERROR(false,20013,"this action is not terminated"),
    PRICE_LOW_ERROR(false,20014,"bid price is lower then current price"),
    ;

    // response success or not
    private Boolean success;
    // response state
    private Integer code;
    // response message
    private String message;

    ResultCodeEnum(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

}
