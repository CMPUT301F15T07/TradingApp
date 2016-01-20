package com.sherpasteven.sscte.Models;

import android.content.Context;

/**
 * Created by elias on 28/11/15.
 */

/**
 * Specific version of the user used when trading. Used to stop
 * recursive calls of trades in the tradelog, so the app and elastic
 * search does not break.
 */
public class Trader extends User {

    public Trader(User user, Context context) {
        super(user.getName(), user.getLocation(), user.getEmail(), user.getProfilePic(), user.getProfileId(), user.getRating());
        setInventory(user.getInventory());
        setRating(user.getRating());
        setFriends(null);
        this.trades = null;
    }

    /**
     * Throws a runtime exception when getting trades to
     * stop recursive trades in owner and borrower trades
     * @return
     */
    @Override
    public TradeLog getTrades(){
        throw new RuntimeException("Get Trades Not Implemented on Trader");
    }

    /**
     * Throws a runtime exception when getting trades to
     * stop recursive trades in owner and borrower trades
     * @return
     */
    @Override
    public void setTrades(TradeLog trades) {
        throw new RuntimeException("Set Trades Not Implemented on Trader");
    }
}
