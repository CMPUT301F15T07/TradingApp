package com.sherpasteven.sscte;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Views.IView;
import com.sherpasteven.sscte.Views.SlidingTabLayout;
import com.sherpasteven.sscte.Views.ViewPagerAdapter;

import java.util.Set;

public class InventoryActivity extends ActionBarActivity implements IView<Inventory>{

    // Declaring Your View and Variables

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Inventory","Trades","Friends"};
    int Numboftabs = 3;
    private Profile currentprofile;
    private User currentuser;

    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        currentprofile = CurrentProfile.GetCurrentProfile(this);
        currentuser = currentprofile.getUser();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        //changeToolbarColor();

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs,currentuser);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.orange_main);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs,currentuser);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.orange_main);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);


    }

    public Button getAddButton(){
        return (Button) findViewById(R.id.btnAddItem);
    }

    public Button getViewButton(){
        return (Button) findViewById(R.id.btnViewItem);
    }

    public Button getEditButton(){
        return (Button) findViewById(R.id.btnEditItem);
    }

    /**
     * Changes the toolbar color on the main page.
     * @return true
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public boolean changeToolbarColor() {
        // Creating The Toolbar and setting it as the Toolbar for the activity

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        return true;
    }

    /**
     * Generates hamburger menu options.
     * @param menu Menu item to be created.
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inventory, menu);
        return true;
    }

    /**
     * OnSelect options for option selected from hamburger menu.
     * @param item Item selected by user.
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent1 = new Intent(this, SettingsActivity.class);
            this.startActivity(intent1);
        } else if (id == R.id.action_profile) {
            Intent intent2 = new Intent(this, ProfileActivity.class);
            this.startActivity(intent2);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Updates the activity based on raised condition.
     * @param inventory Updates inventory based on model response.
     */
    @Override
    public void Update(Inventory inventory) {

    }

    /**
     * Controller code to set intent switch.
     * Currently unused.
     */
    public void NavigateToFriendsActivity() {}
}