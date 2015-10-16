package com.sherpasteven.sscte;

import java.util.ArrayList;

/**
 * Created by Joshua on 2015-10-12.
 */
public class User {

    private String name;
    private String location;
    private ArrayList<User> friends;
    private Inventory inventory;

    public User(String name, String location){

        this.name = name;
        this.location = name;
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

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
