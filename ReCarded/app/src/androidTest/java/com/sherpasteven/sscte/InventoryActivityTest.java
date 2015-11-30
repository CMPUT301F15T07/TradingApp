package com.sherpasteven.sscte;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.sherpasteven.sscte.Models.Profile;

/**
 * Created by joshua on 06/11/15.
 */
public class InventoryActivityTest extends ActivityInstrumentationTestCase2 {

    private Button btnadditem;
    private Button btnviewitem;
    private Button btnedititem;

    public InventoryActivityTest() {
        super(com.sherpasteven.sscte.InventoryActivity.class);}

    /*
    public void testStart() throws Exception{

        Activity activity = getActivity();
    }

    public void testAddCard() {
        InventoryActivity activity = (InventoryActivity) getActivity();

        btnadditem = null; //activity.getAddButton();


        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(AddCardActivity.class.getName(),
                        null, false);

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                //btnadditem.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();


        AddCardActivity receiverActivity = (AddCardActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                AddCardActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);



        getInstrumentation().waitForIdleSync();

        receiverActivity.finish();
    }

    public void testViewCard() {
        InventoryActivity activity = (InventoryActivity) getActivity();

        btnviewitem = null; //activity.getViewButton();


        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(ViewCardActivity.class.getName(),
                        null, false);

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                //btnviewitem.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();


        ViewCardActivity receiverActivity = (ViewCardActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                ViewCardActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);



        getInstrumentation().waitForIdleSync();

        receiverActivity.finish();
    }

    public void testEditCard() {
        InventoryActivity activity = (InventoryActivity) getActivity();

        btnedititem = null; //activity.getEditButton();


        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditCardActivity.class.getName(),
                        null, false);

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                //btnedititem.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();


        EditCardActivity receiverActivity = (EditCardActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditCardActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);



        getInstrumentation().waitForIdleSync();

        receiverActivity.finish();
    }
*/
}