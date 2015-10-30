package com.sherpasteven.sscte.Models;

import java.util.ArrayList;

/**
 * Created by salim_000 on 2015-10-30.
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

    public void tradeFinalized(Trade trade){
        //might need to implement more stuff, like notifications i guess
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

    public Boolean removeFinalizedTrade(Trade trade){

        return getPastTrades().remove(trade);

    }
}
