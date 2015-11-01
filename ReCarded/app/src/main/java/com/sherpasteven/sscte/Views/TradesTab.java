package com.sherpasteven.sscte.Views;

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
import android.widget.RadioButton;

import com.sherpasteven.sscte.AddCardActivity;
import com.sherpasteven.sscte.AddTradeActivity;
import com.sherpasteven.sscte.EditCardActivity;
import com.sherpasteven.sscte.EditTradeActivity;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.Views.RecyclerView.CardAdapter;
import com.sherpasteven.sscte.Views.RecyclerView.TradeAdapter;

import java.util.ArrayList;
import java.util.List;

public class TradesTab extends Fragment {
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
    private List<Trade> tradelist;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.trades_tab,container,false);
        rootView.setTag(TAG);

        Button addItem = (Button) rootView.findViewById(R.id.btnAddTrade);
        addItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), AddTradeActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        Button editItem = (Button) rootView.findViewById(R.id.btnEditTrade);
        editItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), EditTradeActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

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

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
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

    private void initializeData() {
        tradelist = new ArrayList<>();
        tradelist.add(new Trade(new User("borrower1", "location", "email1"), new User("owner1", "location", "email1")));
        tradelist.add(new Trade(new User("borrower2", "location", "email2"), new User("owner1", "location", "email1")));
        tradelist.add(new Trade(new User("borrower3", "location", "email3"), new User("owner1", "location", "email1")));
        tradelist.add(new Trade(new User("borrower4", "location", "email4"), new User("owner1", "location", "email1")));
        tradelist.add(new Trade(new User("borrower5", "location", "email5"), new User("owner1", "location", "email1")));
        tradelist.add(new Trade(new User("borrower6", "location", "email6"), new User("owner1", "location", "email1")));
        tradelist.add(new Trade(new User("borrower7", "location", "email7"), new User("owner1", "location", "email1")));
        tradelist.add(new Trade(new User("borrower8", "location", "email8"), new User("owner1", "location", "email1")));
        tradelist.add(new Trade(new User("borrower9", "location", "email9"), new User("owner1", "location", "email1")));
        tradelist.add(new Trade(new User("borrower10", "location", "email10"), new User("owner1", "location", "email1")));
        tradelist.add(new Trade(new User("borrower11", "location", "email11"), new User("owner1", "location", "email1")));

    }

}