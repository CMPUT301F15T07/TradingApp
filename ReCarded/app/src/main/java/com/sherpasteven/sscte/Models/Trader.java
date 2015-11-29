package com.sherpasteven.sscte.Models;

import android.content.Context;

/**
 * Created by elias on 28/11/15.
 */
public class Trader extends User {

    public Trader(User user, Context context) {
        super(user.getName(), user.getLocation(), user.getEmail(), context);
        setInventory(user.getInventory());
        setProfilePic(user.getProfilePic());
        setProfileID(user.getProfileId());
        setFriends(null);
        this.trades = null;
    }

    @Override
    public TradeLog getTrades(){
        throw new RuntimeException("Get Trades Not Implemented on Trader");
    }

    @Override
    public void setTrades(TradeLog trades) {
        throw new RuntimeException("Set Trades Not Implemented on Trader");
    }
}
