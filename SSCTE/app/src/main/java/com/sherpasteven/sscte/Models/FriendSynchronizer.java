package com.sherpasteven.sscte.Models;

import java.util.List;

/**
 * This class is for synchronizing and updating the friends of a user
 * from an external source such as elasticsearch. It takes updated friends
 * and then synchronizes the local profile's friends.
 */
public class FriendSynchronizer {
    private Profile localProfile;
    public FriendSynchronizer(Profile localProfile){
        this.localProfile = localProfile;
    }

    /**
     * This method will update a profile's friends using
     * an updated friends source. This will replace friends
     * with the same profileId.
     * @param allProfiles updated friends
     */
    public void SynchronizeFriends(Friends allProfiles){
        User localUser = localProfile.getUser();
        List<Friend> unsynchronizedFriends = localUser.getFriends();

        Friend oldfriend;
        Friend newfriend;

        for (int i = 0; i < unsynchronizedFriends.size(); i++){
            oldfriend = unsynchronizedFriends.get(i);
            newfriend = allProfiles.get(oldfriend.getProfileId());
            if (newfriend != null){
                unsynchronizedFriends.remove(i);
                unsynchronizedFriends.add(i, newfriend);
            }
        }
    }
}
