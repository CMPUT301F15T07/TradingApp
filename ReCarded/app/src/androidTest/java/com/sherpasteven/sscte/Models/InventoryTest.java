package com.sherpasteven.sscte.Models;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

/**
 * Created by joshua on 29/11/15.
 */
public class InventoryTest extends ActivityInstrumentationTestCase2 {

    public InventoryTest(){
        super(com.sherpasteven.sscte.InventoryActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    private Inventory inventory;


    private Card card1;
    private Card card2;
    private Card card3;

    private User user;

    public void setUp() throws Exception {
        super.setUp();
        user = new User("Josh", "Edmonton", "jjwhite@ualberta.ca", getActivity());

        String name1 = "Charizard";
        Quality quality1 = new Quality(50);
        String catagory1 = "MTG";
        String series1 = "Cool series";
        Boolean tradeable1 = Boolean.TRUE;

        String name2 = "Pikachu";
        Quality quality2 = new Quality(3);
        String catagory2 = "Pokemon";

        int quant1 = 3;
        int quant2 = 1;

        String comments = "All the same";

        inventory = user.getInventory();


        card1 = new Card(name1,quant1, quality1, catagory1, series1, tradeable1,comments, user );
        card2 = new Card(name1,quant2, quality1, catagory1, series1, tradeable1,comments, user );
        card3 = new Card(name2, quant2, quality2, catagory2, series1, tradeable1,comments, user );
    }

    public void tearDown() throws Exception {

    }

    public void testAddCard() throws Exception {
        user.addInventoryItem(card1);

        assertEquals(card1.getName(), user.returnInventoryItem(card1).getName());
        assertEquals(card1.getQuantity(), user.returnInventoryItem(card1).getQuantity());
        assertEquals(card1.getQuality(), user.returnInventoryItem(card1).getQuality());
        assertEquals(card1.getSeries(), user.returnInventoryItem(card1).getSeries());
        assertEquals(card1.getComments(), user.returnInventoryItem(card1).getComments());
        assertEquals(card1.getOwner() , user.returnInventoryItem(card1).getOwner());

        assertTrue(card1.equals(user.getInventoryItem(0)));
        assertEquals(user.getInventory().getCards().size(), 1);

        user.addInventoryItem(card2);
        assertEquals(user.getInventory().getCards().size(), 1);

        user.addInventoryItem(card3);
        assertEquals(user.getInventory().getCards().size(), 2);

    }

    public void testContainsCard() throws Exception {
        user.addInventoryItem(card1);
        assertTrue(user.hasInventoryItem(card1));
        assertTrue(user.hasInventoryItem(card2));
        assertFalse(user.hasInventoryItem(card3));

    }

    public void testRemoveCard() throws Exception {
        user.addInventoryItem(card1);

        assertEquals(card1, user.returnInventoryItem(card1));

        user.removeInventoryItem(card1, 1);
        assertEquals(user.returnInventoryItem(card1).getQuantity(), 2);

        user.removeInventoryItem(card1, 1);
        assertEquals(user.returnInventoryItem(card1).getQuantity(), 1);

        user.removeInventoryItem(card1, 1);
        assertFalse(user.hasInventoryItem(card1));
        assertEquals(user.getInventory().getCards().size(), 0);

    }
}