package com.sherpasteven.sscte.Models;

import java.util.ArrayList;

/**
 * Created by salim_000 on 2015-10-30.
 */
public class TradeLog {
    private ArrayList<Trade> currentTrades = new ArrayList<Trade>();
    private ArrayList<Trade> pastTrades = new ArrayList<Trade>();

    public TradeLog(ArrayList<Trade> currentTrades, ArrayList<Trade> pastTrades) {
        this.currentTrades = currentTrades;
        this.pastTrades = pastTrades;
    }

    public void tradeFinalized(Trade trade){
        //might need to implement more stuff, like notifications i guess
        pastTrades.add(trade);
        currentTrades.remove(trade);
    }

    public void addTrade(Trade trade){
        currentTrades.add(trade);
    }
}
