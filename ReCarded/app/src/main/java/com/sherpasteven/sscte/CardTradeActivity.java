package com.sherpasteven.sscte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.sherpasteven.sscte.Controllers.FriendsTabController;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.Views.RecyclerView.CardTradeAdapter;
import com.sherpasteven.sscte.Views.RecyclerView.NewFriendAdapter;

import java.util.ArrayList;

public class CardTradeActivity extends AppCompatActivity {

    private FriendsTabController friendstabcontroller;
    private ArrayList<Card> cardlist = new ArrayList<>();
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private boolean isUserList = false;
    protected LayoutManagerType mCurrentLayoutManagerType;
    private ArrayList<Card> cardslist;
    // demo code before get friends inventory
    private ArrayList<Card> friendslist;

    protected RecyclerView mRecyclerView;
    protected CardTradeAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_trade);

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

        Intent intent = getIntent();
        isUserList = intent.getBooleanExtra("com.sherpasteve.sscte.user", false);
        //setProfile(CurrentProfile.getCurrentProfile().getProfile(this));
        //setPosition(intent.getIntExtra("com.sherpasteven.sscte.viewcard", 0));

        if (isUserList) {
            cardslist = CurrentProfile.getCurrentProfile().getProfile(this).getUser().getInventory().getCards();
            mAdapter = new CardTradeAdapter(cardslist);
        } else {
            initializeData(); // build sample data for friends, then show the friendslist
                              // we don't have friends setup yet, so this is sample data to test...
            mAdapter = new CardTradeAdapter(friendslist);
        }

        //
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)

        //initializeData();
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
    
    /*
    @Override
    public void Update(Profile profile) {

    }*/

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
        Card card = new Card("Test", null, 4, new Quality(4), "Test", "Test", true, "Test", new User("test", "test", "test", this));
        friendslist.add(card);
    }
}
