package com.sherpasteven.sscte.Models;

import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by joshua on 25/11/15.
 */
public class TradeComponents extends Model {



    private User borrower;
    private User owner;
    private ArrayList<Card> borrowlist;
    private ArrayList<Card> ownerlist;

    public TradeComponents()  {

        setOwnerList(new ArrayList<Card>());
        setBorrowList(new ArrayList<Card>());
    }

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


    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
        notifyViews();
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
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

    public String userStringValue(User user) {
        return user.getEmail() + "," + user.getName() + "," + user.getLocation();
    }
}
