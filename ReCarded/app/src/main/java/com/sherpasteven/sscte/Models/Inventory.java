package com.sherpasteven.sscte.Models;

import java.util.ArrayList;

/**
 * Created by Joshua on 2015-10-12.
 */
public class Inventory extends Model {

    private ArrayList<Card> cards;

    public Inventory(ArrayList<Card> cards){
        this.cards = cards;
    }

    public Inventory(){
        this.cards = new ArrayList<Card>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    void addCard(Card card){

        if(containsCard(card)){
            incrementCard(card, card.getQuantity());
        }
        else {getCards().add( new Card(card.getName(), card.getQuantity(), card.getQuality(), card.getCatagory(),
                card.getSeries(), card.isTradable(), card.getComments(),card.getOwner()));}
    }

    public Boolean containsCard(Card card) {
        int size = getCards().size();

        for(int i = 0; i < size; i++){

            if(getCards().get(i).equals(card)){
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;

    }

    Card getCard(int index){
        return this.cards.get(index);
    }

    void removeFromInventory(Card card){

        int size = getCards().size();

        for(int i = 0; i < size; i++){

            if(getCard(i).equals(card)){
                getCards().remove(i);
            }
        }

    }


    Card returnCard(Card card){

        int size = getCards().size();

        for(int i = 0; i < size; i++){

            if(getCard(i).equals(card)){
                return getCard(i);
            }
        }

        return null;
    }


    void removeCard(Card card, int amount) {
        try {
            if (amount < returnCard(card).getQuantity()) {
                incrementCard(card, 0-amount);
            }
            else if (amount == returnCard(card).getQuantity()) {
                removeFromInventory(card);
            }
            else {throw new IllegalArgumentException("You tried removing more of card than the user had");
            }
        }

        catch(IllegalArgumentException e){
                e.printStackTrace();
        }
    }

    void incrementCard(Card card, int amount){
        returnCard(card).setQuantity(returnCard(card).getQuantity() + amount);
    }
}

