package com.sherpasteven.sscte.Models;

import java.util.ArrayList;

/**
 * The inventory represents the collection of cards that a User has.
 * When a user gets a new card or trades one of their cards the work will be done in inventory.
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


    public void addCard(Card card){

        if(containsCard(card)){
            incrementCard(card, card.getQuantity());
        }
        else {getCards().add( new Card(card.getName(), card.getQuantity(), card.getQuality(), card.getCatagory(),
                card.getSeries(), card.isTradable(), card.getComments(), card.getImages(), card.getOwner()));}

        notifyViews();
    }

    /**
     * Identifies whether or not an inventory contains a specific card.
     * Queries the inventory, item by item, to find an equality match.
     * @param card
     * @return true if found, false if not found.
     */
    public Boolean containsCard(Card card) {
        int size = getCards().size();

        for(int i = 0; i < size; i++){

            if(getCards().get(i).equals(card)){
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;

    }

    public Card getCard(int index){
        return this.cards.get(index);
    }

    /**
     * Removes a specified card in the inventory, given that
     * the card exists. If the card doesn't exist, no errors
     * will be raised; however, no processing will be completed.
     * @param card
     */
    public void removeFromInventory(Card card){

        int size = getCards().size();

        for(int i = 0; i < size; i++){

            if(getCard(i).equals(card)){
                getCards().remove(i);
                return;
            }
        }

    }


    /**
     * Returns a specified card in an inventory;
     * used for trades where cards are isolated from
     * the inventory, but still need to be backreferenced.
     * @param card
     * @return specified card if found; null if not.
     */
    public Card returnCard(Card card){

        int size = getCards().size();

        for(int i = 0; i < size; i++){

            if(getCard(i).equals(card)){
                return getCard(i);
            }
        }

        return null;
    }


    /**
     * Uses the removeFromInventory process to
     * remove a card from the inventory.
     * @param card
     * @param amount
     */
    public void removeCard(Card card, int amount) {
        try {
            if (amount < returnCard(card).getQuantity()) {
                incrementCard(card, 0-amount);
            }
            else if (amount == returnCard(card).getQuantity()) {
                removeFromInventory(card);
            }
            else {throw new IllegalArgumentException("You tried removing more of card than the user had");
            }

            notifyViews();
        }

        catch(IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    /**
     * Given equality, cards will not be added as new entities
     * into the system; rather, they are treated as 'additional
     * entities' and appended to a card set.
     * @param card
     * @param amount
     */
    public void incrementCard(Card card, int amount){
        returnCard(card).setQuantity(returnCard(card).getQuantity() + amount);
    }
}