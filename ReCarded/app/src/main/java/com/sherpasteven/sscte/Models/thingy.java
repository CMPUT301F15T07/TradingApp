package com.sherpasteven.sscte.Models;

/**
 * Created by joshua on 23/11/15.
 */
public class thingy {
    private static thingy ourInstance = new thingy();

    public static thingy getInstance() {
        return ourInstance;
    }

    private thingy() {
    }
}
