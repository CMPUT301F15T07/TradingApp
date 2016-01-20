package com.sherpasteven.sscte.Models;

/**
 * This class represents a card's physical quality. There are different
 * ranking systems (0-10) or (poor-very good) so we abstract out the implementation of quality.
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
