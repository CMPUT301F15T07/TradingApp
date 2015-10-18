package com.sherpasteven.sscte.Models;

/**
 * Created by Joshua on 2015-10-12.
 */
public class Quality extends Model {

    private Integer quality;

    public Quality(Integer quality){

        this.quality = quality;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }
}
