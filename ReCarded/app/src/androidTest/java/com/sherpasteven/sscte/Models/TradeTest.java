package com.sherpasteven.sscte.Models;

import android.test.AndroidTestCase;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by joshua on 30/11/15.
 */
public class TradeTest extends AndroidTestCase {

    Trader joshua;
    Trader salim;
    Card card1;
    Card card2;
    ArrayList<Card> setupCards;


    public void setUp() throws Exception {
        super.setUp();

        User salimuser = new User("salim", "Canada", "salim@ualberta.ca", this.getContext());
        User joshuauser = new User("joshua", "Canada", "jjwhite@ualberta.ca", this.getContext());
        card1 = new Card("charizard", 2, new Quality(73), "Pokemon", "Basic XY Red",
                true, "Gently bent edge, 100HP", joshuauser);

        card2 = new Card("Blue Eyes White Dragon", 9, new Quality(3), "YuGiOh", "stupid series",
                true, "Dime a dozen", salimuser);

        salimuser.addInventoryItem(card1);
        salim = new Trader(salimuser, getContext());

        joshuauser.addInventoryItem(card2);
        joshua = new Trader(joshuauser, this.getContext());

    }

    public void tearDown() throws Exception {

    }

    public void testSetStatus() throws Exception {
        Trade trade = new Trade(joshua, salim);
        assertEquals(trade.getStatus(), "PENDING");
        trade.setStatus("ACCEPTED");
        assertEquals(trade.getStatus(), "ACCEPTED");

    }

    public void testCounterOffer() throws Exception {

        Trade trade = new Trade(joshua, salim);
        trade.addOwnerList(salim.getInventoryItem(0));
        trade.addBorrowList(joshua.getInventoryItem(0));

        assertEquals(trade.getBorrower(), joshua);
        assertEquals(trade.getOwner(), salim);
        //assertEquals();


    }
}