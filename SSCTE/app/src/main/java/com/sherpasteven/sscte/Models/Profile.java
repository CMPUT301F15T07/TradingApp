package com.sherpasteven.sscte.Models;

import android.graphics.PointF;

/**
 * This class represents all of the data and configuration settings for a certain User.
 * The User class represents the data associated with the user such as trades and cards.
 * The Config class represents the configuration associated with the user such as if
 * images should be downloaded.
 */
public class Profile extends Model {

    private User user;
    private Config config;
    private ProfileId profileId;

    public ProfileId getProfileId() {
        return profileId;
    }

    Profile(User user) {
        this.user = user;
        this.config = new Config();
        this.profileId = user.getProfileId();
    }

    public User getUser() {
        if (user != null) {
            return user;
        } else {
            return null;
        }
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

    @Override
    public boolean equals(Object other) {
        if (other instanceof Profile) {
            Profile otherProfile = (Profile) other;
            return getProfileId().equals(((Profile) other).getProfileId());
        } else {
            return false;
        }
    }

}


