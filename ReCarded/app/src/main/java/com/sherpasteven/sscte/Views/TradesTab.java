package com.sherpasteven.sscte.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sherpasteven.sscte.Controllers.TradesTabController;
import com.sherpasteven.sscte.EditTradeActivity;
import com.sherpasteven.sscte.FriendListActivity;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.ProfileSynchronizer;
import com.sherpasteven.sscte.Models.SynchronizeSingleton;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.TradeComposer;
import com.sherpasteven.sscte.Models.TradeLog;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.Views.RecyclerView.TradeAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Implements the TradesTab system using the TradeLog model.
 */
public class TradesTab extends Fragment implements IView<Model> {
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
    private List<Trade> tradelist;

    private TradeLog trades;
    private TradesTabController tradestabcontroller;
    private View inflate_view;

    public TradesTab() {
        super();
        //User currentUser = CurrentProfile.getCurrentProfile().getProfile(this.getContext()).getUser();
        //trades = currentUser.getTrades();
    }

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
    protected AppCompatActivity hostActivity;
    protected LocalProfileSerializer localProfileSerializer;
    protected View view;
/*
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            synchronizeTrades();
        }
        else {  }
    } */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User currentUser = CurrentProfile.getCurrentProfile().getProfile(this.getContext()).getUser();
        trades = currentUser.getTrades();
        dynamicLoad();
    }

    public void dynamicLoad() {
        //tradelist = createTradesList();
        tradelist = CurrentProfile.getCurrentProfile().getProfile(this.getContext()).getUser().getTrades().getPendingTrades();


        if (mAdapter != null) mAdapter.notifyDataSetChanged();
        // doesn't get the other trades
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.trades_tab,container,false);
        hostActivity = (AppCompatActivity) rootView.getContext();
        inflate_view = rootView;
        trades.addView(this);
        localProfileSerializer = new LocalProfileSerializer();

        //ProfileSynchronizer profileSynchronizer = SynchronizeSingleton.GetSynchronize(hostActivity);
        //profileSynchronizer.addView(this);

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

        mAdapter = new TradeAdapter(tradelist, CurrentProfile.getCurrentProfile().getProfile(hostActivity).getUser());
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)

        return rootView;
    }

    @Override
    public void Update(Model model) {
        if (model instanceof ProfileSynchronizer) {
            ProfileSynchronizer profileSynchronizer = SynchronizeSingleton.GetSynchronize(hostActivity);
            profileSynchronizer.UpdateProfile();
            localProfileSerializer.Serialize(CurrentProfile.getCurrentProfile().getProfile(hostActivity), hostActivity);
        }
        dynamicLoad();
        //tradelist = createTradesList();
        mAdapter.notifyDataSetChanged();
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

    public void synchronizeTrades() {
        ProfileSynchronizer profileSynchronizer = SynchronizeSingleton.GetSynchronize(hostActivity);
        profileSynchronizer.SynchronizeProfile();
    }

    @Override
    public void onResume() {
        super.onResume();
        //ProfileSynchronizer profileSynchronizer = SynchronizeSingleton.GetSynchronize(hostActivity);
        //profileSynchronizer.SynchronizeProfile();
        //synchronizeTrades(); trades should be synchronized in the InventoryActivity
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

    public ArrayList<Trade> createTradesList(){
        ArrayList<Trade> pending = (ArrayList<Trade>) CurrentProfile.getCurrentProfile().getProfile(this.getContext()).getUser().getTrades().getPendingTrades().clone();
        ArrayList<Trade> past = (ArrayList<Trade>) CurrentProfile.getCurrentProfile().getProfile(this.getContext()).getUser().getTrades().getPastTrades().clone();

        Collections.reverse(pending);
        Collections.reverse(past);

        for(Trade trade: past){
            pending.add(trade);
        }

        //for()

        return pending;
    }
}