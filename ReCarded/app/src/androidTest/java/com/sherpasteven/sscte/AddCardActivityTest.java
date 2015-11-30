package com.sherpasteven.sscte;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.User;

/**
 * Created by joshua on 06/11/15.
 */
public class AddCardActivityTest extends ActivityInstrumentationTestCase2 {


    private EditText nametext;
    private EditText catagorytext;
    private EditText seriestext;
    private EditText qualitytext;
    private EditText quantitytext;
    private EditText commentstext;

    private Button enterbutton;

    private CheckBox checkbox;

    public AddCardActivityTest(){

        super(com.sherpasteven.sscte.AddCardActivity.class);}

    public void testStart() throws Exception{

        Activity activity = getActivity();
    }

    public void testRegistration() {
        AddCardActivity activity = (AddCardActivity) getActivity();


        final String name = "Dr.Fate";
        final String catagory = "DC";
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
                catagorytext.setText(catagory);
                seriestext.setText(series);
                qualitytext.setText(quality);
                quantitytext.setText(quantity);
                commentstext.setText(comments);
            }
        }));

        getInstrumentation().waitForIdleSync();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                checkbox.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();

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

        //get the local user and see if that new card was added to inventory
        User user = new User("joshua","edmonton", "jjwhite@ualberta.ca"); //-> replace with method to obtain local user
        Inventory inventory = user.getInventory();
        Card card = new Card(name, Integer.parseInt(quantity), new Quality(Integer.parseInt(quality)), catagory, series,
                Boolean.TRUE, comments, user);



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
    }
}
