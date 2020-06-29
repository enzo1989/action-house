package com.zui.actionhouse.controller;


import com.zui.actionhouse.config.R;
import com.zui.actionhouse.controller.request.NewActionHouseRequest;
import com.zui.actionhouse.exception.NoActionHouseException;
import com.zui.actionhouse.model.Action;
import com.zui.actionhouse.model.ActionHouse;
import com.zui.actionhouse.service.IActionHouseService;
import com.zui.actionhouse.service.IActionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/actionhouse")
@Slf4j
@Api(value = "action house API(v1)", tags = "action house api")
public class ActionHouseController {
    @Autowired
    private IActionHouseService actionHouseService;

    @Autowired
    private IActionService actionService;

    @ApiOperation(value = "create action house from url")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public R addActionHouseWithoutBindingResult(@Valid NewActionHouseRequest newActionHouse) throws NoActionHouseException {
        ActionHouse actionHouse = actionHouseService.saveActionHouse(newActionHouse.getName());
        return R.ok().data("actionHouse", actionHouse).message("actionHouse created");
    }

    @ApiOperation(value = "create action house by json")
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public R addJsonActionHouseWithoutBindingResult(@Valid @RequestBody NewActionHouseRequest newActionHouse) {
        ActionHouse actionHouse =  actionHouseService.saveActionHouse(newActionHouse.getName());
        return R.ok().data("actionHouse", actionHouse).message("actionHouse created");
    }

    @ApiOperation(value = "get all action house")
    @GetMapping(path = "/", params = "!name")
    public R getAll() {
        List<ActionHouse> actionHouses =  actionHouseService.getAllActionHouse();
        return R.ok().data("actionhouses", actionHouses).message("get all action houses");
    }

    @ApiOperation(value = "delete action house by action house name")
    @DeleteMapping(path = "/{name}")
    public R deleteByName(@PathVariable String name) throws NoActionHouseException {
        actionHouseService.deleteActionHouseByName(name);
        return R.ok().message("actionHouse deleted");
    }

    @ApiOperation(value = "get action house by action house id")
    @GetMapping("/{id}")
    public R getOneActionHouse(@PathVariable("id") Long id) {
        ActionHouse actionHouse = actionHouseService.get(id);
        log.info("Get actionHouse: {}", actionHouse);
        return R.ok().data("actionHouse", actionHouse).message("get action house by id: " + id);
    }

    @ApiOperation(value = "get all actions by action house id")
    @GetMapping("/{id}/actions")
    public R getActionsByActionHouseId(@PathVariable("id") Long id) {
        List<Action> actions = actionService.getAllActionByActionHouse(id);
        log.info("Get actions: {}", actions);
        return R.ok().data("actions", actions).message("get all action by action house id: " + id);
    }
}
