package com.sherpasteven.sscte;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.User;

/**
 * Created by joshua on 06/11/15.
 */
public class AddCardActivityTest extends ActivityInstrumentationTestCase2 {


    private EditText nametext;
    private Spinner catagorytext;
    private EditText seriestext;
    private EditText qualitytext;
    private EditText quantitytext;
    private EditText commentstext;

    private Button enterbutton;

    private CheckBox checkbox;

    public AddCardActivityTest(){
        super(com.sherpasteven.sscte.AddCardActivity.class);
    }

    public void testStart() throws Exception{

        Activity activity = getActivity();
    }
/*
    public void testRegistration() {
        final AddCardActivity activity = (AddCardActivity) getActivity();


        final String name = "Dr.Fate";
        final String catagory = "YuGiOh";
        final String series = "Zantana and Friends";
        final String quality = "93";
        final String quantity = "2";
        final String comments = "This is your fate";

        nametext = activity.getNameText();
        catagorytext = activity.getCatagoryText();
        seriestext = activity.getSeriesText();
        qualitytext = activity.getQualityText();
        quantitytext = activity.getQuantityText();
        commentstext = activity.getCommentsText();

        enterbutton = activity.getEnterButton();

        checkbox = activity.getCheckBox();

        activity.runOnUiThread((new Runnable() {
            public void run() {
                nametext.setText(name);
                seriestext.setText(series);
                qualitytext.setText(quality);
                quantitytext.setText(quantity);
                commentstext.setText(comments);
            }
        }));



        activity.runOnUiThread((new Runnable() {
            public void run() {
                nametext.setText(name);
                seriestext.setText(series);
                qualitytext.setText(quality);
                quantitytext.setText(quantity);
                commentstext.setText(comments);
            }
        }));

        getInstrumentation().waitForIdleSync();

        activity.runOnUiThread(
                new Runnable() {
                    public void run() {
                        catagorytext.requestFocus();
                        catagorytext.setSelection(2);
                    }
                }
        );

        getInstrumentation().waitForIdleSync();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                checkbox.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();


        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                enterbutton.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();

        //get the local user and see if that new card was added to inventory
        User user = CurrentProfile.getCurrentProfile().getProfile(activity).getUser();
        Inventory inventory = user.getInventory();
        Card card = new Card(name, Integer.parseInt(quantity), new Quality(Integer.parseInt(quality)), catagory, series,
                Boolean.TRUE, comments, user);


        assertTrue(user.hasInventoryItem(card));
        InventoryActivity receiverActivity = (InventoryActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);

        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activityat com.sherpasteven.sscte.AddCardActivityTest.testRegistration(AddCardActivityTest.java:110)\n" +
                        "at java.lang.reflect.Method.invoke(Native Method)\n is of wrong type",
                InventoryActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);



        getInstrumentation().waitForIdleSync();

        receiverActivity.finish();

    } */
}
