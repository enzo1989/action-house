package com.zui.actionhouse.service;

import com.zui.actionhouse.AppTest;
import com.zui.actionhouse.controller.request.NewActionRequest;
import com.zui.actionhouse.model.Action;
import com.zui.actionhouse.model.ActionState;
import com.zui.actionhouse.repository.ActionHouseRepository;
import com.zui.actionhouse.repository.ActionRepository;
import com.zui.actionhouse.service.impl.ActionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActionServiceTest extends AppTest {

    @Mock
    private ActionRepository actionRepository;
    @Mock
    ActionHouseRepository actionHouseRepository;

    @InjectMocks
    private ActionServiceImpl actionService;


    @Test
    public void getAllAction(){
        when(actionRepository.findAll())
                .thenReturn(getActionList());
        List<Action> actions = actionService.getAllAction();
        verify(actionRepository, times(1)).findAll();
        assertEquals("action1", actions.get(0).getName());
    }

    @Test
    public void getActionByActionHouseId(){
        Long actionHouseId = 1L;
        when(actionRepository.findActionsByActionHouse_Id(actionHouseId))
                .thenReturn(getActionList());
        List<Action> actions = actionService.getAllActionByActionHouse(actionHouseId);
        verify(actionRepository, times(1)).findActionsByActionHouse_Id(actionHouseId);
        assertEquals("action1", actions.get(0).getName());
    }

    @Test
    public void deleteActionByActionId(){
        Long actionId = 1L;
        actionService.deleteActionByActionId(actionId);
        verify(actionRepository, times(1)).deleteById(actionId);
    }

    public NewActionRequest getNewActionRequest(){
        return NewActionRequest.builder().name("action1").description("action1").build();
    }
    public Action getOneAction(NewActionRequest actionRequest){
        Action action = Action.builder()
                .name(actionRequest.getName())
                .description(actionRequest.getDescription())
                .startingTime(actionRequest.getStartingTime())
                .endTime(actionRequest.getEndTime())
                .startPrice(actionRequest.getStartPrice())
                .currentPrice(actionRequest.getCurrentPrice())
                .state(ActionState.NON_STARTED)
                .build();
        return action;
    }

    public List<Action> getActionList(){
        List<Action> actions = new ArrayList<>();
        actions.add(Action.builder().name("action1").description("action1").build());
        actions.add(Action.builder().name("action2").description("action2").build());
        return actions;
    }
}
