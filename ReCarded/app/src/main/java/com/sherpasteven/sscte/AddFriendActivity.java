package com.sherpasteven.sscte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.sherpasteven.sscte.Controllers.FriendsTabController;
import com.sherpasteven.sscte.Models.Friend;
import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Views.IView;
import com.sherpasteven.sscte.Views.RecyclerView.NewFriendAdapter;

import java.util.ArrayList;

public class AddFriendActivity extends AppCompatActivity implements IView<Model>{

    private FriendsTabController friendstabcontroller;
    private ArrayList<Friend> friendslist = new ArrayList<>();
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected NewFriendAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(this);

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mAdapter = new NewFriendAdapter(friendslist, this);
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)

        initializeData();
    }

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {

        mLayoutManager = new LinearLayoutManager(this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    /**
     * Generates hamburger menu options.
     * @param menu Menu item to be created.
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Updates the activity based on raised condition.
     * @param model Profile to reference friend list.
     */
    @Override
    public void Update(Model model) {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Generates data for friends with respect to currentUser object.
     * FIXME: Convert for dynamic friend data loading.
     * FIXME: Adapt currentUser structure for user-hosted profile.
     */
    private void initializeData() {

        for(int i = 1; i < 12; i++) {
            String ist = Integer.toString(i);
            friendslist.add(new Friend(new User("test" + ist, "location" + ist, "email" + ist, this.getApplicationContext()),
                    this.getApplicationContext()));

        }

    }
    public void returnFromActivity(){
        finish();
    }

}

