package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.User;

/**
 * Created by Joshua on 2015-10-16.
 */
public class UseCase1Test extends ApplicationTestCase<Application> {
    public UseCase1Test() {
        super(Application.class);
    }

    public void testUS010101(){
        User user = new User("Joshua", "Edmonton", "DummyEmail@ualberta.ca");


        //User case	US01.01.01
        //User adds card to their inventory

        //User decides to create a new card from their
        //inventory view.  He is prompted on the UI for
        // the following data
        String name = "Charizard";
        int quantity = 2;
        Quality quality = new Quality(73);
        String catagory = "Pokemon";
        String series = "Basic XY Red";
        boolean tradable = true;
        String comments = "Gently bent edge, 100HP";

        //The card is created after the user approves the data
        //they have entered
        Card card = new Card(name, quantity, quality, catagory, series, tradable, comments, user);
        user.addInventoryItem(card);

        assertTrue(user.hasInventoryItem(card));

        assertEquals(name, user.getInventoryItem(0).getName());
        assertTrue(quantity == user.getInventoryItem(0).getQuantity());
        assertEquals(quality, user.getInventoryItem(0).getQuality());
        assertEquals(catagory, user.getInventoryItem(0).getCatagory());
        assertTrue(user.getInventoryItem(0).isTradable());
        assertEquals(comments, user.getInventoryItem(0).getComments());
        assertEquals(user.getInventoryItem(0).getOwner(), user);
        assertTrue(user.getInventoryItem(0).equals(card));

        Card newcard = new Card(name, 5, quality, catagory, series, tradable, comments, user);


        assertTrue(card.equals(newcard));



        //US01.05.01
        //User wants to delete a card from inventory

	/*
	Actors: User, Cards, Inventory, System
	Goal: Removes Cards from user inventory
	Trigger:  User clicks the card to view it,
		clicks edit and then remove card
	Precondition:  User has a card they want to remove
	Postcondition:  New card is no longer viewable by the user
		and their friends

	Basic Flow:

		1) User clicks the card to view it, clicks edit and then remove card
		2) The system confims revoval of card with a prompt
		3) The user confirms
		4) The card is no long viewable to the user from the inventory view
			now and is no longer viewable to friends

	Exceptions:

		4.1) Other users have downloaded cards offline
		4.2) Those cards are removed as soon as the other user is online
	*/


        user.removeInventoryItem(card, 1);
        assertEquals(user.returnInventoryItem(card).getQuantity(), 1);
        user.removeInventoryItem(card, 1);
        assertFalse(user.hasInventoryItem(card));

        user.addInventoryItem(card);
        user.removeInventoryItem(card, 2);
        assertFalse(user.hasInventoryItem(card));

        try {
            user.addInventoryItem(card);
            user.removeInventoryItem(card, 3);
        }
        catch(IllegalArgumentException e)
        {assertEquals("You tried removing more of card than the user had" , e.getMessage());}






    }



