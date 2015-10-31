package com.sherpasteven.sscte.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sherpasteven.sscte.AddFriendActivity;
import com.sherpasteven.sscte.Controllers.FriendsTabController;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;

public class FriendsTab extends Fragment implements IView<User> {

    private FriendsTabController friendstabcontroller;
    private View inflate_view;
    private User currentUser;


    public FriendsTab(User currentUser){
        super();
        this.currentUser = currentUser;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.friends_tab,container,false);
        inflate_view=v;


        currentUser.addView(this);
        friendstabcontroller = new FriendsTabController(this, currentUser);
        return v;
    }
    public void navigateToFriend(){
        Intent myIntent = new Intent(getActivity(), AddFriendActivity.class);
        getActivity().startActivity(myIntent);
    }
    public View getView(){
        return inflate_view;
    }

    @Override
    public void Update(User user) {

    }
}