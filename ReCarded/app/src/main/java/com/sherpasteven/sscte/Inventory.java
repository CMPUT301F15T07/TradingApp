package com.sherpasteven.sscte;

import java.util.ArrayList;

/**
 * Created by Joshua on 2015-10-12.
 */
public class Inventory {

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
        else {getCards().add(card);}
    }

    public Boolean containsCard(Card card) {

        return getCards().contains(card);

    }

    Card getCard(int index){
        return this.cards.get(index);
    }

    Card returnCard(Card card){
        if(containsCard(card)) {
            return getCard(getCards().indexOf(card));
        }

        else{return null;}
    }

    void removeCard(Card card, int amount) {
        try {
            if (amount < returnCard(card).getQuantity()) {
                incrementCard(card, amount - returnCard(card).getQuantity());
            }
            else if (amount == returnCard(card).getQuantity()) {
                getCards().remove(card);
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

