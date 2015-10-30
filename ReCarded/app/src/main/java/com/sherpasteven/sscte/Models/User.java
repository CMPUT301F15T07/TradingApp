package com.sherpasteven.sscte.Models;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Joshua on 2015-10-12.
 */
public class User extends Model {

    private String name;
    private String location;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    private ArrayList<User> friends;
    private Inventory inventory;
    public TradeLog trades;

    public User(String name, String location, String email){

        this.name = name;
        this.location = location;
        this.email = email;
        this.inventory = new Inventory();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void addFriend(User user){ friends.add(user);}

    public void removeFriend(User user){
        friends.remove(user);
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public User getFriend(String userName){
        for(Iterator i = friends.iterator(); i.hasNext();){
            User currentFriend = (User) i.next();
            if(currentFriend.getName().equals(userName)){
                return currentFriend;
            }
        }
        return null;
    }

    public TradeLog getTrades() {
        return trades;
    }

    public void setTrades(TradeLog trades) {
        this.trades = trades;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addInventoryItem(Card card){
        getInventory().addCard(card);
    }

    public Boolean hasInventoryItem(Card card){
        return getInventory().containsCard(card);
    }

    public Card returnInventoryItem(Card card){
        return getInventory().returnCard(card);
    }

    public Card getInventoryItem(int index){
        return getInventory().getCard(index);
    }

    public void removeInventoryItem(Card card, int amount){
        getInventory().removeCard(card, amount);
    }

}
