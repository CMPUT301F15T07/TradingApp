package com.sherpasteven.sscte;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sherpasteven.sscte.Controllers.InventoryTabController;

import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Friend;

import com.sherpasteven.sscte.Models.Model;

import com.sherpasteven.sscte.Models.SearchSingleton;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Views.IView;
import com.sherpasteven.sscte.Views.RecyclerView.CardAdapter;
import com.sherpasteven.sscte.Views.RecyclerView.ViewFriendsCardAdapter;


import java.util.ArrayList;
public class FriendInventoryActivity extends AppCompatActivity implements IView<Model>{

    Friend friend;
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
    private View inflate_view;
    private User user;
    private int position;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected ViewFriendsCardAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_friend_inventory);
        Intent intent = getIntent();
        position = intent.getIntExtra("com.sherpasteven.sscte.viewfriend", 0);
        ArrayList<Friend> listOfFriends = CurrentProfile.getCurrentProfile().getProfile(this).getUser().getFriends();
        friend = listOfFriends.get(position);
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

        mAdapter = new ViewFriendsCardAdapter(friend.getInventory().getCards(), position);

        // Set CardAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)

        setTitle(friend.getName() + "'s Inventory");

    }
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {

        mLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
        mRecyclerView.setLayoutManager(mLayoutManager);

    }



    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_inventory, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void Update(Model model) {

    }

    @Override
    public void startActivity(Intent intent) {
        // check if search intent
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            SearchSingleton.getSearchSingleton().setInventory(friend.getInventory().getCards());
        }

        super.startActivity(intent);
    }
}
