package com.zui.actionhouse.service;

import com.zui.actionhouse.AppTest;
import com.zui.actionhouse.model.Action;
import com.zui.actionhouse.model.ActionBid;
import com.zui.actionhouse.model.ActionState;
import com.zui.actionhouse.repository.ActionBidRepository;
import com.zui.actionhouse.repository.ActionRepository;
import com.zui.actionhouse.service.impl.ActionBidServiceImpl;
import org.joda.money.Money;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActionBidServiceTest extends AppTest {

    @Mock
    private ActionRepository actionRepository;

    @Mock
    ActionBidRepository actionBidRepository;

    @InjectMocks
    private ActionBidServiceImpl actionBidService;

    @Test
    public void getAllActionBids() throws ParseException {
        Long actionId = 1L;
        when(actionBidRepository.findActionBidsByAction_Id(actionId))
                .thenReturn(getActionBidList());
        List<ActionBid> actionBids = actionBidService.getAllActionBidsByActionId(actionId);
        verify(actionBidRepository, times(1)).findActionBidsByAction_Id(actionId);
        assertEquals("bidder1", actionBids.get(0).getBidder());
    }

    @Test
    public void getActionWinner() throws ParseException {
        Long actionId = 1L;
        when(actionBidRepository.findFirstByAction_IdOrderByPriceDesc(actionId))
                .thenReturn(getOneBid());
        when(actionRepository.findById(actionId))
                .thenReturn(Optional.ofNullable(getOneAction()));
        ActionBid actionBid = actionBidService.getWinner(actionId);
        verify(actionBidRepository, times(1)).findFirstByAction_IdOrderByPriceDesc(actionId);
        assertEquals("bidder1", actionBid.getBidder());
    }

    public ActionBid getOneBid() throws ParseException {
        return ActionBid.builder()
                .bidder("bidder1")
                .action(getOneAction())
                .build();
    }

    public List<ActionBid> getActionBidList() throws ParseException {
        List<ActionBid> actionBids = new ArrayList<>();
        Action action = getOneAction();
        actionBids.add(ActionBid.builder().bidder("bidder1").action(action).build());
        actionBids.add(ActionBid.builder().bidder("bidder2").action(action).build());
        return actionBids;
    }

    public Action getOneAction() throws ParseException {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String input = "2020-06-01";

        Date date = ft.parse(input);
        return Action.builder().name("action1")
                .state(ActionState.TERMINATED)
                .endTime(date)
                .build();
    }
}
