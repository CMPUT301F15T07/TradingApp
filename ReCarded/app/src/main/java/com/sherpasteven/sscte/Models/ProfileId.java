package com.sherpasteven.sscte.Models;

import android.content.Context;
import android.provider.Settings;

/**
 * This class is used to uniquely identify different users.
 * FIXME: We need to decide how to implement this. Should we use strings? ints?
 * FIXME: Can we use Android api to get us a unique phone id?
 */
public class ProfileId {

    private String id;

    public ProfileId(Context context){
        //TODO(Put this in referenced sources)
        //Anthony Forloney, http://stackoverflow.com/questions/2785485/is-there-a-unique-android-device-id
        id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public String GetId(){
        return id;
    }
}
