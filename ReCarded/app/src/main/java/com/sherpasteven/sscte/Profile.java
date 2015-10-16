package com.sherpasteven.sscte;

/**
 * Created by Joshua on 2015-10-12.
 */
public class Profile {

    private User user;
    private Config config;


    Profile(User user) {
        this.user = user;
        this.config = new Config();
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

}


