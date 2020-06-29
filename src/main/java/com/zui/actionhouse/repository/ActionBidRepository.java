package com.zui.actionhouse.repository;

import com.zui.actionhouse.model.ActionBid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActionBidRepository extends JpaRepository<ActionBid, Long> {
    List<ActionBid> findAllByBidder(String bidder);
    List<ActionBid> findActionBidsByAction_Id(Long id);
    ActionBid findFirstByAction_IdOrderByPriceDesc(Long id);

}
