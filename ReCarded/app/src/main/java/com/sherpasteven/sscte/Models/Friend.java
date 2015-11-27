package com.sherpasteven.sscte.Models;

import android.content.Context;
import android.graphics.Bitmap;

import com.sherpasteven.sscte.Controllers.Controller;

import java.util.ArrayList;

/**
 * Created by joshua on 27/11/15.
 */
public class Friend extends User{

    public Friend(User user, Context context){
        super(user.getName(), user.getLocation(), user.getEmail(), context);
        setInventory(user.getInventory());
        setProfilePic(user.getProfilePic());
        setFriends(null);
        setTrades(user.getTrades());
        setProfileID(user.getProfileId());
    }


    @Override
    public ArrayList<Friend> getFriends() {
        throw new RuntimeException("This functionality is not permissable");
    }
}
