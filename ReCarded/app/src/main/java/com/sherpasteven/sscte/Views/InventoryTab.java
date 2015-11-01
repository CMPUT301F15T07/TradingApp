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
import com.sherpasteven.sscte.EditCardActivity;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.Views.RecyclerView.CustomAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
 * {@link GridLayoutManager}.
 */
public class InventoryTab extends Fragment {

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
    private List<Card> cardlist;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RadioButton mLinearLayoutRadioButton;
    protected RadioButton mGridLayoutRadioButton;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
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
        View rootView = inflater.inflate(R.layout.inventory_tab, container, false);
        rootView.setTag(TAG);

            Button addItem = (Button) rootView.findViewById(R.id.btnAddItem);
            addItem.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), AddCardActivity.class);
                    getActivity().startActivity(myIntent);
                }
            });

            Button editItem = (Button) rootView.findViewById(R.id.btnEditItem);
            editItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), EditCardActivity.class);
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

        mAdapter = new CustomAdapter(cardlist);
        // Set CustomAdapter as the adapter for RecyclerView.
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

        mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
        mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    // This method creates an ArrayList that has three Person objects
    // Checkout the project associated with this tutorial on Github if
    // you want to use the same images.
    private void initializeData() {
        cardlist = new ArrayList<>();
        cardlist.add(new Card("Item 0", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 1", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 2", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 3", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 4", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 5", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 6", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 7", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 8", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 9", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 10", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 11", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 12", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 13", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 14", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 15", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 16", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 17", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 18", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 19", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 20", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 21", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 22", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 23", R.drawable.logo, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
    }

}
