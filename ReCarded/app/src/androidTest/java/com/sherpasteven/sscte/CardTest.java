package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.User;

/**
 * Created by jake on 2015-11-12.
 */
public class CardTest  extends ApplicationTestCase<Application> {
    public CardTest() {
        super(Application.class);
    }

    public void testEquals() {
        User testuser = new User("John", "city1", "email1@gmail.com",null);
        User testuser2 = new User("Jack", "city2", "email2@gmail.com",null);
        Card card1 = new Card("dummycard", 1, new Quality(20), "category1", "series1", true, "dummy card", testuser);
        Card card2 = new Card(card1.getName(), card1.getQuantity(), card1.getQuality(), card1.getCatagory(), card1.getSeries(), card1.isTradable(), card1.getComments(), testuser);
        assertTrue(card1.equals(card2));

        Card card3 = new Card("dummycard", 1, new Quality(20), "category1", "series1", true, "dummy card", testuser);
        assertTrue(card1.equals(card3));

        card2.setName("changed name");
        assertFalse(card1.equals(card2));

        card3.setOwner(testuser2);
        assertFalse(card1.equals(card3));


    }

    public void testRelevantCategories(){
        String[] categories = Card.getRelevantCatagories();
        assertEquals(categories.length,10 );
        assertEquals(categories[0],"Magic The Gathering");
        assertEquals(categories[9], "MISC");
    }
}
