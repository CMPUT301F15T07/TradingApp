package com.sherpasteven.sscte.Views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.sherpasteven.sscte.AddFriendActivity;
import com.sherpasteven.sscte.Controllers.FriendsTabController;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.ViewFriendActivity;
import com.sherpasteven.sscte.Views.RecyclerView.FriendAdapter;

/**
 * Friends tab includes all the friends list, and incorporates many of the useful
 * functions that includes friends. Also manages how the friend list is viewed.
 */
public class FriendsTab extends Fragment implements IView<Model> {

    private FriendsTabController friendstabcontroller;
    private User currentUser;
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private View inflate_view;

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected FriendAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    public FriendsTab() {
        super();
    }

    @SuppressLint("ValidFragment")
    public FriendsTab(User currentUser){
        super();
    }

    @Override
    public void Update(Model model) {
        mAdapter.notifyDataSetChanged();
    }

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    public void navigateToFriend(){
        Intent myIntent = new Intent(getActivity(), AddFriendActivity.class);
        getActivity().startActivity(myIntent);
    }
    public void navigateToView(){
        Intent myIntent = new Intent(getActivity(), ViewFriendActivity.class);
        getActivity().startActivity(myIntent);
    }
    public View getView(){
        return inflate_view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =inflater.inflate(R.layout.friends_tab,container,false);
        currentUser = CurrentProfile.getCurrentProfile().getProfile(this.getContext()).getUser();
        inflate_view = rootView;
        currentUser.addView(this);
        friendstabcontroller = new FriendsTabController(this, currentUser);

        rootView.setTag(TAG);

        ImageButton addItem = (ImageButton) rootView.findViewById(R.id.btnAddFriend);
        addItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), AddFriendActivity.class);
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

        mAdapter = new FriendAdapter(currentUser);
        mAdapter.setTradeState(false);
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
}