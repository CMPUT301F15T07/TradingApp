package com.sherpasteven.sscte.Models;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;

import com.sherpasteven.sscte.ProfileActivity;
import com.sherpasteven.sscte.Views.IView;

/**
 * Created by elias on 29/11/15.
 */
public class ElasticSearchTest extends AndroidTestCase implements IView {

    ElasticSearch elasticSearch;
    Friend friend;
    boolean updated = false;
    Friends pulledFriends;
    Activity splashPage;


    public ElasticSearchTest() {
        super();
    }

    @Override
    public void Update(Object o) {
        updated = true;
        pulledFriends = elasticSearch.getFriends();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        elasticSearch = new ElasticSearch();
        elasticSearch.addView(this);
        User user = new User("test", "test", "test", getContext());
        friend = new Friend(user, getContext());
    }

    @Override
    protected void tearDown() throws Exception {
        //elasticSearch.deleteView(this);
        //elasticSearch = null;
    }

    /**
     * This test is pretty unreliable since
     * it will not work if you aren't connected to the internet,
     * or if elasticsearch is slow/down.
     * @throws Exception
     */
    public void testInsertAndSearch() throws Exception {
        elasticSearch.InsertFriend(friend);
        Thread.sleep(1000);
        elasticSearch.searchFriends("*", null);
        Thread.sleep(5000);
        assertNotNull(pulledFriends);
        assertNotNull(pulledFriends.get(friend.getProfileId()));
    }

}