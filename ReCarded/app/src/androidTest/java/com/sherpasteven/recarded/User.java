package com.sherpasteven.recarded;

/**
 * Created by Joshua on 2015-10-12.
 */
public class User {

    private String name;
    private String location;

    public User(String name, String location){

        this.name = name;
        this.location = name;
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
}
