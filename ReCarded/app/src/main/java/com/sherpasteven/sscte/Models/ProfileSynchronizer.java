package com.sherpasteven.sscte.Models;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.sherpasteven.sscte.Views.IView;

/**
 * Created by elias on 19/11/15.
 */
public class ProfileSynchronizer extends Model implements IView<Model> {
    private ElasticSearch elasticSearch;
    public Friends cloudFriends;
    private Profile localProfile;
    private AppCompatActivity activity;
    private FriendSynchronizer friendSynchronizer;
    private TradeSynchronizer tradeSynchronizer;

    public ProfileSynchronizer(Profile profile){
        this.localProfile = profile;
        elasticSearch = new ElasticSearch();
        friendSynchronizer = new FriendSynchronizer(profile);
        tradeSynchronizer = new TradeSynchronizer(profile);
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

    //return a bool or something when you cant get friends
    public boolean PullProfiles(){
        try {
            elasticSearch.searchFriends("*", null);
            return true;
        } catch (RuntimeException ex){
            return false;
        }
    }

    private boolean InsertProfile(){
        try {
            elasticSearch.InsertFriend(new Friend(localProfile.getUser(), activity.getApplicationContext()));
            return true;
        } catch (RuntimeException ex){
            return false;
        }
    }

    public void UpdateProfile() {
        UpdateFriends();
        UpdateTrades();
    }

    private void UpdateFriends(){
        try {
            friendSynchronizer.SynchronizeFriends(cloudFriends);
        } catch (RuntimeException ex){
            throw ex;
        }
    }

    private void UpdateTrades() {
        try {
            tradeSynchronizer.SynchronizeTrades();
        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    @Override
    public void Update(Model o) {
        if (o instanceof ElasticSearch){
            ElasticSearch es = (ElasticSearch) o;
            Friends friends = es.getFriends();
            friends.remove(localProfile.getProfileId());
            cloudFriends = friends;
        }
        activity.runOnUiThread(new Runnable() {
            public void run() {
                notifyViews();
            }
        });
    }
}
