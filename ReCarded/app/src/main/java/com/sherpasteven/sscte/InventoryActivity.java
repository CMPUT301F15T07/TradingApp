package com.sherpasteven.sscte;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.ISerializer;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.ProfileSynchronizer;
import com.sherpasteven.sscte.Models.SearchSingleton;
import com.sherpasteven.sscte.Models.SynchronizeSingleton;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Views.IView;
import com.sherpasteven.sscte.Views.SlidingTabLayout;
import com.sherpasteven.sscte.Views.TradesTab;
import com.sherpasteven.sscte.Views.ViewPagerAdapter;

public class InventoryActivity extends ActionBarActivity implements IView<Model>{

    // Declaring Your View and Variables

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Inventory","Trades","Friends"};
    int Numboftabs = 3;

    private Profile currentprofile;
    private User currentuser;

    private void setLocalProfile(Profile profile) {
        ISerializer<Profile> serializer = new LocalProfileSerializer();
        serializer.Serialize(profile, this);
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onResume(){
        super.onResume();
        ProfileSynchronizer profileSynchronizer = SynchronizeSingleton.GetSynchronize(this);
        profileSynchronizer.SynchronizeProfile();
    }

    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ProfileSynchronizer profileSynchronizer = SynchronizeSingleton.GetSynchronize(this);
        profileSynchronizer.addView(this);
        setContentView(R.layout.activity_inventory);

        currentprofile = CurrentProfile.getCurrentProfile().getProfile(this);
        if (currentprofile == null) {
            Toast.makeText(getApplicationContext(), "No profile loaded, returning to main page",
                    Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(this, SplashPage.class);
            startActivity(myIntent);
            finish();
        } else {
            currentuser = currentprofile.getUser();
        }
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        /*
        if (android.os.Build.VERSION.SDK_INT >= 21) { // attempt for conditional run
            changeToolbarColor();
        }
        */


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

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo( searchManager.getSearchableInfo(getComponentName()) );

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
     * @param model Updates inventory based on model response.
     */
    //@Override
    //public void Update(Model model) {
    //}


    /**
     * Controller code to set intent switch.
     * Currently unused.
     */
    public void NavigateToFriendsActivity() {}

    @Override
    public void Update(Model model) {
        if (model instanceof ProfileSynchronizer) {
            ProfileSynchronizer profileSynchronizer = SynchronizeSingleton.GetSynchronize(this);
            profileSynchronizer.UpdateProfile();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        // check if search intent
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            SearchSingleton.getSearchSingleton().setInventory(CurrentProfile.getCurrentProfile().getProfile(this).getUser().getInventory().getCards());
        }

        super.startActivity(intent);
    }

    public ImageButton getAddButton() {
        return (ImageButton)findViewById(R.id.btnAddItem);
    }
}