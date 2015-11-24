package com.sherpasteven.sscte.Models;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by elias on 19/11/15.
 */
public class ProfileSynchronizer extends Model {
    private ElasticSearch elasticSearch;
    public Profiles profiles;
    private AppCompatActivity activity;

    public ProfileSynchronizer(){
    }

    public void SetActivity(AppCompatActivity activity){
        this.activity = activity;
    }


    //return a bool or something when you cant get profiles
    public boolean PullProfiles(){
        try {
            elasticSearch.searchProfiles("*", null);
            profiles = elasticSearch.getProfiles();
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    notifyViews();
                }
            });
            return true;
        } catch (RuntimeException ex){
            return false;
        }
    }

    public boolean InsertProfile(Profile profile){
        try {
            elasticSearch.InsertProfile(profile);
            return true;
        } catch (RuntimeException ex){
            return false;
        }
    }
}
