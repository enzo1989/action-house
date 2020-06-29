package com.zui.actionhouse.controller;


import com.zui.actionhouse.config.R;
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
@RequestMapping("/action")
@Slf4j
@Api(value = "action API(v1)", tags = "action api")
public class ActionController {
    @Autowired
    private IActionService actionService;
    @Autowired
    private IActionBidService actionBidService;

    @ApiOperation(value = "create action by json")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public R create(@RequestBody NewActionRequest newAction) {
        log.info("Receive new action {}", newAction);
        Action action = actionService.saveAction(newAction);
        return R.ok().data("action", action).message("action created");
    }

    @ApiOperation(value = "get all actions by action state")
    @GetMapping(path = "/", params = "name")
    public R getActionsByState(@RequestParam ActionState actionState) {
        List<Action> actions = actionService.getAllActionByState(actionState);
        log.info("Get actions: {}", actions);
        return R.ok().data("actions",actions).message("actions");
    }

    @ApiOperation(value = "get all actions ")
    @GetMapping(path = "/", params = "!name")
    public R getAll() {
        List<Action> actions = actionService.getAllAction();
        return R.ok().data("actions",actions).message("actions");
    }

    @ApiOperation(value = "get all action bids by action id")
    @GetMapping("/{id}/bids")
    public R getBidsByActionId(@PathVariable("id") Long id) {
        List<ActionBid> actionBids = actionBidService.getAllActionBidsByActionId(id);
        log.info("Get actionBids: {}", actionBids);
        return R.ok().data("actionbids",actionBids).message("action bid by actionId");
    }

    @ApiOperation(value = "get action winner by action id")
    @GetMapping("/{id}/winner")
    public R getActionWinner(@PathVariable("id") Long id) {
        ActionBid actionBid = actionBidService.getWinner(id);
        log.info("Get actionBids: {}", actionBid);
        return  R.ok().data("actionbid", actionBid).message("action winner");
    }

    @ApiOperation(value = "delete action by action id")
    @DeleteMapping(path = "/{id}")
    public R deleteByName(@PathVariable Long id) {
        actionService.deleteActionByActionId(id);
        return R.ok().message("actionHouse deleted");
    }
}
