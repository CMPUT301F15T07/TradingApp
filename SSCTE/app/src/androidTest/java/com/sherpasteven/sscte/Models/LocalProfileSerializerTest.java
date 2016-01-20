package com.sherpasteven.sscte.Models;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.sherpasteven.sscte.ProfileActivity;

import junit.framework.TestCase;

/**
 * Created by elias on 29/11/15.
 */
public class LocalProfileSerializerTest extends ActivityInstrumentationTestCase2<ProfileActivity> {

    LocalProfileSerializer localProfileSerializer;
    ProfileActivity profileActivity;

    public LocalProfileSerializerTest() {
        super(ProfileActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        profileActivity = getActivity();
        localProfileSerializer = new LocalProfileSerializer();
    }

    public void testSerialize() throws Exception {
        Profile profile = new Profile(new User("test name", "test location", "test email", profileActivity));
        localProfileSerializer.Serialize(profile, profileActivity);
        Profile otherProfile = localProfileSerializer.Deserialize(null, profileActivity);

        assertEquals(profile, otherProfile);
    }

}