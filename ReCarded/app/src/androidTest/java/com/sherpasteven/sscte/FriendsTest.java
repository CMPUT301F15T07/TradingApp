package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.sherpasteven.sscte.Models.User;

/**
 * Created by elias on 17/10/15.
 */
public class FriendsTest extends ApplicationTestCase<Application> {
	public FriendsTest() {
		super(Application.class);
	}


    public void testAddFriend() {
        //User johnCena = new User("WWE","Salt lake City","theChampion@yahoo.com");
        //User user1 = new User("User1", "City1","email@myspace.gog");
        //johnCena.addFriend(user1);
        //assertEquals(user1, johnCena.getFriend("User1"));
    }



    public void testRemoveFriend() {
        User user1 = new User("user1","city1","email1@myspace.gog");
        User johnCena = new User("johnCena","WWE","theChampion@yahoo.com");
        User user2 = new User("user2","city2","email2@myspace.gog");
        user1.addFriend(johnCena);
        user1.addFriend(user2);

        user1.removeFriend(johnCena);
        assertEquals(null, user1.getFriend("johnCena"));
    }


    public void testProfileData() {
        User johnCena = new User("The Heavyweight Champion","WWE RAW","theChampion@yahoo.com");
        assertEquals("The Heavyweight Champion",johnCena.getName());
        assertEquals("WWE RAW", johnCena.getLocation());
        johnCena.setName("John Felix Anthony Cena");
        johnCena.setLocation("Tampa, Florida, U.S.");
        assertEquals("John Felix Anthony Cena",johnCena.getName());
        assertEquals("Tampa, Florida, U.S.", johnCena.getLocation());
    }



    public void testViewOtherProfiles() {
        User user1 = new User("user1","city1","email1@myspace.gog");
        User johnCena = new User("The Heavyweight Champion","WWE RAW","theChampion@yahoo.com");
        User user2 = new User("user2","city2","email2@myspace.gog");
        user1.addFriend(johnCena);
        user1.addFriend(user2);

		assertEquals(johnCena, user1.getFriend("The Heavyweight Champion"));
        assertTrue(user1.getFriend("The Heavyweight Champion").getLocation().equals("WWE RAW"));
    }


}
