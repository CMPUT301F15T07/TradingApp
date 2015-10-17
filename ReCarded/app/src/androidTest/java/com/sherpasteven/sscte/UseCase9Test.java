package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * Created by elias on 17/10/15.
 */
public class UseCase9Test extends ApplicationTestCase<Application> {
    public UseCase9Test(Class<Application> applicationClass) {
        super(applicationClass);
    }

    //************************** OFFLINE BEHAVIOURS **************************
//User Case US09.01.01
    public void testUS090101{
//User wants to make changes to inventory while offline and have changes
//save to server automatically when connection is restored

	/*
	Actors: User, Cards, Inventory, System
	Goal: Make changes to user inventory while offline
	Trigger:  User edits inventory items identically to the online procedure.
	Precondition:  User is offline. User has an item they want to edit.
	Postcondition:  Edited item is viewable once connection has been re-established.

	Basic Flow:

		1) User clicks add button from inventory view
		2) View appears with empty fields to enter info about the card
		3) User enters the info they have about the card (and possibly multiple photos)
		4) User reviews info and clicks "accept"
		5) The system creates a new card with the info provided locally
		6) The system adds the new card to the Inventory associated
			with the user locally
		7) The system re-establishes connection to server
		8) The system updates user profile to the server
		9) The card is viewable to the user from the inventory view
			now and is also viewable (if the user made it viewable)
			to friends.
	*/

        //System cannot contact server
        Boolean connection_established = Boolean.FALSE;

        //User creates new item as per User case US01.01.01/US01.05.01
        User user1 = new User();
        user1.getInventory().add(new Card());
        Profile profile = user1.getProfile();

        //System saves changes locally
        profile.saveToLocal("users_profile");

        //User re-establishes connection with server
        connection_established = Boolean.TRUE;

        //System recognizes connection and saves changes to server
        profile.saveToExternal("users_profile");

        Profile profile2 = loadFromExternal("users_profile");
        assertEquals(profile, profile2);
    }

    //User Case US09.02.01
    public void testUS090201{
//User wants to propose trades offline, once connectivity reseumes the trades will be established

	/*
	Actors: User, Inventory, System, friend
	Goal: propose trade to friend while offline and have it appear when connection is re-established
	Trigger:  User clicks the new trade button while offline.
	Precondition:  User has a trade they wish to make and is offline
	Postcondition:  New trade is viewable by user and friend when connection is re-established

	Basic Flow:

		1) User clicks trade button on their friends inventory
		2) View appears with empty fields to enter about the trade
		3) User enters the info they wish to propose in the trade
		4) User reviews info and clicks "accept"
		5) The system creates a new trade locally with the info provided
		6) The system updates the server with the trade when connection is re-established
		7) The friend is notified that a trade has been proposed.
		8) the user and the friend can see the trade
	*/

        //System cannot contact server
        Boolean connection_established = Boolean.FALSE;

        //User proposes trade while offline
        User user1 = new User();
        User user2 = new User();
        Profile profile = user1.getProfile();
        Trade trade = new Trade(user1,user2);
        Boolean trade_success = trade.send();
        assertFalse(trade_success);

        //System saves changes locally
        profile.saveToLocal();

        //User re-establishes connection with server
        connection_established = Boolean.TRUE;

        //System recognizes connection and attempts to resend trade
        profile.sendAllTradesInQueue();

        //User 1 sees the newly proposed trade
        assertTrue(user1.hasTrade(trade.id()));

        //User 2 sees the newly proposed trade
        assertTrue(user2.hasTrade(trade.id()));
    }

    //User Case US09.03.01
    public void testUS090301{
//User opens up friends inventories that has been previously opened while offline

	/*
	Actors: User, Inventory, System, friend
	Goal: while offline, user can view friends inventories that have previously been viewed
	Trigger:  User clicks on friends ivnentory while offline
	Precondition:  User is offline
	Postcondition:  user can view friends inventory while offline.

	Basic Flow:

		1) User clicks to view frieneds inventory
		2) System saves a copy of friends profile locally
		3) User loses connection with server
		4) User clicks to view friends profile again
		5) The system retrieves cached version of users profile
		6) User can browse friends profile.
	*/

        //User browses friends inventory as per User Case US03.01.01
        User user1 = new User();
        User user2 = new User();
        Inventory friends_inventory = user2.getInventory();

        //System saves copy of inventory locally
        user1.getProfile().saveToCache(user2.getName(), friends_inventory);

        //System cannot contact server
        Boolean connection_established = Boolean.FALSE;

        //User browses friends inventory while offline
        Inventory friends_cached_inventory = loadFromCache(user2.getName());

        assertEquals(friends_inventory, friends_cached_inventory);
    }
}
