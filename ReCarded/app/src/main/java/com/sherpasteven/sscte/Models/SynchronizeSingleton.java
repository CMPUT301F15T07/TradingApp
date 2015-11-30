package com.sherpasteven.sscte.Models;

import android.support.v7.app.AppCompatActivity;

/**
 * This class is used to manage the ProfileSynchronizer singleton.
 * We don't want to have multiple instances of the synchronizer since
 * we dont want to have multiple background threads pulling from
 * elasticsearch at the same time.
 */
public class SynchronizeSingleton {
    private static ProfileSynchronizer instance;

    public static ProfileSynchronizer GetSynchronize(AppCompatActivity activity){
        if (instance == null){
            instance = new ProfileSynchronizer(CurrentProfile.getCurrentProfile().getProfile(activity));
        }
        instance.SetActivity(activity);
        return instance;
    }
}
