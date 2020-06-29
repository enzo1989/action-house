package com.zui.actionhouse.repository;

import com.zui.actionhouse.model.ActionHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionHouseRepository extends JpaRepository<ActionHouse, Long> {
    ActionHouse findByName(String name);

}
