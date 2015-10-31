package com.sherpasteven.sscte.Models;

import android.content.Context;

/**
 * Created by jake on 2015-10-31.
 */
public class CurrentProfile {
    public static Profile GetCurrentProfile(Context context){
        LocalProfileSerializer serializer = new LocalProfileSerializer();
        return serializer.Deserialize(null, context);
    }
}
