package com.zui.actionhouse.service.impl;

import com.zui.actionhouse.config.ResultCodeEnum;
import com.zui.actionhouse.controller.request.NewActionBidRequest;
import com.zui.actionhouse.exception.ActionNotTerminatedException;
import com.zui.actionhouse.exception.ActionTerminatedException;
import com.zui.actionhouse.exception.NoActionException;
import com.zui.actionhouse.exception.PriceLowException;
import com.zui.actionhouse.model.Action;
import com.zui.actionhouse.model.ActionBid;
import com.zui.actionhouse.model.ActionState;
import com.zui.actionhouse.repository.ActionBidRepository;
import com.zui.actionhouse.repository.ActionRepository;
import com.zui.actionhouse.service.IActionBidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@CacheConfig(cacheNames = "ActionBidCache")
public class ActionBidServiceImpl implements IActionBidService {
    @Autowired
    ActionRepository actionRepository;

    @Autowired
    ActionBidRepository actionBidRepository;

    @Override
    public ActionBid saveActionBid(NewActionBidRequest actionBidRequest) {
        Optional<Action> action = Optional.ofNullable(actionRepository.findById(actionBidRequest.getActionId()))
                .orElseThrow(() -> new NoActionException(ResultCodeEnum.No_ACTION_ERROR));

        if(action.get().getState().equals(ActionState.TERMINATED)){
            throw new ActionTerminatedException(ResultCodeEnum.ACTION_TERMINATED_ERROR);
        }

        if(!action.get().getCurrentPrice().isLessThan(actionBidRequest.getPrice())){
            throw new PriceLowException(ResultCodeEnum.PRICE_LOW_ERROR);
        }
        Date date = new Date();
        if(date.after(action.get().getEndTime())){
            action.get().setState(ActionState.TERMINATED);
            actionRepository.save(action.get());
            throw new ActionTerminatedException(ResultCodeEnum.ACTION_TERMINATED_ERROR);
        }
        ActionBid actionBid = ActionBid.builder()
                .bidder(actionBidRequest.getBidder())
                .price(actionBidRequest.getPrice())
                .action(action.get())
                .build();
        action.get().setCurrentPrice(actionBidRequest.getPrice());
        if(action.get().getState().equals(ActionState.NON_STARTED)) {
            action.get().setState(ActionState.RUNNING);
        }
        actionRepository.save(action.get());
        return actionBidRepository.save(actionBid);
    }

    @Override
    public List<ActionBid> getAllActionBidsByBidder(String bidder) {
        return actionBidRepository.findAllByBidder(bidder);
    }

    @Override
    public List<ActionBid> getAllActionBidsByActionId(Long actionId) {
        return actionBidRepository.findActionBidsByAction_Id(actionId);
    }

    @Override
    public ActionBid getWinner(Long actionId) {
        Optional<Action> action = Optional.ofNullable(actionRepository.findById(actionId))
                .orElseThrow(() -> new NoActionException(ResultCodeEnum.No_ACTION_ERROR));

        if(!action.get().getState().equals(ActionState.TERMINATED)){
            throw new ActionNotTerminatedException(ResultCodeEnum.ACTION_NOT_TERMINATED_ERROR);
        }
        Date date = new Date();
        if(!date.after(action.get().getEndTime())){
            throw new ActionNotTerminatedException(ResultCodeEnum.ACTION_NOT_TERMINATED_ERROR);
        } else {
            action.get().setState(ActionState.TERMINATED);
            actionRepository.save(action.get());
        }
        return actionBidRepository.findFirstByAction_IdOrderByPriceDesc(actionId);
    }
}
