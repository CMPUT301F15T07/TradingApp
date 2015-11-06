package com.sherpasteven.sscte.Models;

import android.content.Context;


/**
 * Determines the current profile of the user, used for application permanence.
 */
public class CurrentProfile {
    public static Profile GetCurrentProfile(Context context){
        LocalProfileSerializer serializer = new LocalProfileSerializer();
        return serializer.Deserialize(null, context);
    }
}
