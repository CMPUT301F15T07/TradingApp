package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.TradeLog;
import com.sherpasteven.sscte.Models.User;

import java.util.ArrayList;

/**
 * Created by jake on 2015-11-12.
 */
public class UserTest extends ApplicationTestCase<Application> {
    public UserTest() {
        super(Application.class);
    }

    public void testNewUser() {
        User johnCena = new User("WWE","Salt lake City","theChampion@yahoo.com");
        assertEquals(johnCena.getName(),"WWE");
        assertEquals(johnCena.getLocation(),"Salt lake City");
        assertEquals(johnCena.getEmail(), "theChampion@yahoo.com");

    }

    public void testSetUserAttributes(){
        User johnCena = new User("WWE","Salt lake City","theChampion@yahoo.com");
        johnCena.setName("THE CHAMP");
        johnCena.setLocation("THE RING");
        johnCena.setEmail("hellokitty@yahoo.com");
        assertEquals(johnCena.getName(),"THE CHAMP");
        assertEquals(johnCena.getLocation(),"THE RING");
        assertEquals(johnCena.getEmail(), "hellokitty@yahoo.com");

    }
    public void testUserFriends(){
        User johnCena = new User("WWE","Salt lake City","theChampion@yahoo.com");
        User friend = new User("Mr.Friend","Friend City","veryFriend@yahoo.com");
        johnCena.addFriend(friend);
        assertTrue(johnCena.getFriends().contains(friend));

        User friend2 = new User("Mrs.Friend", "Friendlytown", "iamfriend@gmail.com");
        johnCena.addFriend(friend2);

        assertEquals(johnCena.getFriends().size(), 2);
        User friend3 = johnCena.getFriend("Mrs.Friend");
        assertEquals(friend2,friend3);

        johnCena.removeFriend(friend);
        assertFalse(johnCena.getFriends().contains(friend));
        assertEquals(johnCena.getFriends().size(),1);

        ArrayList<User> friendlist = new ArrayList<>();
        User user1 = new User("user1","city1","email1@yahoo.com");
        User user2 = new User("user2","ity2","email2@yahoo.com");
        friendlist.add(user1);
        friendlist.add(user2);

        johnCena.setFriends(friendlist);
        assertTrue(johnCena.getFriends().contains(user1));
        assertTrue(johnCena.getFriends().contains(user2));
        assertEquals(johnCena.getFriends().size(),2);

    }
    public void testUserTrades(){
        User johnCena = new User("Johnnyboy","Salt lake City","theChampion@yahoo.com");
        User randyOrton = new User("Randy","Salt City","RKO@yahoo.com");
        TradeLog tradelist = new TradeLog();
        Trade trade = new Trade(johnCena, randyOrton);
        tradelist.addTrade(trade);

        johnCena.setTrades(tradelist);
        assertEquals(johnCena.getTrades().getPendingTrades().size(),1);
        assertTrue(johnCena.getTrades().getPendingTrades().contains(trade));

    }
    public void testUserInventory(){
        User johnCena = new User("Johnnyboy","Salt lake City","theChampion@yahoo.com");
        Inventory inventory1 = new Inventory();
        Card card1 = new Card("dummycard", 1, new Quality(20), "category1", "series1", true, "dummy card", johnCena);
        inventory1.addCard(card1);

        johnCena.setInventory(inventory1);
        assertTrue(johnCena.getInventory().containsCard(card1));

    }


}
