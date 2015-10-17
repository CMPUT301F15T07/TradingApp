package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * Created by elias on 17/10/15.
 */
public class UseCase10Test extends ApplicationTestCase<Application> {
    public UseCase10Test(Class<Application> applicationClass) {
        super(applicationClass);
    }

    public void testUS010101(){

    //User case	US10.01.01
    // User configs the app not to download photos of cards

	/*
	Actors: User, , Config
	Goal: User wants to save phone space by not downloading photos
	Trigger:  User navitgates to config and toggles the proper toggle
	Precondition:  User wants to save space on phone
	Postcondition:  App doesnt download photos for viewing

	Basic Flow:

		1) User navigates to config/settings tabs
		2) System shows changable settings
		3) User toggles "download Photos" toggle to off
		4) The app no longer downloads photos for viewing
		5) The app deletes all offline images

	*/


        //Default config downloadsimages
        Config config = new Config();

        assertTrue(config.imagesDowloadable());

        //User with a swipe switch decides not
        //to download images

        config.setImagesDownlaodable(FALSE);

        assertFalse(config.imagesDowloadable());

    }


    public void testUS010101(){

    //User case	US10.02.01
    //User configs thier profile settings

	/*
	Actors: User, System, Config
	Goal: User wants to change profile settings
	Trigger:  User navitgates to config and selects ed profile settings
	Precondition:  User wants to change profile info
	Postcondition:  App shows new changed profile settings

	Basic Flow:

		1) User navigates to config/settings tabs
		2) System shows changable settings
		3) User selects profile settings
		4) The app changes profile settings and its viewable

	Exceptions:

		4.1) Other users have downloaded friends profiles for off line viewing
		4.1) The system updates offline info as soon as the other users are online.

	*/

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
