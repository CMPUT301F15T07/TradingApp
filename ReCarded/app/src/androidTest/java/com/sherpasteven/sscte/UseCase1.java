package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * Created by Joshua on 2015-10-16.
 */
public class UseCase1 extends ApplicationTestCase<Application> {
    public UseCase1() {
        super(Application.class);
    }

    public void testUS010101(){
        User user = new User("Joshua", "Edmonton");
        Inventory inventory = new Inventory();

        //User case	US01.01.01
        //User adds card to their inventory

        //User decides to create a new card from their
        //inventory view.  He is prompted on the UI for
        // the following data
        String name = "Charizard";
        Integer quantity = 2;
        Quality quality = new Quality(73);
        String catagory = "Pokemon";
        String series = "series";
        boolean tradable = true;
        String comments = "Gently bent edge, 100HP";

        //The card is created after the user approves the data
        //they have entered
        Card card = new Card(name, quantity, quality, catagory, series, tradable, comments);

        assertEquals(name, card.getName());
        assertEquals(quantity, card.getQuantity());
        assertEquals(quality, card.getQuality());
        assertEquals(catagorty, card.getCatagory());
        assertEquals(tradable, Card.isTradable());
        assertEquals(comments, Card.getComments());


        //It gets added to their inventory
        Inventory inventory = new Inventory(card);
        assertEquals(inventory.size(), 1);

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


        inventory.removeCard("Charizard");
        assertEquals(inventory.size(),0);

    }

    public void testUS010401{

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
        String name = "Charizard";
        Integer quantity = 2;
        Quality quality = new Quality(73);
        String catagory = "Pokemon";
        String series = "series";
        boolean tradable = true;
        String comments = "Gently bent edge, 100HP";

        Card card = new Card(name, quantity, quality, catagory, series, tradable, comments);

        assertEquals(name, card.getName());
        assertEquals(quantity, card.getQuantity());
        assertEquals(quality, card.getQuality());
        assertEquals(catagorty, card.getCatagory());
        assertEquals(tradable, card.isTradable());
        assertEquals(comments, card.getComments());

        //Then realized that they got all the info wrong
        //From the item view UI, they can enter the edit mode and
        //Change the card info.
        String newname = "Blue Eyes White Dragon";
        Integer newquantity = 9;
        Quality newquality = new Quality(3);
        String newcatagory = "YuGiOh";
        String newseries = "stupid series";
        boolean newtradable = false;
        String newcomments = "Dime a dozen";

        card.setName(newname);
        card.setQuantity(newquanitity);
        card.setQuality(newquality);
        card.setCatagory(newcatagory);
        card.setSeries(newseries);
        card.setTradable(newtradable);
        card.setComments(newcomments);

        assertEquals(newname, card.getName());
        assertEquals(newquantity, card.getQuantity());
        assertEquals(newquality, card.getQuality());
        assertEquals(newcatagorty, card.getCatagory());
        assertEquals(newtradable, card.isTradable());
        assertEquals(newcomments, card.getComments());

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

        Card card = new Card();

        //When the user creates a card, they can choose a default catagory from the drop down menu
        ArrayList<String> releventcatagories = new ArrayList<String>("Magic The Gathering","Pokemon","YuGiOh","Digimon","Sports","Steam Trading Card",
                "Neopets", "Amiibo Cards", "Shrek Trading Cards", "MISC");
        assertEquals(releventcatagories, card.getRelevantCatagories());
    }

}
