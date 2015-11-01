package com.sherpasteven.sscte.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by salim_000 on 2015-10-30.
 */
public class Trade extends Model {
    private User borrower;
    private User owner;

    //Making this public to make tests correct, don't like this being public so i might change the test. (Salim)
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

    public void sendTrade(){
        getBorrower().addPendingTrade(this);
        //Send over network to the owner

    }

    public void recieveTrade(User user){
        user.addPendingTrade(this); //The person recieving it

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
        if(card.isTradable()) {
            getBorrowList().add(card);
        return Boolean.TRUE;
        }

        else{return Boolean.FALSE;}
    }

    public Boolean addOwnerList(Card card){
        if(card.isTradable()){
        getOwnerList().add(card);
        return Boolean.TRUE;
    }
    else{return Boolean.FALSE;
    }

}
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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