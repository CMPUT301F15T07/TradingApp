package com.sherpasteven.sscte.Models;

/**
 * Created by joshua on 25/11/15.
 */
public class TradeComposer {
    private static TradeComposer ourInstance = new TradeComposer();
    private TradeComponents components;

    public static TradeComposer getTradeComposer() {
        return ourInstance;
    }

    private TradeComposer() {
    }

    public TradeComponents getComponents() {
        if (components == null) {
            this.components = new TradeComponents();
            return this.components;
        } else {
            return this.components;
        }
    }

    public void resetComponents() {
        this.components = null;
    }

    public Trade composeTrade(){
        if(getComponents().isComposable()){
            Trade trade =  new Trade(getComponents().getBorrower(),
                    getComponents().getOwner());
            trade.setBorrowList(getComponents().getBorrowList());
            trade.setOwnerList(getComponents().getOwnerList());
            resetComponents();
            return trade;
        }

        return null;
    }

}
