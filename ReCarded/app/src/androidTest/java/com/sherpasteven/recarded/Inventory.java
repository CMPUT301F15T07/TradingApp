package com.sherpasteven.recarded;

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
}
