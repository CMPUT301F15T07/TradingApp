package com.sherpasteven.sscte.Models;

import android.content.Context;



/**
 * Determines the current profile of the user, used for application permanence.
 */
public class CurrentProfile {

    private static CurrentProfile currentprofile = new CurrentProfile();
    private Profile profile = null;


    public static CurrentProfile getCurrentProfile(){
        return currentprofile;
    }

    private CurrentProfile(){

    }

    public Profile getProfile(Context context){
        if(profile == null) {
            LocalProfileSerializer serializer = new LocalProfileSerializer();
            this.profile = serializer.Deserialize(null, context);
            return this.profile;
        }
        else{
            return this.profile;
        }
    }
}
