package com.sherpasteven.recarded;

/**
 * Created by Joshua on 2015-10-12.
 */
public class Config {

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
