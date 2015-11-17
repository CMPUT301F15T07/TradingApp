package com.sherpasteven.sscte.Models;

import java.util.Date;

/**
 * Created by elias on 16/11/15.
 */
public class LastModified {

    private ProfileId profileId;
    private Date lastModified;

    public LastModified(ProfileId profileId, Date lastModified){
        this.profileId = profileId;
        this.lastModified = lastModified;
    }

    public ProfileId getProfileId() {
        return profileId;
    }

    public Date getLastModified() {
        return lastModified;
    }

    /**
     * Set profile modified date to now
     */
    public void SetModifiedDate(){
        lastModified = new Date();
    }
}
