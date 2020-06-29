package com.zui.actionhouse.repository;


import com.zui.actionhouse.model.ActionState;
import com.zui.actionhouse.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long> {
    List<Action> findActionsByState(ActionState actionState);
    List<Action> findActionsByActionHouse_Id(Long actionId);
}
