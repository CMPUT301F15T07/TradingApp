package com.sherpasteven.sscte.Models;

import android.content.Context;
import android.graphics.Bitmap;

import com.sherpasteven.sscte.Controllers.Controller;

import java.util.ArrayList;

/**
 * Friend extends User, and retains critical values that are required for the
 * application to understand other 'users', without resulting in critical failures.
 * Reduced to serve ElasticSearch properties.
 */
public class Friend extends User{

    public Friend(User user, Context context){
        super(user.getName(), user.getLocation(), user.getEmail(), user.getProfilePic(), user.getProfileId(), user.getRating());
        setInventory(user.getInventory());
        setFriends(null);
        setTrades(user.getTrades());
        setRating(user.getRating());
    }

    /**
     * Overrides the User model with a RuntimeException.
     * Developed in order to notify users that the friends' friends is called,
     * which cannot be completed in the application (and is designed so).
     */
    @Override
    public ArrayList<Friend> getFriends() {
        throw new RuntimeException("This functionality is not permissible");
    }
}
