package com.sherpasteven.sscte.Models;

import android.content.Context;
import android.graphics.Bitmap;

import com.sherpasteven.sscte.R;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The user represents all data associated with the user such as trades, cards in inventory,
 * and friends.
 */
public class User extends Model {

    private String name;
    private String location;
    private String email;
    private Image profilepic;
    private ArrayList<User> friends = new ArrayList<User>();
    private Inventory inventory;
    public TradeLog trades;

    public ProfileId getProfileId() {
        return profileId;
    }

    private ProfileId profileId;

    public User(String name, String location, String email, Context context){

        this.name = name;
        this.location = location;
        this.email = email;
        this.inventory = new Inventory();
        this.trades = new TradeLog();
        setFriends(new ArrayList<User>());
        setProfilePic(new Image(R.drawable.grey_rectangle, context));
        //get id here
        profileId = new ProfileId(context);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyViews();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyViews();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;notifyViews();
    }

    public ArrayList<User> getFriends() {
        return this.friends;
    }

    public void addFriend(User user){
        this.friends.add(user);
        notifyViews();
    }


    public void removeFriend(User user){
        this.friends.remove(user);
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
        notifyViews();
    }

    /**
     * get a friend by username from the friends list
     * @param userName user name to search for
     * @return friend if found, otherwise null
     */
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
        notifyViews();
    }

    public void addPendingTrade(Trade trade){
        getTrades().addTrade(trade);
        notifyViews();
    }

    public void finalizeTrade(Trade trade){
        getTrades().tradeFinalized(trade);
        notifyViews();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
        notifyViews();
    }

    public void addInventoryItem(Card card){
        getInventory().addCard(card);
        notifyViews();
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
        notifyViews();
    }

    public void deletePastTrade(Trade trade){
        getTrades().removeFinalizedTrade(trade);
        notifyViews();
    }

    public Image getProfilePic() {
        return profilepic;
    }

    public void setProfilePic(Image profilepic) {
        this.profilepic = profilepic;
        notifyViews();
    }

    public Bitmap  constructProfilePic(){
        return getProfilePic().constructImage();
    }

}
