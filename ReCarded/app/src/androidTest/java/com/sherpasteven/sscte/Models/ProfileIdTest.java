package com.sherpasteven.sscte.Models;

import android.test.AndroidTestCase;


/**
 * Created by elias on 30/11/15.
 */
public class ProfileIdTest extends AndroidTestCase {


    ProfileId id;

    public void setUp() throws Exception {
        super.setUp();
        id = new ProfileId(getContext());
    }

    public void testGetId() throws Exception {
        ProfileId newId = new ProfileId(getContext());
        assertNotNull(newId);
    }

    public void testEquals() throws Exception {
        ProfileId sameId = new ProfileId(getContext());
        ProfileId otherId = new ProfileId(getContext());
        assertEquals(sameId, otherId);
    }

    public void testGetIdImmutable() throws Exception {
        ProfileId newId = new ProfileId(getContext());
        assertEquals(id, newId);
    }


}