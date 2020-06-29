package com.zui.actionhouse.service.impl;


import com.zui.actionhouse.config.ResultCodeEnum;
import com.zui.actionhouse.exception.NoActionHouseException;
import com.zui.actionhouse.repository.ActionHouseRepository;
import com.zui.actionhouse.service.IActionHouseService;
import com.zui.actionhouse.model.ActionHouse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@CacheConfig(cacheNames = "ActionHouseCache")
public class ActionHouseServiceImpl implements IActionHouseService {

    @Autowired
    ActionHouseRepository actionHouseRepository;

    @Override
    public ActionHouse saveActionHouse(String name) throws NoActionHouseException {
        //exist then throw exception
        Optional.ofNullable(actionHouseRepository.findByName(name)).ifPresent(s -> {
            throw new NoActionHouseException(ResultCodeEnum.No_ACTION_HOUSE_ERROR);
        } );
        return actionHouseRepository.save(ActionHouse.builder().name(name).build());
    }

    public List<ActionHouse> getAllActionHouse(){
        return actionHouseRepository.findAll();
    }

    public void deleteActionHouseByName(String name) throws NoActionHouseException {
        //TODO, delete all actions and bids?
        Optional<Long> actionHouse = Optional.ofNullable(Optional.ofNullable(actionHouseRepository.findByName(name)).map(e -> e.getId()).orElseThrow(() -> new NoActionHouseException(ResultCodeEnum.No_ACTION_HOUSE_ERROR)));
        actionHouseRepository.deleteById(actionHouse.get());
    }

    @Override
    public ActionHouse get(Long id) {
        return actionHouseRepository.getOne(id);
    }


}
