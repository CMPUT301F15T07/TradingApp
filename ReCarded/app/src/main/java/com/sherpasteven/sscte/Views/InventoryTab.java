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
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
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
public class InventoryTab extends Fragment implements IView<Inventory> {

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;
    private InventoryTabController inventorytabcontroller;
    private View inflate_view;
    private User user;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected CardAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;

    @SuppressLint("ValidFragment")
    public InventoryTab(Inventory inventory) {
        super();
    }

    @Override
    public void Update(Inventory inventory) {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        removeViewsfromCards();
    }

    public void removeViewsfromCards(){
        ArrayList<Card> inventorycards = getUser().getInventory().getCards();
        for(Card card: inventorycards){
            if(card.getViews() != null) {
                card.getViews().clear();
            }
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUser(CurrentProfile.getCurrentProfile().getProfile(this.getContext()).getUser());
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.inventory_tab, container, false);
        inflate_view = rootView;
        rootView.setTag(TAG);

        Inventory inventory = getUser().getInventory();
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

        mAdapter = new CardAdapter(inventory.getCards());
        // Set CardAdapter as the adapter for RecyclerView.
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


    /*
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getUser().getInventory().deleteView(this);
    } */

    public View getView(){
        return inflate_view;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}