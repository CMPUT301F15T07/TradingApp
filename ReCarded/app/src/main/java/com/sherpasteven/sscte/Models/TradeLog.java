package com.sherpasteven.sscte.Models;

import java.util.ArrayList;

/**
 * Develops a tradelog, similar to an arraylist of trades.
 * Stores data used for past and current trades.
 * Used for tabbed trade activity.
 */
public class TradeLog extends Model {
    private ArrayList<Trade> pendingTrades;
    private ArrayList<Trade> pastTrades;

    public TradeLog(ArrayList<Trade> pendingTrades, ArrayList<Trade> pastTrades) {
        this.pendingTrades = pendingTrades;
        this.pastTrades = pastTrades;
    }

    public TradeLog(){
        this.pendingTrades = new ArrayList<Trade>();
        this.pastTrades = new ArrayList<Trade>();
    }

    /**
     * Identifies the trade status as either in pending trades or completed trades.
     * FIXME: Requires notification addition.
     * @param trade Trade to be added to the trade log.
     */
    public void tradeFinalized(Trade trade){
        if(trade.getStatus().equals("ACCEPTED") || trade.getStatus().equals("DECLINED")){
            pastTrades.add(trade);
            pendingTrades.remove(trade);
        }
    }

    public void addTrade(Trade trade){
        pendingTrades.add(trade);
    }

    public ArrayList<Trade> getPendingTrades() {
        return pendingTrades;
    }

    public void setPendingTrades(ArrayList<Trade> pendingTrades) {
        this.pendingTrades = pendingTrades;
    }

    public ArrayList<Trade> getPastTrades() {
        return pastTrades;
    }

    public void setPastTrades(ArrayList<Trade> pastTrades) {
        this.pastTrades = pastTrades;
    }

    /**
     * Deletes a trade from the trade log.
     * @param trade Trade to be deleted.
     * @return status of deleted trade: true if deleted, false if error.
     */
    public Boolean removeFinalizedTrade(Trade trade){

        if(trade.getStatus().equals("ACCEPTED") || trade.getStatus().equals("DECLINED")){
            return getPastTrades().remove(trade);
        }
        else {return Boolean.FALSE;}

    }

    /**
     * Identifies whether a trade is previously included
     * in the trade log.
     * @param trade Trade is recognised before query.
     * @return true if contained; false if not contained.
     */
    public Boolean containsPastTrade(Trade trade){
        return getPastTrades().contains(trade);
    }

}
