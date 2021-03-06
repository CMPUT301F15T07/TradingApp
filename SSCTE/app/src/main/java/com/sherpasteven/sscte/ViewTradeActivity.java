package com.sherpasteven.sscte;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.sherpasteven.sscte.Controllers.ViewTradeController;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Views.IView;
import com.sherpasteven.sscte.Views.RecyclerView.BorrowerViewTradeAdapter;
import com.sherpasteven.sscte.Views.RecyclerView.OwnerViewTradeAdapter;

import java.util.ArrayList;

public class ViewTradeActivity extends AppCompatActivity implements IView<Model> {

    private User user;
    private User friend;
    private Trade trade;
    private ViewTradeController viewtradecontroller;
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 3;
    private static final int DATASET_COUNT = 60;
    int position;

    Button acceptButton;
    Button declineButton;
    Button counterofferButton;
    TextView statusInfo;

    TextView nametext;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mYourRecycler;
    protected RecyclerView mTheirRecycler;
    //protected BorrowerViewTradeAdapter mYourAdapter;
    //protected OwnerViewTradeAdapter mTheirAdapter;
    protected RecyclerView.Adapter mYourAdapter;
    protected RecyclerView.Adapter mTheirAdapter;
    protected RecyclerView.LayoutManager mYourLayoutManager;
    protected RecyclerView.LayoutManager mTheirLayoutManager;


    /**
     * (not Javadoc)
     *
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trade);
        user = CurrentProfile.getCurrentProfile().getProfile(this).getUser();
        mYourRecycler = (RecyclerView) this.findViewById(R.id.YourOfferCards);
        mTheirRecycler = (RecyclerView) this.findViewById(R.id.theirOfferCards);
        mYourLayoutManager = new LinearLayoutManager(this);
        mTheirLayoutManager = new LinearLayoutManager(this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        position = getIntent().getIntExtra("com.sherpasteven.sscte.position", 0);

        //setUser(CurrentProfile.getCurrentProfile().getProfile(this).getUser());
        //trade = new Trade(user, friend);
        //this crashes
        ArrayList<Trade> tradeList = new ArrayList<>();
        tradeList.addAll(CurrentProfile.getCurrentProfile().getProfile(this).getUser().getTrades().getPendingTrades());
        tradeList.addAll(CurrentProfile.getCurrentProfile().getProfile(this).getUser().getTrades().getPastTrades());

        trade = tradeList.get(position);

        viewtradecontroller = new ViewTradeController(this, trade);

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        /**
         * FIXME: Only gets pending trades at the moment.
         */

        if (user.getProfileId().equals(trade.getOwner().getProfileId())) {
            //user is the owner
            mYourAdapter = new OwnerViewTradeAdapter(trade.getOwnerList(), this, position);
            mTheirAdapter = new BorrowerViewTradeAdapter(trade.getBorrowList(), this, position);
        } else {
            //user is the borrower
            mYourAdapter = new BorrowerViewTradeAdapter(trade.getBorrowList(), this, position);
            mTheirAdapter = new OwnerViewTradeAdapter(trade.getOwnerList(), this, position);
        }
        // mYourAdapter = new BorrowerViewTradeAdapter(trade.getBorrowList(), this, position);
        // mTheirAdapter = new OwnerViewTradeAdapter(trade.getOwnerList(), this, position);
        // Set CardAdapter as the adapter for RecyclerView.
        mYourRecycler.setAdapter(mYourAdapter);
        mTheirRecycler.setAdapter(mTheirAdapter);

        /**
         * FIXME: Move controller information to the controller.
         */
        nametext = getNameText();

        //we want to set the trade text to the friend
        if (trade.getOwner().getProfileId().equals(user.getProfileId())) {
            nametext.setText("Trade with " + trade.getBorrower().getName());
        } else {
            nametext.setText("Trade with " + trade.getOwner().getName());
        }
        updateButtons();

    }

    public TextView getStatusInfo() {
        return (TextView) findViewById(R.id.StatusInfo);
    }

    public Button getAcceptButton() {
        return (Button) findViewById(R.id.btnAccept);
    }

    public Button getDeclineButton() {
        return (Button) findViewById(R.id.btnDecline);
    }

    public Button getCounterOfferButton() {
        return (Button) findViewById(R.id.btnCounterOffer);
    }

    public void updateButtons() {
        statusInfo = getStatusInfo();
        acceptButton = getAcceptButton();
        declineButton = getDeclineButton();
        counterofferButton = getCounterOfferButton();

        statusInfo.setText("Trade Status: " + trade.getStatus().toString());

        if (trade != null) {
            if (trade.getStatus().equals("PENDING")) {
                if ((trade.getBorrower().getProfileId().equals(
                        CurrentProfile.getCurrentProfile().getProfile(this).getProfileId()))) {
                    // if you are the borrower, you can only decline -- change color of other trade buttons
                    acceptButton.setBackgroundResource(R.drawable.trade_options_top_grey);
                    acceptButton.setTextColor(Color.parseColor("#484848"));
                    acceptButton.setClickable(false);
                    counterofferButton.setBackgroundResource(R.drawable.trade_options_bot_grey);
                    counterofferButton.setTextColor(Color.parseColor("#484848"));
                    counterofferButton.setClickable(false);
                }
            } else if (trade.getStatus().equals("DECLINED") || trade.getStatus().equals("ACCEPTED")) {
                acceptButton.setBackgroundResource(R.drawable.trade_options_top_grey);
                acceptButton.setClickable(false);
                acceptButton.setTextColor(Color.parseColor("#484848"));
                declineButton.setBackgroundResource(R.drawable.trade_options_mid_grey);
                declineButton.setClickable(false);
                declineButton.setTextColor(Color.parseColor("#484848"));
                counterofferButton.setBackgroundResource(R.drawable.trade_options_bot_grey);
                counterofferButton.setTextColor(Color.parseColor("#484848"));
                counterofferButton.setClickable(false);
            }
        }
    }

    public TextView getNameText() {
        return (TextView) findViewById(R.id.txtName);
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

    private void setUser(User currentuser) {
        user = currentuser;
    }

    @Override
    public void Update(Model model) {
        mTheirAdapter.notifyDataSetChanged();
        mYourAdapter.notifyDataSetChanged();
    }

}
