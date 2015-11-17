package com.sherpasteven.sscte.Models;

import java.util.ArrayList;

/**
 * Created by elias on 16/11/15.
 */
public class Profiles extends Model{
    private ArrayList<Profile> profiles;

    public Profiles(){
        profiles = new ArrayList<>();
    }

    public void add(Profile profile){
        profiles.add(profile);
        notifyViews();
    }

    public Profile get(ProfileId profileId){
        for (Profile profile : profiles){
            if (profileId.equals(profile.getProfileId())){
                return profile;
            }
        }
    }
}
