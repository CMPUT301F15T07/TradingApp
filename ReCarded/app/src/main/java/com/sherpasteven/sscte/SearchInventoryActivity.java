package com.sherpasteven.sscte;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sherpasteven.sscte.Controllers.FriendsTabController;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.SearchSingleton;
import com.sherpasteven.sscte.Models.TradeComposer;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Views.IView;
import com.sherpasteven.sscte.Views.RecyclerView.CardAdapter;
import com.sherpasteven.sscte.Views.RecyclerView.CardTradeAdapter;

import java.util.ArrayList;


public class SearchInventoryActivity extends AppCompatActivity implements IView<Model> {

    private FriendsTabController friendstabcontroller;
    private ArrayList<Card> cardlist = new ArrayList<>();
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;

    private boolean isUserList = false;
    protected LayoutManagerType mCurrentLayoutManagerType;
    private ArrayList<Card> cardslist;
    // demo code before get friends inventory
    private ArrayList<Card> friendslist;
    private View inflate_view;

    private User user;
    private User owner;

    protected RecyclerView mRecyclerView;
    protected CardAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleIntent(getIntent());
        setTitle("Search Results");

        setContentView(R.layout.activity_card_trade);


        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        mAdapter = new CardAdapter(cardslist, true);
        mRecyclerView.setAdapter(mAdapter);
    }


    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER, LINEAR_LAYOUT_MANAGER
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {

        mLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
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
    

    @Override
    public void Update(Model model) {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }



    public View getView(){
        return inflate_view;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }


    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query =
                    intent.getStringExtra(SearchManager.QUERY);
            SearchSingleton.getSearchSingleton().setSearchterm(query);
            doSearch();
        }
    }

    private void doSearch() {
        cardslist = SearchSingleton.getSearchSingleton().search();
    }

    @Override
    public void onBackPressed() {
        SearchSingleton.getSearchSingleton().reset();
        super.onBackPressed();
        finish();

    }


}
