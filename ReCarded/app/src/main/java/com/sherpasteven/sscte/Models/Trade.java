package com.sherpasteven.sscte.Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * Initialises the trade model used for trading inventory systems.
 */
public class Trade extends Model {
    private Trader borrower;
    private Trader owner;

    private Calendar created;
    private Calendar lastupdate;
    private Calendar accepted;
    private String status;
    private UUID id;


    private ArrayList<Card> borrowlist;
    private ArrayList<Card> ownerlist;



    public Trade(Trader borrower, Trader owner) {
        setBorrower(borrower);
        setOwner(owner);
        setOwnerList(new ArrayList<Card>());
        setBorrowList(new ArrayList<Card>());
        created = Calendar.getInstance();
        setStatus("PENDING");
        id = UUID.randomUUID();
        //sets the created time to the current time
        created = Calendar.getInstance();
    }

    public Trade(Trader borrower, Trader owner, UUID tradeId) {
        this(borrower, owner);
        this.id = tradeId;
    }


    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Calendar getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Calendar lastupdate) {
        this.lastupdate = lastupdate;
    }

    public Calendar getAccepted() {
        return accepted;
    }

    public void setAccepted(Calendar accepted) {
        this.accepted = accepted;
    }

    public UUID getId() {
        return id;
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


    public Trader getBorrower() {
        return borrower;
    }

    public void setBorrower(Trader borrower) {
        this.borrower = borrower;
    }

    public Trader getOwner() {
        return owner;
    }

    public void setOwner(Trader owner) {
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

    /**
     * Adds the card to the borrow list (local user)
     * @param card
     * @return boolean (true on success)
     */
    public Boolean addBorrowList(Card card){
        if(card != null && card.isTradable()) {
            getBorrowList().add(card);
            return Boolean.TRUE;
        }

        else{return Boolean.FALSE;}
    }

    /**
     * Remove the card to the owner lsit (local user)
     * @param card
     * @return  boolean (true on success)
     */
    public Boolean removeBorrowList(Card card){
        if(getBorrowList().contains(card)) {
            getBorrowList().remove(card);
            return Boolean.TRUE;
        }

        else{return Boolean.FALSE;}
    }

    /**
     * Add the card to the owner lsit (friend)
     * @param card
     * @return  boolean (true on success)
     */
    public Boolean addOwnerList(Card card){
        if(card.isTradable()){
            getOwnerList().add(card);
            return Boolean.TRUE;
        }

        else{return Boolean.FALSE;}
    }

    /**
     * Remove the card to the owner lsit (friend)
     * @param card
     * @return  boolean (true on success)
     */
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
        lastupdate = Calendar.getInstance();
        if (status.equals("ACCEPTED")) {
            accepted = Calendar.getInstance();
        }
    }

    /**
     * Initialises a counteroffer given an existing trade set.
     * Does not reference previous trade.
     * @return a counteroffer trade.
     */
    public Trade counterOffer(){
        this.setStatus("DECLINED");
        Trade counter = new Trade(this.getBorrower(), this.getOwner(), this.getId());
        for(Card cards: this.getOwnerList()) {
            counter.addBorrowList(cards);
        }

        for(Card cards: this.getBorrowList()) {
            counter.addOwnerList(cards);
        }

        return counter;
    }
}