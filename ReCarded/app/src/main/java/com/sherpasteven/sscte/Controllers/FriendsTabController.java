package com.sherpasteven.sscte.Controllers;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.sherpasteven.sscte.AddFriendActivity;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.Views.FriendsTab;

/**
 * Created by jake on 2015-10-31.
 */
public class FriendsTabController extends Controller<FriendsTab, User> {
    private final FriendsTab view;
    private final User model;

    public FriendsTabController(FriendsTab view, User model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }
    @Override
    protected void setListeners(final FriendsTab view) {
        Button addFriend = (Button) view.getView().findViewById(R.id.btnAddFriend);
        addFriend.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.navigateToFriend();
            }
        });

    }
}