package com.zui.actionhouse.controller.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
@ApiModel(value = "ActionBidRequest")
public class NewActionBidRequest {
    @NotNull(message = "bidder can not be empty")
    @ApiModelProperty(value = "bidder", required = true)
    private String bidder;

    @ApiModelProperty(value = "description")
    private String description;

    @ApiModelProperty(value = "price")
    private Money price;

    @ApiModelProperty(value = "actionId")
    private Long actionId;
}
