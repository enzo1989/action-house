package com.zui.actionhouse.service;



import com.zui.actionhouse.model.ActionState;
import com.zui.actionhouse.controller.request.NewActionRequest;
import com.zui.actionhouse.model.Action;

import java.util.List;

public interface IActionService {
    Action saveAction(NewActionRequest actionRequest);
    List<Action> getAllActionByActionHouse(Long actionHouseId);
    List<Action> getAllActionByState(ActionState actionState);
    List<Action> getAllAction();
    Action get(Long id);
    void deleteActionByActionId(Long actionId);
}
