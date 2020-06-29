package com.zui.actionhouse.service;

import com.zui.actionhouse.controller.request.NewActionBidRequest;
import com.zui.actionhouse.model.ActionBid;

import java.util.List;

public interface IActionBidService {
    ActionBid saveActionBid(NewActionBidRequest actionRequest);
    List<ActionBid> getAllActionBidsByBidder(String bidder);
    List<ActionBid> getAllActionBidsByActionId(Long actionId);
    ActionBid getWinner(Long actionId);

}
