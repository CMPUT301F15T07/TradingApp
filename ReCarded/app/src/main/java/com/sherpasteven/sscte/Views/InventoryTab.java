package com.sherpasteven.sscte.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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
import com.sherpasteven.sscte.Controllers.InventoryTabController;
import com.sherpasteven.sscte.EditCardActivity;
import com.sherpasteven.sscte.InventoryActivity;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.ViewCardActivity;
import com.sherpasteven.sscte.Views.RecyclerView.CardAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
 * {@link GridLayoutManager}.
 */
@SuppressLint("ValidFragment")
public class InventoryTab extends Fragment implements IView<Inventory>{

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
    private Inventory inventory;
    private InventoryTabController inventorytabcontroller;
    private View inflate_view;

    @SuppressLint("ValidFragment")
    public InventoryTab(Inventory inventory){
        super();
        this.inventory = inventory;
    }
    @Override
    public void Update(Inventory inventory) {

    }
    private List<Card> cardlist;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected CardAdapter mAdapter;
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
        inflate_view = rootView;
        rootView.setTag(TAG);
        inventory.addView(this);
        inventorytabcontroller = new InventoryTabController(this, inventory);



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

        mAdapter = new CardAdapter(cardlist);
        // Set CardAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // END_INCLUDE(initializeRecyclerView)

        return rootView;
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
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

    private void initializeData() {
        cardlist = new ArrayList<>();
        /*
        cardlist.add(new Card("Item 0", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 1", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 2", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 3", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 4", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 5", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 6", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 7", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 8", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 9", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 10", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 11", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 12", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        cardlist.add(new Card("Item 13", R.drawable.splash_page, 4, new Quality(1), "Test", "Test", true, "Test", new User("Test", "Test", "Test")));
        */
    }

    public void navigateToAddCardActivity(){
        Intent myIntent = new Intent(getActivity(), AddCardActivity.class);
        getActivity().startActivity(myIntent);
    }
    public void navigateToEditCardActivity(){
        Intent myIntent = new Intent(getActivity(), EditCardActivity.class);
        getActivity().startActivity(myIntent);
    }
    public void navigateToViewCardActivity(){
        Intent myIntent = new Intent(getActivity(), ViewCardActivity.class);
        getActivity().startActivity(myIntent);
    }

    public View getView(){
        return inflate_view;
    }
}
