package com.sherpasteven.sscte.Models;

/**
 * Created by Joshua on 2015-10-12.
 */
public class Config extends Model {

    private Boolean imagesdownloadable;

    public Config(){

        this.imagesdownloadable = Boolean.TRUE;

    }

    public Boolean getImagesdownloadable() {
        return imagesdownloadable;
    }

    public void setImagesdownloadable(Boolean imagesdownloadable) {
        this.imagesdownloadable = imagesdownloadable;
    }
}