    public void testUS010401(){

        //User case	US01.04.01
        //User edits card info

	/*
	Actors: User, Cards, Inventory, System
	Goal: Edit the info on a users card
	Trigger:  User clicks the add button from
		the inventory view screen
	Precondition:  User has a card they want to add
	Postcondition:  New card is viewable by the user
		and their friends

	Basic Flow:

		1) User clicks the card to view it, clicks edit
		1.1) The system retrieves the card info
		2) The view of the card is now editable with editable text fields and a viewable toggle
		3) User changes the info they have about the card (and possibly multiple photos)
		4) User reviews info and clicks "accept"
		5) The system edits the info of the card the user selected
		6) The system replaces the edited card to the Inventory associated
			with the user
		7) The edited card is viewable to the user from the inventory view
			now and is also viewable (if the user made it viewable)
			friends.

		Exceptions:

		7.1) Other users have downloaded cards offline
		7.2) Card info is updated as soon as teh other users are online
	*/



        //The user creates a card
        User user = new User("Joshua", "Edmonton", "DummyEmail@ualberta.ca");


        //User case	US01.01.01
        //User adds card to their inventory

        //User decides to create a new card from their
        //inventory view.  He is prompted on the UI for
        // the following data
        String name = "Charizard";
        int quantity = 2;
        Quality quality = new Quality(73);
        String catagory = "Pokemon";
        String series = "Basic XY Red";
        boolean tradable = true;
        String comments = "Gently bent edge, 100HP";

        //The card is created after the user approves the data
        //they have entered
        Card card = new Card(name, quantity, quality, catagory, series, tradable, comments, user);
        user.addInventoryItem(card);

        assertTrue(user.hasInventoryItem(card));

        assertEquals(name, user.getInventoryItem(0).getName());
        assertTrue(quantity == user.getInventoryItem(0).getQuantity());
        assertEquals(quality, user.getInventoryItem(0).getQuality());
        assertEquals(catagory,  user.getInventoryItem(0).getCatagory());
        assertTrue(user.getInventoryItem(0).isTradable());
        assertEquals(comments,  user.getInventoryItem(0).getComments());
        assertEquals(user.getInventoryItem(0).getOwner(), user);

        //Then realized that they got all the info wrong
        //From the item view UI, they can enter the edit mode and
        //Change the card info.
        String newname = "Blue Eyes White Dragon";
        int newquantity = 9;
        Quality newquality = new Quality(3);
        String newcatagory = "YuGiOh";
        String newseries = "stupid series";
        boolean newtradable = false;
        String newcomments = "Dime a dozen";

        user.getInventoryItem(0).setName(newname);
        user.getInventoryItem(0).setQuantity(newquantity);
        user.getInventoryItem(0).setQuality(newquality);
        user.getInventoryItem(0).setCatagory(newcatagory);
        user.getInventoryItem(0).setSeries(newseries);
        user.getInventoryItem(0).setTradable(newtradable);
        user.getInventoryItem(0).setComments(newcomments);

        assertEquals(newname, user.getInventoryItem(0).getName());
        assertTrue(newquantity == user.getInventoryItem(0).getQuantity());
        assertEquals(newquality, user.getInventoryItem(0).getQuality());
        assertEquals(newcatagory, user.getInventoryItem(0).getCatagory());
        assertTrue(newtradable == user.getInventoryItem(0).isTradable());
        assertEquals(newcomments, user.getInventoryItem(0).getComments());


    }



    public void testUS010601(){

        //user case US01.06.01
        //The user has the ability to choose from
        //preset catagories of cards upon creation and editing

	/*
	Actors: User, Cards, Inventory, System
	Goal: Upon creation, there are preset catagories to choose from
	Trigger:  User clicks the add button from
		the inventory view screen/or edit button from card view
	Precondition:  User has a card they want to add/edit
	Postcondition:  New card is viewable by the user
		and their friends

	Basic Flow:

		1) User clicks the card to view it, clicks edit/or add card from inventory view
		1.1) The system retrieves the card info/prompts for new card info
		2) The view of the card is now editable/the user can enter info for a new card
		3) User changes catagory/enters catagory with a pre-determined drop down menue of
			specific card catagories
		4) User reviews info and clicks "accept"
		5) The system edits the info of the card the user selected/creates new card with entered info
		6) The system replaces the edited card to the Inventory associated
			with the user/adds card to the inventory
		7) The edited/new card is viewable to the user from the inventory view
			now and is also viewable (if the user made it viewable)
			friends.

		Exceptions:

		7.1) Other users have downloaded cards offline
		7.2) Card info is updated as soon as teh other users are online
	*/


        //When the user creates a card, they can choose a default catagory from the drop down menu
        String theserelevantcatagories[] = {"Magic The Gathering","Pokemon","YuGiOh","Digimon","Sports","Steam Trading Card",
                "Neopets", "Amiibo Cards", "Shrek Trading Cards", "MISC"};

        String relevantcatagories[] = Card.getRelevantCatagories();

        assertEquals(theserelevantcatagories[0], relevantcatagories[0]);
        assertEquals(theserelevantcatagories[1], relevantcatagories[1]);
        assertEquals(theserelevantcatagories[2], relevantcatagories[2]);
        assertEquals(theserelevantcatagories[3], relevantcatagories[3]);
        assertEquals(theserelevantcatagories[4], relevantcatagories[4]);
        assertEquals(theserelevantcatagories[5], relevantcatagories[5]);
        assertEquals(theserelevantcatagories[6], relevantcatagories[6]);
        assertEquals(theserelevantcatagories[7], relevantcatagories[7]);
        assertEquals(theserelevantcatagories[8], relevantcatagories[8]);
        assertEquals(theserelevantcatagories[9], relevantcatagories[9]);



    }



}
