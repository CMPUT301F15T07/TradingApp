package com.sherpasteven.sscte.Models;

/**
 * User config to store settings and preferances
 */
public class Config extends Model {

    private Boolean imagesdownloadable;

    public Config(){

        this.imagesdownloadable = Boolean.TRUE;

    }

    public Boolean isImagesdownloadable() {
        return imagesdownloadable;
    }

    public void setImagesdownloadable(Boolean imagesdownloadable) {
        this.imagesdownloadable = imagesdownloadable;
    }
}
