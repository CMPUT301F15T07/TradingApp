package com.sherpasteven.sscte;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sherpasteven.sscte.Models.Profile;

/**
 * Created by joshua on 06/11/15.
 */
public class SplashPageTest extends ActivityInstrumentationTestCase2 {

        private Button enterbutton;
        private EditText nametext;
        private EditText citytext;
        private EditText emailtext;

        public SplashPageTest() {
            super(com.sherpasteven.sscte.SplashPage.class);}

        public void testStart() throws Exception{

            Activity activity = getActivity();
        }

        public void testRegistration() {
            SplashPage activity = (SplashPage) getActivity();


            final String name = "Joshua";
            final String city = "Edmonton";
            final String email = "jjwhite@ualberta.ca";

            nametext = activity.getNameText();
            citytext = activity.getCityText();
            emailtext = activity.getEmailText();

            enterbutton = activity.getEnterButton();

            activity.runOnUiThread((new Runnable() {
                public void run() {
                    nametext.setText(name);
                    citytext.setText(city);
                    emailtext.setText(email);
                }
            }));

            // Set up an ActivityMonitor
            Instrumentation.ActivityMonitor receiverActivityMonitor =
                    getInstrumentation().addMonitor(InventoryActivity.class.getName(),
                            null, false);

            getInstrumentation().waitForIdleSync();
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    enterbutton.performClick();
                }
            });

            getInstrumentation().waitForIdleSync();

            Profile profile = activity.getProfile();
            assertEquals(profile.getUser().getName(), name);
            assertEquals(profile.getUser().getLocation(), city);
            assertEquals(profile.getUser().getEmail(), email);






            InventoryActivity receiverActivity = (InventoryActivity)
                    receiverActivityMonitor.waitForActivityWithTimeout(1000);
            assertNotNull("ReceiverActivity is null", receiverActivity);
            assertEquals("Monitor for ReceiverActivity has not been called",
                    1, receiverActivityMonitor.getHits());
            assertEquals("Activity is of wrong type",
                    InventoryActivity.class, receiverActivity.getClass());

            // Remove the ActivityMonitor
            getInstrumentation().removeMonitor(receiverActivityMonitor);



            getInstrumentation().waitForIdleSync();

            receiverActivity.finish();
}}
