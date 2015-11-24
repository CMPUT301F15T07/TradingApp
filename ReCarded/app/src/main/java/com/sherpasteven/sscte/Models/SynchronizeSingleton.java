package com.sherpasteven.sscte.Models;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by elias on 19/11/15.
 */
public class SynchronizeSingleton {
    private static ProfileSynchronizer instance;

    public static ProfileSynchronizer GetSynchronize(AppCompatActivity activity){
        if (instance == null){
            instance = new ProfileSynchronizer();
        }
        instance.SetActivity(activity);
        return instance;
    }
}
