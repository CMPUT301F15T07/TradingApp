package com.sherpasteven.sscte.Models;

import java.util.List;

/**
 * This class is for synchronizing and updating the friends of a user
 * from an external source such as elasticsearch.
 */
public class FriendSynchronizer {
    private Profile localProfile;
    public FriendSynchronizer(Profile localProfile){
        this.localProfile = localProfile;
    }

    /**
     * This method will update a profile's friends using
     * an updated friends source
     * @param allProfiles updated profiles to pull from
     */
    public void SynchronizeFriends(Profiles allProfiles){
        User localUser = localProfile.getUser();
        List<User> unsynchronizedFriends = localUser.getFriends();
        User oldfriend;
        Profile newfriend;
        for (int i = 0; i < unsynchronizedFriends.size(); i++){
            oldfriend = unsynchronizedFriends.get(i);
            newfriend = allProfiles.get(oldfriend.getProfileId());
            if (newfriend != null){
                unsynchronizedFriends.remove(i);
                unsynchronizedFriends.add(i, newfriend.getUser());
            }
        }
    }
}
