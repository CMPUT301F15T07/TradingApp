package com.sherpasteven.sscte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sherpasteven.sscte.Controllers.AddTradeController;
import com.sherpasteven.sscte.Controllers.ViewTradeController;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.TradeComposer;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.Views.IView;
import com.sherpasteven.sscte.Views.RecyclerView.BorrowerTradeListAdapter;
import com.sherpasteven.sscte.Views.RecyclerView.BorrowerViewTradeAdapter;
import com.sherpasteven.sscte.Views.RecyclerView.OwnerTradeListAdapter;
import com.sherpasteven.sscte.Views.RecyclerView.OwnerViewTradeAdapter;

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

    TextView nametext;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mYourRecycler;
    protected RecyclerView mTheirRecycler;
    protected BorrowerViewTradeAdapter mYourAdapter;
    protected OwnerViewTradeAdapter mTheirAdapter;
    protected RecyclerView.LayoutManager mYourLayoutManager;
    protected RecyclerView.LayoutManager mTheirLayoutManager;


    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trade);

        mYourRecycler = (RecyclerView) this.findViewById(R.id.YourOfferCards);
        mTheirRecycler = (RecyclerView) this.findViewById(R.id.theirOfferCards);
        mYourLayoutManager = new LinearLayoutManager(this);
        mTheirLayoutManager = new LinearLayoutManager(this);
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        position = getIntent().getIntExtra("com.sherpasteven.sscte.position", 0);

        //setUser(CurrentProfile.getCurrentProfile().getProfile(this).getUser());
        //trade = new Trade(user, friend);
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
        mYourAdapter = new BorrowerViewTradeAdapter(CurrentProfile.getCurrentProfile().getProfile(this).getUser().getTrades().getPendingTrades().get(position).getBorrowList(), this, position);
        mTheirAdapter = new OwnerViewTradeAdapter(CurrentProfile.getCurrentProfile().getProfile(this).getUser().getTrades().getPendingTrades().get(position).getOwnerList(), this, position);
        // Set CardAdapter as the adapter for RecyclerView.
        mYourRecycler.setAdapter(mYourAdapter);
        mTheirRecycler.setAdapter(mTheirAdapter);

        /**
         * FIXME: Move controller information to the controller.
         */
        nametext = getNameText();
        nametext.setText("Trade with " + CurrentProfile.getCurrentProfile().getProfile(this).getUser().getTrades().getPendingTrades().get(position).getOwner().getName());
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