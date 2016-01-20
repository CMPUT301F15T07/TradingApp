package com.sherpasteven.sscte.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class is responsible for synchronizing the trades of
 * the user with their friends such as when a new trade is made
 * or counteroffered.
 */
public class TradeSynchronizer {
    private Profile localProfile;
    private LocalProfileSerializer serializer;
    public TradeSynchronizer(Profile localProfile){
        this.localProfile = localProfile;
    }

    /**
     * This method will syncronize the trades of the user after
     * their friends are updated
     */
    public void SynchronizeTrades(){
        User localUser = localProfile.getUser();
        serializer = new LocalProfileSerializer();
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
        int numPending = pendingTrades.size();
        for (int i = 0; i < numPending; i++) {
            Trade userTrade = pendingTrades.get(i);
            UUID id = userTrade.getId();
            for (Friend friend : friends) {
                Trade friendTrade = friend.getTrades().getTrade(id);
                if (friendTrade == null) continue;

                //The friend has a newer version of the trade then the user does
                if (friendTrade.getLastupdate().after(userTrade.getLastupdate())) {
                    pendingTrades.remove(i);
                    if (friendTrade.getStatus().equals("PENDING")){
                        pendingTrades.add(friendTrade);
                    } else {
                        //trade was accepted
                        //TODO(should this be added to pendingTrades?)
                        pastTrades.add(friendTrade);
                        swapItems(friendTrade);
                    }
                }
            }
        }

        localUser.getTrades().setPendingTrades(pendingTrades);
        localUser.getTrades().setPastTrades(pastTrades);
    }

    public void swapItems(Trade trade){
        Inventory inventory = localProfile.getUser().getInventory();
        List<Card> userList;
        List<Card> friendList;
        //local profile is owner
        if (trade.getOwner().getProfileId().equals(localProfile.getProfileId())) {
            userList = trade.getOwnerList();
            friendList = trade.getBorrowList();

        } else {
            //local profile is borrower
            userList = trade.getBorrowList();
            friendList = trade.getOwnerList();
        }

        for (Card card : userList) {
            inventory.removeCard(card, card.getQuantity());
        }

        for (Card card : friendList) {
            //TODO(swap the card owner's name here) <- test this
            //card.setOwner(localProfile.getUser().getName());
            inventory.addCard(card);
        }
    }
}
