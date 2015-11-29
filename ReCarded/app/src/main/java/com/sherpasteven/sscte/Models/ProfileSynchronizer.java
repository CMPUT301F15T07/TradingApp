package com.sherpasteven.sscte.Models;

import android.support.v7.app.AppCompatActivity;

import com.sherpasteven.sscte.Views.IView;

/**
 * This class is the high level implementation of
 * synchronizing the local profile with the cloud
 * friends. This involves pulling friends and pushing
 * the profile using elasticsearch. This class is also
 * responsible for synchronizing the friends and trades
 * after the new data is pulled.
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

    /**
     * This will insert the local profile into elastic
     * search and then pull the friends from elastic search.
     * PullFriends is async so it will call Update() once
     * the search results are in and the attribute
     * "cloudFriends" will be populated
     */
    public boolean SynchronizeProfile() {
        if (!InsertProfile()){
            return false;
        }
        if (!PullFriends()){
            return false;
        }
        return true;

    }

    /**
     * Pull friends from elasticsearch asynchronously.
     * This is done by pulling ALL friends from elasticsearch
     * and then we will filter the results later. Update() will
     * be called once the pull is done and "cloudFriends" is
     * populated with the updated friends.
     * @return success
     */
    public boolean PullFriends(){
        try {
            elasticSearch.searchFriends("*", null);
            return true;
        } catch (RuntimeException ex){
            return false;
        }
    }

    /**
     * Insert the local profile into elasticsearch. This
     * will create the user if one doesn't already exist in
     * elasticsearch with the same profileId, otherwise it
     * will overwrite the old one.
     * @return success
     */
    private boolean InsertProfile(){
        try {
            elasticSearch.InsertFriend(new Friend(localProfile.getUser(), activity.getApplicationContext()));
            return true;
        } catch (RuntimeException ex){
            return false;
        }
    }

    /**
     * This will synchronize the local profiles
     * friends and trades by using the new
     * friends that were pulled from elasticsearch
     */
    public void UpdateProfile() {
        UpdateFriends();
        UpdateTrades();
    }

    /**
     * This will update the user's friends
     * using the pulled friends from elasticsearch
     */
    private void UpdateFriends(){
        try {
            friendSynchronizer.SynchronizeFriends(cloudFriends);
        } catch (RuntimeException ex){
            throw ex;
        }
    }

    /**
     * This will update the user's trades
     * using the pulled friends from elasticsearch
     */
    private void UpdateTrades() {
        try {
            tradeSynchronizer.SynchronizeTrades();
        } catch (RuntimeException ex) {
            throw ex;
        }
    }

    /**
     * This is called once elasticsearch
     * is done pulling the friends. We will
     * update any listeners of this class
     * once we update the cloudFriends
     * @param o
     */
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
