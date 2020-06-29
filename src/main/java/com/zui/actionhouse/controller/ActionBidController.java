package com.zui.actionhouse.controller;


import com.zui.actionhouse.config.R;
import com.zui.actionhouse.controller.request.NewActionBidRequest;
import com.zui.actionhouse.controller.request.NewActionRequest;
import com.zui.actionhouse.model.Action;
import com.zui.actionhouse.model.ActionBid;
import com.zui.actionhouse.model.ActionState;
import com.zui.actionhouse.service.IActionBidService;
import com.zui.actionhouse.service.IActionHouseService;
import com.zui.actionhouse.service.IActionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actionbid")
@Slf4j
@Api(value = "action bid API(v1)", tags = "action bid")
public class ActionBidController {
    @Autowired
    private IActionBidService actionBidService;

    @ApiOperation(value = "create action bid by json")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public R create(@RequestBody NewActionBidRequest newActionBid) {
        log.info("Receive new action bid {}", newActionBid);
        ActionBid actionBid = actionBidService.saveActionBid(newActionBid);
        return R.ok().data("actionbid", actionBid).message("actionBid created");
    }


    @ApiOperation(value = "get action bid by bidder name")
    @GetMapping("/{bidder}")
    public R getActionBidByName(@PathVariable("bidder") String bidder) {
        List<ActionBid> actionBids = actionBidService.getAllActionBidsByBidder(bidder);
        log.info("Get action bids: {}", actionBids);
        return  R.ok().data("actionbids", actionBids).message("action bids");
    }


}
