package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.User;


public class InventoryTest extends ApplicationTestCase<Application> {
    public InventoryTest() {
        super(Application.class);
    }

    String name = "Charizard";
    int quantity;
    Quality quality;
    String catagory;
    String series;
    boolean tradable;
    String comments;


    Card card;

    public User inventorySetUp(){

        User user = new User("Joshua", "Edmonton", "DummyEmail@ualberta.ca");

        String name = "Charizard";
        int quantity = 2;
        Quality quality = new Quality(73);
        String catagory = "Pokemon";
        String series = "Basic XY Red";
        boolean tradable = true;
        String comments = "Gently bent edge, 100HP";

        
        Card card = new Card(name, quantity, quality, catagory, series, tradable, comments, user);
        user.addInventoryItem(card);

        return user;

    }

    
    public void testAddRemove(){

        User user = inventorySetUp();
        

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
        catch(IllegalArgumentException e){
            assertEquals("You tried removing more of card than the user had" , e.getMessage());
        }
    }




    public void testEditItem(){

        
        User user = inventorySetUp();

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


    public void testAvailableCatagories(){

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
