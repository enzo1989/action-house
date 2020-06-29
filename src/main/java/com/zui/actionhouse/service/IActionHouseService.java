package com.zui.actionhouse.service;


import com.zui.actionhouse.exception.NoActionHouseException;
import com.zui.actionhouse.model.ActionHouse;
import java.util.List;

public interface IActionHouseService {
    ActionHouse saveActionHouse(String name) throws NoActionHouseException;
    List<ActionHouse> getAllActionHouse();
    void deleteActionHouseByName(String name) throws NoActionHouseException;
    ActionHouse get(Long id);
}
