package com.sherpasteven.sscte.Models;

import android.content.Context;
import android.graphics.Bitmap;

import com.sherpasteven.sscte.Controllers.Controller;

import java.util.ArrayList;

/**
 * Created by joshua on 27/11/15.
 */
public class Friend extends Model{

    private String name;
    private String location;
    private String email;
    private Image profilepic;
    private Inventory inventory;

    private ProfileId profileId;

    public Friend(User user, Context context){

        setProfilePic(user.getProfilePic());
        setEmail(user.getEmail());
        setInventory(user.getInventory());
        setLocation(user.getLocation());
        setName(user.getName());
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
        this.location = location;
        notifyViews();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
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

    public Image getProfilePic() {
        return profilepic;
    }

    public void setProfilePic(Image profilepic) {
        this.profilepic = profilepic;
        notifyViews();
    }

    public Bitmap constructProfilePic(){
        return getProfilePic().constructImage();
    }


    public ProfileId getProfileId() {
        return profileId;
    }
}
