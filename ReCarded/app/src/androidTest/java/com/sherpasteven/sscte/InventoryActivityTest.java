package com.sherpasteven.sscte;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.sherpasteven.sscte.Views.FriendsTab;
import com.sherpasteven.sscte.Views.InventoryTab;
import com.sherpasteven.sscte.Views.SlidingTabLayout;
import com.sherpasteven.sscte.Views.TradesTab;

/**
 * Created by joshua on 06/11/15.
 */
public class InventoryActivityTest extends ActivityInstrumentationTestCase2 {

    private ImageButton btnadditem;
    private Button btnviewitem;
    private Button btnedititem;

    public InventoryActivityTest() {
        super(com.sherpasteven.sscte.InventoryActivity.class);}

    public void testStart() throws Exception{

        Activity activity = getActivity();
    }

    /**
     * This tests the add card functionality of the Inventory tab
     * The test should check that the AddCardActivity is run when
     * The add card button is hit
     */
    public void testAddCard() {
        InventoryActivity activity = (InventoryActivity) getActivity();

        btnadditem = activity.getAddButton();


        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(AddCardActivity.class.getName(),
                        null, false);

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                btnadditem.performClick();
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

    /**
     * This tests the view card functionality of the inventory tab
     * The ViewCardActivity should be run when the view item button is
     * hit
     */
    public void testViewCard() {
        InventoryActivity activity = (InventoryActivity) getActivity();

        btnviewitem = activity.getViewButton();
        // enter the recyclerview actions

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(ViewCardActivity.class.getName(),
                        null, false);

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                btnviewitem.performClick();
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

    /**
     * This tests the edit card functionality of the inventory tab
     * The EditCardActivity should be run when the edit button is hit.
     */
    public void testEditCard() {
        InventoryActivity activity = (InventoryActivity) getActivity();

        btnedititem = activity.getEditButton();
        // enter the recyclerview action -> enter the view card activity
        // click on edit item in the menu from there

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditCardActivity.class.getName(),
                        null, false);

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                btnedititem.performClick();
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

    /**
     * This tests that the scroll bar can scroll to the inventory tab.
     * The tests scrolls the bar and then gets the focused child which
     * should be a InventoryTab
     */
    public void testGetInventoryTab(){
        InventoryActivity activity = (InventoryActivity) getActivity();
        final SlidingTabLayout tabLayout = (SlidingTabLayout) activity.findViewById(R.id.tabs);
        final Integer scrollRight = 1;
        final View[] focusedChild = new View[1];

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //scroll to right
                focusedChild[0] = tabLayout.getFocusedChild();
            }
        });
        getInstrumentation().waitForIdleSync();

        //fragment has the same id as the containing view
        Fragment focusedFragment = activity.getSupportFragmentManager().findFragmentById(focusedChild[0].getId());
        assertTrue(focusedFragment instanceof InventoryTab);

