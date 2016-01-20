package com.sherpasteven.sscte.Models;

/**
 * User config to store settings and preferances
 */
public class Config extends Model {

    private Boolean imagesdownloadable;

    public Config(){

        this.imagesdownloadable = Boolean.TRUE;

    }

    public Boolean getImagesDownloadable() {
        return imagesdownloadable;
    }

    public void setImagesDownloadable(Boolean imagesdownloadable) {
        this.imagesdownloadable = imagesdownloadable;
    }
}
