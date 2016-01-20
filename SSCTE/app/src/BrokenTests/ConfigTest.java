package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.sherpasteven.sscte.Models.Config;
import com.sherpasteven.sscte.Models.User;

/**
 * Created by elias on 17/10/15.
 */
public class ConfigTest extends ApplicationTestCase<Application> {
    public ConfigTest(Class<Application> applicationClass) {
        super(applicationClass);
    }

    public void testSwitchDownlaodableImages(){


        //Default config downloadsimages
        Config config = new Config();

        assertTrue(config.imagesDowloadable());

        //User with a swipe switch decides not
        //to download images

        config.setImagesDownlaodable(FALSE);

        assertFalse(config.imagesDowloadable());

    }


    public void testEditProfileInfo(){


        //User creates their profile on prompt
        User user = new User("Snape", "Hogwartz");

        assertEquals("Snape", user.getName);
        assertEquals("Hogwartz", user.getLocation);

        //The they realize they forgot their name and where they were
        user.setName("Ruby Rose");
        user.setLocation("beacon");

        assertEquals("Ruby Rose", user.getName);
        assertEquals("beacon", user.getLocation);

    }
}
