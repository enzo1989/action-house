package com.zui.actionhouse.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Builder
@Getter
@Setter
@ToString
@ApiModel(value = "Action Request")
public class NewActionRequest {
    @NotNull(message = "name can not be empty")
    @ApiModelProperty(value = "name", required = true)
    private String name;

    @ApiModelProperty(value = "description")
    private String description;

    @ApiModelProperty(value = "startingTime")
    private Date startingTime;

    @ApiModelProperty(value = "endTime")
    private Date endTime;

    @ApiModelProperty(value = "startPrice")
    private Money startPrice;

    @ApiModelProperty(value = "currentPrice")
    private Money currentPrice;

    @ApiModelProperty(value = "actionHouseId")
    private Long actionHouseId;
}
