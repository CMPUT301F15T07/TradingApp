package com.sherpasteven.sscte;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sherpasteven.sscte.Controllers.FriendsTabController;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Views.IView;
import com.sherpasteven.sscte.Views.RecyclerView.FriendAdapter;
import com.sherpasteven.sscte.Views.RecyclerView.NewFriendAdapter;

import java.util.ArrayList;

public class AddFriendActivity extends AppCompatActivity implements IView<Profile>{

    private FriendsTabController friendstabcontroller;
    private ArrayList<User> friendslist = new ArrayList<>();
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

        mAdapter = new NewFriendAdapter(friendslist);
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
     * @param profile Profile to reference friend list.
     */
    @Override
    public void Update(Profile profile) {

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
        friendslist.add(new User("test1", "location1", "email1", this.getApplicationContext()));
        friendslist.add(new User("test2", "location2", "email2", this.getApplicationContext()));
        friendslist.add(new User("test3", "location3", "email3", this.getApplicationContext()));
        friendslist.add(new User("test4", "location4", "email4", this.getApplicationContext()));
        friendslist.add(new User("test5", "location5", "email5", this.getApplicationContext()));
        friendslist.add(new User("test6", "location6", "email6", this.getApplicationContext()));
        friendslist.add(new User("test7", "location7", "email7", this.getApplicationContext()));
        friendslist.add(new User("test8", "location8", "email8", this.getApplicationContext()));
        friendslist.add(new User("test9", "location9", "email9", this.getApplicationContext()));
        friendslist.add(new User("test10", "location10", "email10", this.getApplicationContext()));
        friendslist.add(new User("test11", "location11", "email11", this.getApplicationContext()));
    }

}

