package com.sherpasteven.sscte.Models;

/**
 * TradeComposer is a singleton designed to hold
 * a temporary trade in action. This ensures that the
 * tradelog space is preserved while a trade is being manipulated.
 * TradeComposer is stored throughout the entire application - as such
 * there will only ever be one instance of such composer.
 */
public class TradeComposer {
    private static TradeComposer ourInstance = new TradeComposer();
    private TradeComponents components;

    private TradeComposer() {
    }

    public static TradeComposer getTradeComposer() {
        return ourInstance;
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

    /**
     * If the components are complete and correct, the trade will be
     * created and become available for use.
     * @return trade, containing compoenents, for processing.
     */
    public Trade composeTrade(){
        if(getComponents().isComposable()){
            Trade trade;

            if (getComponents().getTradeId() == null) {
                trade =  new Trade(getComponents().getBorrower(),
                        getComponents().getOwner());
            } else {
                trade = new Trade(getComponents().getBorrower(),
                        getComponents().getOwner(), getComponents().getTradeId());
            }

            trade.setBorrowList(getComponents().getBorrowList());
            trade.setOwnerList(getComponents().getOwnerList());
            resetComponents();
            return trade;
        }

        return null;
    }

}
