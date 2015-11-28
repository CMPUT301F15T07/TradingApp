package com.sherpasteven.sscte.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by elias on 28/11/15.
 */
public class TradeSynchronizer {
    private Profile localProfile;
    public TradeSynchronizer(Profile localProfile){
        this.localProfile = localProfile;
    }

    /**
     * This method will syncronize the trades of the user after
     * thier friends are updated
     */
    public void SynchronizeTrades(){
        User localUser = localProfile.getUser();
        ProfileId localId = localUser.getProfileId();
        ArrayList<Trade> pendingTrades = localUser.getTrades().getPendingTrades();
        ArrayList<Trade> pastTrades = localUser.getTrades().getPastTrades();

        /* we assume that the friends have been updated since
         the last time the trades were updated */
        List<Friend> friends = localUser.getFriends();

        //adding new trades from friends
        for (Friend friend : friends) {
            for (Trade friendTrade : friend.getTrades().getPendingTrades()) {

                //If the local user is the owner of the trade
                if (friendTrade.getOwner().getProfileId().equals(localId)) {

                    //local user doesn't have trade
                    if (localUser.getTrades().getTrade(friendTrade.getId()) == null) {
                        //Trade swappedTrade = tradeReverse(friendTrade, friend);
                        //add new trade to user from friend
                        pendingTrades.add(friendTrade);
                    }
                }
            }
        }

        //updating existing trades
        for (int i = 0; i < pendingTrades.size(); i++) {
            Trade userTrade = pendingTrades.get(i);
            UUID id = userTrade.getId();
            for (Friend friend : friends) {
                Trade friendTrade = friend.getTrades().getTrade(id);
                if (friendTrade == null) continue;

                //The friend has a newer version of the trade then the user does
                if (friendTrade.getLastupdate().after(userTrade.getLastupdate())) {
                    pendingTrades.remove(i);
                    //Trade swappedTrade = tradeReverse(friendTrade, friend);
                    if (friendTrade.getStatus().equals("PENDING")){
                        pendingTrades.add(friendTrade);
                    } else {
                        pastTrades.add(friendTrade);
                    }
                }
            }
        }

        localUser.getTrades().setPendingTrades(pendingTrades);
        localUser.getTrades().setPastTrades(pastTrades);
    }

    private Trade tradeReverse(Trade initialTrade, Trader borrower) {
        //tradelog.addCounterOfferTrade(model, countertrade);
        TradeComposer.getTradeComposer().resetComponents();
        TradeComposer.getTradeComposer().getComponents().setOwner(borrower);
        TradeComposer.getTradeComposer().getComponents().setBorrower(initialTrade.getOwner());
        TradeComposer.getTradeComposer().getComponents().setTradeId(initialTrade.getId());
        TradeComposer.getTradeComposer().getComponents().setOwnerList(initialTrade.getBorrowList());
        TradeComposer.getTradeComposer().getComponents().setBorrowList(initialTrade.getOwnerList());
        return TradeComposer.getTradeComposer().composeTrade();
    }

}
