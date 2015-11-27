package com.sherpasteven.sscte.Models;

import java.util.ArrayList;

/**
 * Created by elias on 16/11/15.
 */
public class Friends extends Model{
    private ArrayList<Friend> friends;

    public Friends(){
        friends = new ArrayList<>();
    }

    public void add(Friend friend){
        friends.add(friend);
        notifyViews();

    }

    public Friend get(ProfileId profileId){
        for (Friend friend : friends){
            if (profileId.equals(friend.getProfileId())){
                return friend;
            }
        }
        return null;
    }
}
