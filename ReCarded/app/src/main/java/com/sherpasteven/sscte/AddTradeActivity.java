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
import com.sherpasteven.sscte.Models.Trader;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Models.Friend;
import com.sherpasteven.sscte.Views.IView;
import com.sherpasteven.sscte.Views.RecyclerView.BorrowerTradeListAdapter;
import com.sherpasteven.sscte.Views.RecyclerView.OwnerTradeListAdapter;

public class AddTradeActivity extends AppCompatActivity implements IView<Model> {

    private User user;
    private Trader friend;
    private Trade trade;
    private AddTradeController addtradecontroller;
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 3;
    private static final int DATASET_COUNT = 60;
    private int position = 0;
    private boolean isCounter = false;

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
        TradeComposer.getTradeComposer().getComponents().addView(this);

        user = CurrentProfile.getCurrentProfile().getProfile(this).getUser();

        mYourAdapter = new BorrowerTradeListAdapter(TradeComposer.getTradeComposer().getComponents().getBorrowList(), this);
        mTheirAdapter = new OwnerTradeListAdapter(TradeComposer.getTradeComposer().getComponents().getOwnerList(), this);
        // Set CardAdapter as the adapter for RecyclerView.


        mYourRecycler = (RecyclerView) this.findViewById(R.id.YourOfferCards);
        mTheirRecycler = (RecyclerView) this.findViewById(R.id.theirOfferCards);
        mYourLayoutManager = new LinearLayoutManager(this);
        mTheirLayoutManager = new LinearLayoutManager(this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        mYourRecycler.setAdapter(mYourAdapter);
        mTheirRecycler.setAdapter(mTheirAdapter);

        if (getIntent().hasExtra("com.sherpasteven.sscte.counterindex")) {
            position = getIntent().getIntExtra("com.sherpasteven.sscte.counterindex", 0);
            friend = TradeComposer.getTradeComposer().getComponents().getOwner();
            setCounter(true);
        }

        if (getIntent().hasExtra("com.sherpasteven.sscte.friend") || getIntent().hasExtra("com.sherpasteven.sscte.counterindex")) {
            if (!(getIntent().hasExtra("com.sherpasteven.sscte.counterindex"))) {
                friend = new Trader(user.getFriends().get(getIntent().getIntExtra("com.sherpasteven.sscte.friend", 0)), this);
                setupTradeComposer();
            }
        } else {
            Toast.makeText(this, "Invalid friend selected, returning...", Toast.LENGTH_SHORT).show();
            finish();
        }



        setUser(CurrentProfile.getCurrentProfile().getProfile(this).getUser());
        trade = new Trade(new Trader(user, this), new Trader(friend,this));
        addtradecontroller = new AddTradeController(this, trade);

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);




        setTitle("Trade with " + friend.getName());

    }

    public void setPosition(int pos) {
        this.position = pos;
    }

    public void setCounter(boolean counter) {
        this.isCounter = counter;
    }

    public int getPosition() {
        return position;
    }

    public boolean getCounter() {
        return isCounter;
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
        TradeComposer.getTradeComposer().getComponents().setBorrower(new Trader(user, this));
        TradeComposer.getTradeComposer().getComponents().setOwner(new Trader(friend, this));

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
