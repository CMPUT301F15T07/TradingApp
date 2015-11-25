package com.sherpasteven.sscte;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sherpasteven.sscte.Controllers.AddTradeController;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.TradeComposer;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Views.IView;
import com.sherpasteven.sscte.Views.RecyclerView.CardAdapter;
import com.sherpasteven.sscte.Views.RecyclerView.TradeListAdapter;

public class AddTradeActivity extends AppCompatActivity implements IView {

    private User user;
    private User friend;
    private Trade trade;
    private AddTradeController addtradecontroller;
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mYourRecycler;
    protected RecyclerView mTheirRecycler;
    protected TradeListAdapter mYourAdapter;
    protected TradeListAdapter mTheirAdapter;
    protected RecyclerView.LayoutManager mYourLayoutManager;
    protected RecyclerView.LayoutManager mTheirLayoutManager;


    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trade);

        mYourRecycler = (RecyclerView) this.findViewById(R.id.YourOfferCards);
        mTheirRecycler = (RecyclerView) this.findViewById(R.id.theirOfferCards);
        mYourLayoutManager = new LinearLayoutManager(this);
        mTheirLayoutManager = new LinearLayoutManager(this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        setUser(CurrentProfile.getCurrentProfile().getProfile(this).getUser());
        trade = new Trade(user, friend);
        addtradecontroller = new AddTradeController(this, trade);

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        mYourAdapter = new TradeListAdapter(TradeComposer.getTradeComposer().getComponents().getBorrowList());
        mTheirAdapter = new TradeListAdapter(TradeComposer.getTradeComposer().getComponents().getOwnerList());
        // Set CardAdapter as the adapter for RecyclerView.
        mYourRecycler.setAdapter(mYourAdapter);
        mTheirRecycler.setAdapter(mTheirAdapter);

        if (getIntent().hasExtra("com.sherpasteven.sscte.friend")) {
            friend = user.getFriends().get(getIntent().getIntExtra("com.sherpasteven.sscte.friend", 0));
            setTitle("Trade with " + friend.getName());
        } else {
            Toast.makeText(this, "Invalid friend selected, returning...", Toast.LENGTH_SHORT).show();
            finish();
        }
        setupTradeComposer();
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {

        mYourLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        mTheirLayoutManager = new GridLayoutManager(this, SPAN_COUNT);
        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
        mYourRecycler.setLayoutManager(mYourLayoutManager);
        mTheirRecycler.setLayoutManager(mTheirLayoutManager);

    }

    private void setupTradeComposer() {
        TradeComposer.getTradeComposer().getComponents().setOwner(friend);
        TradeComposer.getTradeComposer().getComponents().setBorrower(user);
    }

    public ImageButton getItemUserBtn() {
        return (ImageButton) this.findViewById(R.id.btnAddUser);
    }

    public ImageButton getItemFriendBtn() {
        return (ImageButton) this.findViewById(R.id.btnAddOther);
    }


    private void setUser(User currentuser) {
        user = currentuser;
    }

    @Override
    public void Update(Object o) {

    }
}
