package com.zui.actionhouse.service.impl;


import com.zui.actionhouse.config.ResultCodeEnum;
import com.zui.actionhouse.exception.NoActionException;
import com.zui.actionhouse.exception.NoActionHouseException;
import com.zui.actionhouse.repository.ActionHouseRepository;
import com.zui.actionhouse.controller.request.NewActionRequest;
import com.zui.actionhouse.model.Action;
import com.zui.actionhouse.model.ActionHouse;
import com.zui.actionhouse.model.ActionState;
import com.zui.actionhouse.repository.ActionRepository;
import com.zui.actionhouse.service.IActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@CacheConfig(cacheNames = "ActionCache")
public class ActionServiceImpl implements IActionService {

    @Autowired
    ActionRepository actionRepository;

    @Autowired
    ActionHouseRepository actionHouseRepository;

    @Override
    public Action saveAction(NewActionRequest actionRequest) {
        ActionHouse actionHouse = actionHouseRepository.findById(actionRequest.getActionHouseId()).orElseThrow(() ->new NoActionHouseException(ResultCodeEnum.No_ACTION_HOUSE_ERROR));

        Action action = Action.builder()
                .name(actionRequest.getName())
                .description(actionRequest.getDescription())
                .startingTime(actionRequest.getStartingTime())
                .endTime(actionRequest.getEndTime())
                .startPrice(actionRequest.getStartPrice())
                .currentPrice(actionRequest.getCurrentPrice())
                .state(ActionState.NON_STARTED)
                .actionHouse(actionHouse)
                .build();

        return actionRepository.save(action);
    }

    @Override
    public List<Action> getAllActionByActionHouse(Long actionHouseId) {
        return actionRepository.findActionsByActionHouse_Id(actionHouseId);
    }


    @Override
    public List<Action> getAllActionByState(ActionState actionState) {
        return  actionRepository.findActionsByState(actionState);
    }

    @Override
    public List<Action> getAllAction() {
        List<Action> actions =  actionRepository.findAll();

        return actions;
    }

    @Override
    public Action get(Long id) {
        return actionRepository.getOne(id);
    }

    @Override
    public void deleteActionByActionId(Long actionId) {
        actionRepository.findById(actionId).orElseThrow(() ->new NoActionException(ResultCodeEnum.No_ACTION_ERROR));
        actionRepository.deleteById(actionId);
    }


}
