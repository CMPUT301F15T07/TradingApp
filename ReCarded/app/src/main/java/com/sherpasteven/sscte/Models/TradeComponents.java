package com.sherpasteven.sscte.Models;

import java.util.ArrayList;

/**
 * Created by joshua on 25/11/15.
 */
public class TradeComponents {



    private User borrower;
    private User owner;
    private ArrayList<Card> borrowlist;
    private ArrayList<Card> ownerlist;

    public TradeComponents() {

        setOwnerList(new ArrayList<Card>());
        setBorrowList(new ArrayList<Card>());
    }

    public Boolean isComposable(){
        if(getOwnerList() != null && getBorrowList() != null){
            if(!getOwnerList().isEmpty() &&  !getBorrowList().isEmpty()){
                if(getOwner() != null && getBorrowList() != null){
                    return Boolean.TRUE;
                }
            }
        }

        return Boolean.FALSE;
    }

    public void addToOwner(Card card){
        getOwnerList().add(card);
    }

    public void addToBorrower(Card card){
        getBorrowList().add(card);
    }


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

    public void setOwnerList(ArrayList<Card> ownerlist) {
        this.ownerlist = ownerlist;
    }

    public String userStringValue(User user) {
        return user.getEmail() + "," + user.getName() + "," + user.getLocation();
    }
}
