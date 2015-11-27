package com.sherpasteven.sscte.Models;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Initialises the trade model used for trading inventory systems.
 */
public class Trade extends Model {
    private transient User borrower;
    private User owner;

    //FIXME: Making this public to make tests correct, return to private
    private ArrayList<Card> borrowlist;
    private ArrayList<Card> ownerlist;
    private Calendar created;
    private Calendar lastupdate;
    private Calendar accepted;
    private String status;

    public Trade(User borrower, User owner) {
        setBorrower(borrower);
        setOwner(owner);
        setOwnerList(new ArrayList<Card>());
        setBorrowList(new ArrayList<Card>());
        created = Calendar.getInstance();
        setStatus("PENDING");
    }

    /**
     * Send over network to the owner.
     */
    public void sendTrade(){
        getBorrower().addPendingTrade(this);
    }

    /**
     * Receives a trade.
     * @param user
     */
    public void recieveTrade(User user){
        user.addPendingTrade(this);
    }

    //public void setNotification(User user){}

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ArrayList<Card> getBorrowList() {
        return borrowlist;
    }

    public void setBorrowList(ArrayList<Card> borrowlist) {
        this.borrowlist = borrowlist;
    }

    public ArrayList<Card> getOwnerList() {
        return ownerlist;
    }

    public void setOwnerList(ArrayList<Card> ownerlsit) {
        this.ownerlist = ownerlsit;
    }

    public Boolean addBorrowList(Card card){
        if(card != null && card.isTradable()) {
            getBorrowList().add(card);
            return Boolean.TRUE;
        }

        else{return Boolean.FALSE;}
    }

    public Boolean removeBorrowList(Card card){
        if(getBorrowList().contains(card)) {
            getBorrowList().remove(card);
            return Boolean.TRUE;
        }

        else{return Boolean.FALSE;}
    }

    public Boolean addOwnerList(Card card){
        if(card.isTradable()){
            getOwnerList().add(card);
            return Boolean.TRUE;
        }

        else{return Boolean.FALSE;}
    }

    public Boolean removeOwnerList(Card card){
        if(getOwnerList().contains(card)) {
            getOwnerList().remove(card);
            return Boolean.TRUE;
        }

        else{return Boolean.FALSE;}
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Initialises a counteroffer given an existing trade set.
     * Does not reference previous trade.
     * @return a counteroffer trade.
     */
    public Trade counterOffer(){
        this.setStatus("DECLINED");
        Trade counter = new Trade(this.getOwner(), this.getBorrower());
        for(Card cards: this.getOwnerList()) {
            counter.addBorrowList(cards);
        }

        for(Card cards: this.getBorrowList()) {
            counter.addOwnerList(cards);
        }

        return counter;
    }

}