        getInstrumentation().waitForIdleSync();
        activity.finish();
    }

    /**
     * This tests that the scroll bar can scroll to the trades tab.
     * The tests scrolls the bar and then gets the focused child which
     * should be a TradesTab
     */
    public void testGetTradesTab(){
        InventoryActivity activity = (InventoryActivity) getActivity();
        final SlidingTabLayout tabLayout = (SlidingTabLayout) activity.findViewById(R.id.tabs);
        final Integer scrollRight = 1;
        final View[] focusedChild = new View[1];

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //scroll to right
                tabLayout.arrowScroll(scrollRight);
                focusedChild[0] = tabLayout.getFocusedChild();
            }
        });
        getInstrumentation().waitForIdleSync();

        //fragment has the same id as the containing view
        Fragment focusedFragment = activity.getSupportFragmentManager().findFragmentById(focusedChild[0].getId());
        assertTrue(focusedFragment instanceof TradesTab);

        getInstrumentation().waitForIdleSync();
        activity.finish();
    }

    /**
     * This tests that the scroll bar can scroll to the friends tab.
     * The tests scrolls the bar and then gets the focused child which
     * should be a Friends
     */
    public void testGetFriendsTab(){
        InventoryActivity activity = (InventoryActivity) getActivity();
        final SlidingTabLayout tabLayout = (SlidingTabLayout) activity.findViewById(R.id.tabs);
        final Integer scrollRight = 1;
        final View[] focusedChild = new View[1];

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //scroll to right twice
                tabLayout.arrowScroll(scrollRight);
                tabLayout.arrowScroll(scrollRight);
                focusedChild[0] = tabLayout.getFocusedChild();
            }
        });
        getInstrumentation().waitForIdleSync();

        //fragment has the same id as the containing view
        Fragment focusedFragment = activity.getSupportFragmentManager().findFragmentById(focusedChild[0].getId());
        assertTrue(focusedFragment instanceof FriendsTab);
    }

    /**
     * Test that a new trade can be opened by clicking on the
     * addTradeButton from the TradesTab. The AddTradeActivity should be opened.
     */
    public void testAddNewTradeOpens(){
        InventoryActivity activity = (InventoryActivity) getActivity();

        final SlidingTabLayout tabLayout = (SlidingTabLayout) activity.findViewById(R.id.tabs);
        final Integer scrollRight = 1;

        //scroll to trade tab
        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //scroll to right
                tabLayout.arrowScroll(scrollRight);
            }
        });
        getInstrumentation().waitForIdleSync();

        Fragment tradeFragment =  activity.getSupportFragmentManager().findFragmentById(R.id.tabs);
        final Button addTradeButton = (Button) tradeFragment.getView().findViewById(R.id.btnAddTrade);
        assertNotNull(addTradeButton);

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(AddTradeActivity.class.getName(),
                        null, false);

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                addTradeButton.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();
        AddTradeActivity receiverActivity = (AddTradeActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                AddTradeActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);
        getInstrumentation().waitForIdleSync();
        receiverActivity.finish();
    }

    /**
     * Test that a trade can be edited by clicking on the
     * editTradeButton from the TradesTab. The EditTradeActivity should be opened.
     */
    public void testEditTradeOpens(){
        InventoryActivity activity = (InventoryActivity) getActivity();
        final SlidingTabLayout tabLayout = (SlidingTabLayout) activity.findViewById(R.id.tabs);
        final Integer scrollRight = 1;

        //scroll to trade tab
        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //scroll to right
                tabLayout.arrowScroll(scrollRight);
            }
        });

        getInstrumentation().waitForIdleSync();
        Fragment tradeFragment =  activity.getSupportFragmentManager().findFragmentById(R.id.tabs);
        final Button editTradeButton = (Button) tradeFragment.getView().findViewById(R.id.btnEditTrade);
        assertNotNull(editTradeButton);

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTradeActivity.class.getName(),
                        null, false);

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                editTradeButton.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();
        EditTradeActivity receiverActivity = (EditTradeActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTradeActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);
        getInstrumentation().waitForIdleSync();
        receiverActivity.finish();
    }

    /**
     * Test that a new friend can be added by clicking on the
     * addFriendButton from the FriendsTab. The AddFriendActivity should be opened.
     */
    public void testAddNewFriendOpens(){
        InventoryActivity activity = (InventoryActivity) getActivity();
        final SlidingTabLayout tabLayout = (SlidingTabLayout) activity.findViewById(R.id.tabs);
        final Integer scrollRight = 1;

        //scroll to friends tab
        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //scroll to right
                tabLayout.arrowScroll(scrollRight);
                tabLayout.arrowScroll(scrollRight);
            }
        });

        Fragment tradeFragment =  activity.getSupportFragmentManager().findFragmentById(R.id.tabs);
        final Button addFriendButton = (Button) tradeFragment.getView().findViewById(R.id.btnAddFriend);
        assertNotNull(addFriendButton);

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTradeActivity.class.getName(),
                        null, false);

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                addFriendButton.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();
        AddFriendActivity receiverActivity = (AddFriendActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                AddFriendActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);
        getInstrumentation().waitForIdleSync();
        receiverActivity.finish();
    }

    /**
     * Test that a friend can be viewed by clicking on the
     * viewFriendButton from the FriendsTab. The ViewFriendActivity should be opened.
     */
    public void testViewFriendOpens(){
        InventoryActivity activity = (InventoryActivity) getActivity();
        final SlidingTabLayout tabLayout = (SlidingTabLayout) activity.findViewById(R.id.tabs);
        final Integer scrollRight = 1;

        //scroll to friends tab
        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //scroll to right
                tabLayout.arrowScroll(scrollRight);
                tabLayout.arrowScroll(scrollRight);
            }
        });

        Fragment tradeFragment =  activity.getSupportFragmentManager().findFragmentById(R.id.tabs);
        final Button viewFriendButton = (Button) tradeFragment.getView().findViewById(R.id.btnViewFriend);
        assertNotNull(viewFriendButton);

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTradeActivity.class.getName(),
                        null, false);

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                viewFriendButton.performClick();
            }
        });

        getInstrumentation().waitForIdleSync();
        ViewFriendActivity receiverActivity = (ViewFriendActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                ViewFriendActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);
        getInstrumentation().waitForIdleSync();
        receiverActivity.finish();
    }

    /**
     * Test that when a card is selected from the list of cards in the InventoryTab an
     * appropriate ViewCardActivity is run.
     */
    public void testSelectCard(){
        InventoryActivity activity = (InventoryActivity) getActivity();
        final SlidingTabLayout tabLayout = (SlidingTabLayout) activity.findViewById(R.id.tabs);
        final Integer scrollRight = 1;
        final View[] focusedChild = new View[1];


        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(ViewCardActivity.class.getName(),
                        null, false);

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                focusedChild[0] = tabLayout.getFocusedChild();
            }
        });
        getInstrumentation().waitForIdleSync();

        //fragment has the same id as the containing view
        InventoryTab focusedFragment = (InventoryTab) activity.getSupportFragmentManager().findFragmentById(focusedChild[0].getId());
        RecyclerView recyclerView = (RecyclerView) focusedFragment.getView().findViewById(R.id.recyclerView);
        final CardView cardView = (CardView) recyclerView.getFocusedChild();

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //click card
                cardView.callOnClick();
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

        getInstrumentation().waitForIdleSync();
        activity.finish();
    }

    /**
     * Test that when a trade is selected from the list of trades in the TradeTab an
     * appropriate ViewTradeActivity is run.
     */
    public void testSelectTrade(){
        InventoryActivity activity = (InventoryActivity) getActivity();
        final SlidingTabLayout tabLayout = (SlidingTabLayout) activity.findViewById(R.id.tabs);
        final Integer scrollRight = 1;
        final View[] focusedChild = new View[1];


        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTradeActivity.class.getName(),
                        null, false);

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //scroll to right
                focusedChild[0] = tabLayout.getFocusedChild();
                tabLayout.arrowScroll(scrollRight);
            }
        });
        getInstrumentation().waitForIdleSync();

        //fragment has the same id as the containing view
        InventoryTab focusedFragment = (InventoryTab) activity.getSupportFragmentManager().findFragmentById(focusedChild[0].getId());
        RecyclerView recyclerView = (RecyclerView) focusedFragment.getView().findViewById(R.id.recyclerView);
        final CardView cardView = (CardView) recyclerView.getFocusedChild();

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //click card
                cardView.callOnClick();
            }
        });

        getInstrumentation().waitForIdleSync();
        EditTradeActivity receiverActivity = (EditTradeActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTradeActivity.class, receiverActivity.getClass());

        getInstrumentation().waitForIdleSync();
        activity.finish();
    }

    /**
     * Test that when a friend is selected from the list of friends in the FriendsTab an
     * appropriate ViewFriendActivity is run.
     */
    public void testGetFriend(){
        InventoryActivity activity = (InventoryActivity) getActivity();
        final SlidingTabLayout tabLayout = (SlidingTabLayout) activity.findViewById(R.id.tabs);
        final Integer scrollRight = 1;
        final View[] focusedChild = new View[1];


        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(ViewFriendActivity.class.getName(),
                        null, false);

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //scroll to right
                focusedChild[0] = tabLayout.getFocusedChild();
                tabLayout.arrowScroll(scrollRight);
                tabLayout.arrowScroll(scrollRight);
            }
        });
        getInstrumentation().waitForIdleSync();

        //fragment has the same id as the containing view
        InventoryTab focusedFragment = (InventoryTab) activity.getSupportFragmentManager().findFragmentById(focusedChild[0].getId());
        RecyclerView recyclerView = (RecyclerView) focusedFragment.getView().findViewById(R.id.recyclerView);
        final CardView cardView = (CardView) recyclerView.getFocusedChild();

        getInstrumentation().waitForIdleSync();
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //click card
                cardView.callOnClick();
            }
        });

        getInstrumentation().waitForIdleSync();
        ViewFriendActivity receiverActivity = (ViewFriendActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                ViewFriendActivity.class, receiverActivity.getClass());

        getInstrumentation().waitForIdleSync();
        activity.finish();
    }
}