package com.sherpasteven.sscte.Models;

import android.support.v7.app.AppCompatActivity;

import com.sherpasteven.sscte.Views.IView;

/**
 * Created by elias on 19/11/15.
 */
public class ProfileSynchronizer extends Model implements IView {
    private ElasticSearch elasticSearch;
    public Profiles cloudProfiles;
    private Profile localProfile;
    private AppCompatActivity activity;
    private FriendSynchronizer friendSynchronizer;

    public ProfileSynchronizer(Profile profile){
        this.localProfile = profile;
        elasticSearch = new ElasticSearch();
        friendSynchronizer = new FriendSynchronizer(profile);
        elasticSearch.addView(this);
    }

    public void SetActivity(AppCompatActivity activity){
        this.activity = activity;
    }

    public boolean SynchronizeProfile() {
        //Insert local profile into elasticsearch
        if (!InsertProfile()){
            return false;
        }
        if (!PullProfiles()){
            return false;
        }
        return true;

    }

    //return a bool or something when you cant get profiles
    private boolean PullProfiles(){
        try {
            elasticSearch.searchProfiles("*", null);
            return true;
        } catch (RuntimeException ex){
            return false;
        }
    }

    private boolean InsertProfile(){
        try {
            elasticSearch.InsertProfile(localProfile);
            return true;
        } catch (RuntimeException ex){
            return false;
        }
    }

    public void UpdateFriends(){
        try {
            friendSynchronizer.SynchronizeFriends(cloudProfiles);
        } catch (RuntimeException ex){
            throw ex;
        }
    }

    @Override
    public void Update(Object o) {
        if (o instanceof ElasticSearch){
            ElasticSearch es = (ElasticSearch) o;
            cloudProfiles = es.getProfiles();
        }
        activity.runOnUiThread(new Runnable() {
            public void run() {
                notifyViews();
            }
        });
    }
}
