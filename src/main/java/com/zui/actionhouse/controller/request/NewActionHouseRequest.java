package com.zui.actionhouse.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@ApiModel(value = "Action House Request")
public class NewActionHouseRequest {
    @NotNull(message = "name can not be empty")
    @ApiModelProperty(value = "name", required = true)
    private String name;
}
