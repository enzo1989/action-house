package com.zui.actionhouse.service;

import com.zui.actionhouse.AppTest;
import com.zui.actionhouse.controller.request.NewActionRequest;
import com.zui.actionhouse.model.Action;
import com.zui.actionhouse.model.ActionHouse;
import com.zui.actionhouse.model.ActionState;
import com.zui.actionhouse.repository.ActionHouseRepository;
import com.zui.actionhouse.repository.ActionRepository;
import com.zui.actionhouse.service.impl.ActionHouseServiceImpl;
import com.zui.actionhouse.service.impl.ActionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActionHouseServiceTest extends AppTest {

    @Mock
    ActionHouseRepository actionHouseRepository;

    @InjectMocks
    private ActionHouseServiceImpl actionHouseService;


    @Test
    public void getAllActionHouse(){
        when(actionHouseRepository.findAll())
                .thenReturn(getActionHouseList());
        List<ActionHouse> actionHouses = actionHouseService.getAllActionHouse();
        verify(actionHouseRepository, times(1)).findAll();
        assertEquals("actionHouse1", actionHouses.get(0).getName());
    }

    public ActionHouse getOneActionHouse(){
        ActionHouse actionHouse = ActionHouse.builder()
                .name("actionHouse1")
                .build();
        return actionHouse;
    }

    public List<ActionHouse> getActionHouseList(){
        List<ActionHouse> actionHouses = new ArrayList<>();
        actionHouses.add(ActionHouse.builder().name("actionHouse1").build());
        actionHouses.add(ActionHouse.builder().name("actionHouse1").build());
        return actionHouses;
    }
}
