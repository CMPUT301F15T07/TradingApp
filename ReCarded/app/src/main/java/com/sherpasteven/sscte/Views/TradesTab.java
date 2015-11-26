package com.sherpasteven.sscte.Views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sherpasteven.sscte.AddCardActivity;
import com.sherpasteven.sscte.AddTradeActivity;
import com.sherpasteven.sscte.Controllers.TradesTabController;
import com.sherpasteven.sscte.EditCardActivity;
import com.sherpasteven.sscte.EditTradeActivity;
import com.sherpasteven.sscte.FriendListActivity;
import com.sherpasteven.sscte.Models.TradeComposer;
import com.sherpasteven.sscte.Models.TradeLog;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.Views.RecyclerView.CardAdapter;
import com.sherpasteven.sscte.Views.RecyclerView.TradeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the TradesTab system using the TradeLog model.
 */
public class TradesTab extends Fragment implements IView<TradeLog> {
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
    private List<Trade> tradelist;

    private TradeLog trades;
    private TradesTabController tradestabcontroller;
    private View inflate_view;

    public TradesTab(TradeLog trades){
        super();
        this.trades = trades;
    }

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RadioButton mLinearLayoutRadioButton;
    protected RadioButton mGridLayoutRadioButton;

    protected RecyclerView mRecyclerView;
    protected TradeAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initializeData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.trades_tab,container,false);

        inflate_view = rootView;
        trades.addView(this);

        tradestabcontroller = new TradesTabController(this, trades);
        rootView.setTag(TAG);

        // BEGIN_INCLUDE(initializeRecyclerView)
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        mAdapter = new TradeAdapter(tradelist);
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)

        return rootView;
    }

    @Override
    public void Update(TradeLog tradeLog) {

    }

    public void navigateToAddTradeActivity(){
        // process flow for the activity is like this:
        // enter list of friends
        // select the list of friends and instantiate activity there
        // once you're in, you can select the + and go into cardlistactivity
        // select cards from there
        Intent myIntent = new Intent(getActivity(), FriendListActivity.class);
        Toast.makeText(this.getContext(), "Adding a trade...", Toast.LENGTH_SHORT).show();
        getActivity().startActivity(myIntent);
    }
    public void navigateToEditTradeActivity(){
        Intent myIntent = new Intent(getActivity(), EditTradeActivity.class);
        Toast.makeText(this.getContext(), "Editing selected trade...", Toast.LENGTH_SHORT).show();
        getActivity().startActivity(myIntent);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(TradeComposer.getTradeComposer().getComponents() != null){
            TradeComposer.getTradeComposer().getComponents().getViews().clear();
            TradeComposer.getTradeComposer().resetComponents();
        }
    }

    public View getView(){
        return inflate_view;
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {

        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    /** Initialises data for trades tab given trade objects.
     * Completed with respect to tradelist object.
     * FIXME: Change system for dynamic trade list loading.
     * FIXME: Implement tradelist as user-relevant trade list structure.
     */
    private void initializeData() {
        tradelist = new ArrayList<>();
        Context context = this.getContext();
        tradelist.add(new Trade(new User("borrower1", "location", "email1", context), new User("owner1", "location", "email1", context)));
        tradelist.add(new Trade(new User("borrower2", "location", "email2", context), new User("owner1", "location", "email1", context)));
        tradelist.add(new Trade(new User("borrower3", "location", "email3", context), new User("owner1", "location", "email1", context)));
        tradelist.add(new Trade(new User("borrower4", "location", "email4", context), new User("owner1", "location", "email1", context)));
        tradelist.add(new Trade(new User("borrower5", "location", "email5", context), new User("owner1", "location", "email1", context)));
        tradelist.add(new Trade(new User("borrower6", "location", "email6", context), new User("owner1", "location", "email1", context)));
        tradelist.add(new Trade(new User("borrower7", "location", "email7", context), new User("owner1", "location", "email1", context)));
        tradelist.add(new Trade(new User("borrower8", "location", "email8", context), new User("owner1", "location", "email1", context)));
        tradelist.add(new Trade(new User("borrower9", "location", "email9", context), new User("owner1", "location", "email1", context)));
        tradelist.add(new Trade(new User("borrower10", "location", "email10", context), new User("owner1", "location", "email1", context)));
        tradelist.add(new Trade(new User("borrower11", "location", "email11", context), new User("owner1", "location", "email1", context)));

    }

}