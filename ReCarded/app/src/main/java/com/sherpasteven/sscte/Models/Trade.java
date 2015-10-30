package com.sherpasteven.sscte.Models;

import java.util.ArrayList;

/**
 * Created by salim_000 on 2015-10-30.
 */
public class Trade extends Model {
    private User borrower;
    private User owner;

    //Making this public to make tests correct, don't like this being public so i might change the test. (Salim)
    public ArrayList<Card> list1 = new ArrayList<Card>();
    public ArrayList<Card> list2 = new ArrayList<Card>();

    public Trade(User borrower, User owner) {
        this.borrower = borrower;
        this.owner = owner;
    }

    public void sendTrade(User user){
        user.trades.addTrade(this);
    }

    public void setNotification(User user){

    }
}
