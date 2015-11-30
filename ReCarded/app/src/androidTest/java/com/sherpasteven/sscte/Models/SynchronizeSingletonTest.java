package com.sherpasteven.sscte.Models;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.sherpasteven.sscte.ProfileActivity;
import com.sherpasteven.sscte.SettingsActivity;

import junit.framework.TestCase;

/**
 * Created by elias on 30/11/15.
 */
public class SynchronizeSingletonTest extends ActivityInstrumentationTestCase2<SettingsActivity> {

    ProfileSynchronizer profileSynchronizer;
    SettingsActivity settingsActivity;

    public SynchronizeSingletonTest() {
        super(SettingsActivity.class);
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        settingsActivity = getActivity();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGetSynchronize() throws Exception {
        profileSynchronizer = SynchronizeSingleton.GetSynchronize(settingsActivity);
        assertNotNull(profileSynchronizer);
        ProfileSynchronizer same = SynchronizeSingleton.GetSynchronize(settingsActivity);
        assertEquals(profileSynchronizer, same);
    }

    public void testReGetSynchronize() throws Exception {

        ProfileSynchronizer same = SynchronizeSingleton.GetSynchronize(settingsActivity);
        assertEquals(profileSynchronizer, same);
    }
}