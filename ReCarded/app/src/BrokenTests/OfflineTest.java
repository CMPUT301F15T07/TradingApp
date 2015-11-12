package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.User;

/**
 * Created by elias on 17/10/15.
 */
public class OfflineTest extends ApplicationTestCase<Application> {
    public OfflineTest(Class<Application> applicationClass) {
        super(applicationClass);
    }

  
    public void testMakeOffLineItems{

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


    public void testMakeOffLineTrades{
    //User wants to propose trades offline, once connectivity reseumes the trades will be established

	

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

    
    public void testSaveFriendsLocal
    //User opens up friends inventories that has been previously opened while offline

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
