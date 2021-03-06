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
    private int rating;
    private Image profilepic;
    private ArrayList<Friend> friends = new ArrayList<Friend>();
    private Inventory inventory;
    public TradeLog trades;

    private ProfileId profileId;

    /**
     * Used for initializing a new user
     * @param name
     * @param location
     * @param email
     * @param context
     */
    public User(String name, String location, String email, Context context){

        this.name = name;
        this.location = location;
        this.email = email;
        this.inventory = new Inventory();
        this.trades = new TradeLog();
        rating = 0;
        setFriends(new ArrayList<Friend>());
        setProfilePic(new Image(R.drawable.splash_page, context));
        //get id here
        profileId = new ProfileId(context);
    }

    /**
     * used for the subclasses of user where id and profile pic are already exitant
     * @param name
     * @param location
     * @param email
     * @param profilepic
     * @param id
     */
    public User(String name, String location, String email, Image profilepic, ProfileId id, Integer rating){

        this.name = name;
        this.location = location;
        this.email = email;
        this.inventory = new Inventory();
        this.trades = new TradeLog();
        this.rating = rating;
        setFriends(new ArrayList<Friend>());
        setProfilePic(profilepic);
        //get id here
        profileId = id;
    }

    public Integer getRating() {
        return rating;
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

    public ArrayList<Friend> getFriends() {
        return this.friends;
    }

    public void addFriend(Friend friend){
        this.friends.add(friend);
        notifyViews();
    }


    /**
     * Removes a friend from a users friend list
     * @param friend
     */
    public void removeFriend(Friend friend){
        for(Friend f:friends){
            if(f.getName().equals(friend.getName()) && f.getLocation().equals(friend.getLocation()) && f.getEmail().equals(friend.getEmail())){
                this.friends.remove(f);
            }
        }
        notifyViews();
    }

    public void setFriends(ArrayList<Friend> friends) {
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

    /**
     * Adds a pending trade that the user is a part of
     * @param trade
     */
    public void addPendingTrade(Trade trade){
        getTrades().addTrade(trade);
        notifyViews();
    }

    /**
     * Finalizes the the trade given
     * @param trade
     */
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

    /**
     * Tells if the inventory contains the card, bypassing the quantity attribute
     * @param card
     * @return boolean
     */
    public Boolean hasInventoryItem(Card card){
        return getInventory().containsCard(card);
    }

    /**
     * returns a card matching the given card from the users inventory, bypassing the quantity attribute
     * @param card
     * @return card
     */
    public Card returnInventoryItem(Card card){
        return getInventory().returnCard(card);
    }

    /**
     * returns the card at that index in the users invnetory
     * @param index
     * @return card
     */
    public Card getInventoryItem(int index){
        return getInventory().getCard(index);
    }

    /**
     * removes the given card in amount from the users inventory
     * @param card
     * @param amount
     */
    public void removeInventoryItem(Card card, int amount){
        getInventory().removeCard(card, amount);
        notifyViews();
    }

    /**
     * removes the given finzalized trade
     * @param trade
     */
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

    public ProfileId getProfileId() {
        return profileId;
    }

    public void setProfileID(ProfileId id){
        this.profileId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        final User other = (User) o;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.location == null) ? (other.location != null) : !this.location.equals(other.location)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }


    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void incrementRating() {
        rating++;
    }


    public String getOwnerInfo() {
        return(getEmail() + "," + getName() + "," + getLocation());
    }
}
