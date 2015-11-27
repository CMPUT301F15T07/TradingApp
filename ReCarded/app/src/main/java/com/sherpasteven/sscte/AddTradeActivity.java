package com.sherpasteven.sscte;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sherpasteven.sscte.Controllers.AddTradeController;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.TradeComposer;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Models.Friend;
import com.sherpasteven.sscte.Views.IView;
import com.sherpasteven.sscte.Views.RecyclerView.BorrowerTradeListAdapter;
import com.sherpasteven.sscte.Views.RecyclerView.OwnerTradeListAdapter;

public class AddTradeActivity extends AppCompatActivity implements IView<Model> {

    private User user;
    private Friend friend;
    private Trade trade;
    private AddTradeController addtradecontroller;
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 3;
    private static final int DATASET_COUNT = 60;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mYourRecycler;
    protected RecyclerView mTheirRecycler;
    protected BorrowerTradeListAdapter mYourAdapter;
    protected OwnerTradeListAdapter mTheirAdapter;
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
        mYourAdapter = new BorrowerTradeListAdapter(TradeComposer.getTradeComposer().getComponents().getBorrowList(), this);
        mTheirAdapter = new OwnerTradeListAdapter(TradeComposer.getTradeComposer().getComponents().getOwnerList(), this);
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


        TradeComposer.getTradeComposer().getComponents().addView(this);
        setupTradeComposer();

    }

    @Override
    protected void onResume() {
        super.onResume();
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
        TradeComposer.getTradeComposer().getComponents().setBorrower(user);
        TradeComposer.getTradeComposer().getComponents().setOwner(friend);

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
    public void Update(Model model) {
        mTheirAdapter.notifyDataSetChanged();
        mYourAdapter.notifyDataSetChanged();
    }

    public Button getSubmitButton(){
        return (Button) findViewById(R.id.btnEnter);
    }
}
