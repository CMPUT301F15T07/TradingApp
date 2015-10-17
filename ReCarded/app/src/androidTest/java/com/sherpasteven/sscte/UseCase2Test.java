package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * Created by elias on 17/10/15.
 */
public class UseCase2Test extends ApplicationTestCase<Application> {
    public UseCase2Test(Class<Application> applicationClass) {
        super(applicationClass);
    }

//Search User through the search tab by Username
//it will then show a list of usernames that may match the pattern
//or it will show nothing if there is no username

/*
	Actors: Users, System
	Goal: search for other Users using username
	Trigger:  User clicks the search funtion to use it,
		and types in username
	Precondition:  User has a username they want to search
	Postcondition:  Will show list of user with similar username
					or nothing if none are similar

	Basic Flow:

		1) User logs in if not in his account
		2) user then clicks the search function
		3) user types in username
		4) A list of username that are similar will pop up, and if
		   there are no similar names will show blank list
	*/

    void testUS020101() {
        userList users = loadFromStorage();
        users.add(user1);
        users.add(user2);
        users.add(johnCena);
        assertEquals(johnCena.users.search("johnCena");
        assertEquals(user1.user2,users.search("user"));
    }

//User needs to add other user into friends list
//Can be done by searching a username

/*
	Actors: Users, Friends, System
	Goal: add user to friends list
	Trigger:  User clicks the add funtion in friend list page,
		      and types in username of user to add
	Precondition:  User has a username they want to add to list
	Postcondition:  Will show friend list with the new added user,
					if there is one
	Basic Flow:

		1) User logs in if not in his account
		2) user then clicks the add function in the friend list page
		3) user types in username
		4) A list of username that are similar will pop up, and if
		   there are no similar names will show blank list
		5) User will then click username to add that user to friend
		   list
	*/

    void testUS020201() {
        user johnCena = new user();
        user user1 = new user();
        johnCena.addFriend(user1);
        assertEquals(user1,johnCena.getFriend("user1"));
    }

//User needs to remove another user from his friends list
//Can be done by searching user in friend list and then pressing
//remove button

/*
	Actors: Users, Friends, System
	Goal: remove user to friends list
	Trigger:  User clicks the remove funtion in friend list page
	Precondition:  User has a friend they want to remove from list
	Postcondition:  Will show friend list with the user removed

	Basic Flow:

		1) User logs in if not in his account
		2) user then clicks the remove function in the friend list page
		3) user will then click friend that will be removed
*/

    void testUS020301() {
        user user1 = new user();
        user johnCena = new user();
        user user2 = new user();
        user1.addFriend(johnCena);
        user1.addFriend(user2);

        user1.remove(johnCena);
        assertEquals(NULL, user1.friends.getFriend("johnCena"));
    }

//User will  have contact information (Name) and his city
//Will be displayed in profile
//There should be option to update contact information and city

/*
	Actors: Users, Friends,  System, Profile
	Goal: Viewing own friends Profile
	Trigger:  User taps friend username on friend list screen
	Precondition:  User wishes to view friend profile
	Postcondition:  Will show screen of friend profile

	Basic Flow:

		1) User logs in if not in his account
		2) user then goes to friend list
		3) user clicks on username of fried we wishes to view
		4) Will go to friends profile
	*/

    void testUS020401() {
        user johnCena = new user("The Heavyweight Champion","WWE RAW");
        assertEquals("The Heavyweight Champion",johnCena.getName());
        assertEquals("WWE RAW", johnCena.getCity());
        johnCena.setName("John Felix Anthony Cena");
        johnCena.setCity("Tampa, Florida, U.S.");
        assertEquals("John Felix Anthony Cena",johnCena.getName());
        assertEquals("Tampa, Florida, U.S.", johnCena.getCity());
    }

//User will get friends contact information and the city
//This will be displayed on firends profile page

/*
	Actors: Users, System, Profile
	Goal: Viewing own user Profile
	Trigger:  User goes to profile screen
	Precondition:  User wishes to view/change profile
	Postcondition:  Will show user profile with updated information
					if it was changed
	Basic Flow:

		1) User logs in if not in his account
		2) user then goes to user profile
		3) If user wishes to change profile, he'll hit the edit button
		4) Profile we'll be updated with changes
	*/

    void testUS20501() {
        user user1 = new user();
        user johnCena = new user("The Heavyweight Champion","WWE RAW");
        user user2 = new user();
        user1.addFriend(johnCena);
        user1.addFriend(user2);

        assertEquals("The Heavyweight Champion",user1.getFriend("johnCena").getName);
        assertEquals("WWE RAW", user1.getFriend("johnCena").getCity);
    }


}
