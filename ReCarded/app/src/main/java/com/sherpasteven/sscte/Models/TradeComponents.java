package com.sherpasteven.sscte.Models;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Components used to generate the trade.
 * Contains a list of components used by the TradeComposer singleton,
 * in order to create a 'temporary' trade to process.
 */
public class TradeComponents extends Model {

    private Trader borrower;
    private Trader owner;
    private ArrayList<Card> borrowlist;
    private ArrayList<Card> ownerlist;
    private UUID tradeId;

    /**
     * Unique trade id's are used primarily to ensure that
     * counteroffers are completed using the same trade id.
     * @return UUID of the trade.
     */
    public UUID getTradeId() {
        return tradeId;
    }

    public void setTradeId(UUID tradeId) {
        this.tradeId = tradeId;
    }

    public TradeComponents()  {

        setOwnerList(new ArrayList<Card>());
        setBorrowList(new ArrayList<Card>());
    }

    /**
     * Provides the logic to determine whether or not the trade is ready to process.
     * If it's not ready to process, further actions taken to export a trade are prevented.
     * @return boolean declaring whether or not the trade is composable
     */
    public Boolean isComposable(){
        if(getOwnerList() != null && getBorrowList() != null){
            if(!getOwnerList().isEmpty() &&  !getBorrowList().isEmpty()){
                if(getOwner() != null && getBorrowList() != null){
                    for (Card card : getBorrowList()) {
                        if (!card.isTradable()) {
                            return Boolean.FALSE;
                        }
                    }
                    for (Card card : getOwnerList()) {
                        if (!card.isTradable()) {
                            return Boolean.FALSE;
                        }
                    }
                    return Boolean.TRUE;
                }
            }
        }

        return Boolean.FALSE;
    }

    public void addToOwner(Card card){
        if(card.isTradable()) {
            if(!getOwnerList().contains(card)) {
                getOwnerList().add(card);
                notifyViews();
            }
        }
    }

    public void addToBorrower(Card card){
        if(card.isTradable()) {
            if(!getBorrowList().contains(card)) {
                getBorrowList().add(card);
                notifyViews();
            }
        }
    }

    public Trader getBorrower() {
        return borrower;
    }

    public void setBorrower(Trader borrower) {
        this.borrower = borrower;
        notifyViews();
    }

    public Trader getOwner() {
        return owner;
    }

    public void setOwner(Trader owner) {
        this.owner = owner;
        notifyViews();
    }

    public ArrayList<Card> getBorrowList() {
        return borrowlist;
    }

    public void setBorrowList(ArrayList<Card> borrowlist) {
        this.borrowlist = borrowlist;
        notifyViews();
    }

    public ArrayList<Card> getOwnerList() {
        return ownerlist;

    }

    public void setOwnerList(ArrayList<Card> ownerlist) {
        this.ownerlist = ownerlist;
        notifyViews();
    }

    /**
     * Gets a string value providing the key details of a user.
     * @param user to parse
     * @return string containing concatenated details.
     */
    public String userStringValue(User user) {
        return user.getEmail() + "," + user.getName() + "," + user.getLocation();
    }
}
