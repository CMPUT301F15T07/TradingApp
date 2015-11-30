package com.sherpasteven.sscte.Models;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by joshua on 25/11/15.
 */
public class TradeComponents extends Model {

    private Trader borrower;
    private Trader owner;
    private ArrayList<Card> borrowlist;
    private ArrayList<Card> ownerlist;
    private UUID tradeId;

    public TradeComponents()  {
        setOwnerList(new ArrayList<Card>());
        setBorrowList(new ArrayList<Card>());
    }

    public UUID getTradeId() {
        return tradeId;
    }

    public void setTradeId(UUID tradeId) {
        this.tradeId = tradeId;
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

    public String userStringValue(User user) {
        return user.getEmail() + "," + user.getName() + "," + user.getLocation();
    }
}
