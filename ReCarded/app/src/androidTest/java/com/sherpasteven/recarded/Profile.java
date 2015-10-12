package com.sherpasteven.recarded;

import java.util.ArrayList;

/**
 * Created by Joshua on 2015-10-12.
 */
public class Profile {

    private User user;
    private Config config;
    private ArrayList<User> friends;

    Profile(User user){
        this.user = user;
        this.config = new Config();
        this.friends = new ArrayList<User>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }
}
