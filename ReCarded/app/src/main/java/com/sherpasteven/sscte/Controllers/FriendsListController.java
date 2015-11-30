package com.sherpasteven.sscte.Controllers;

import com.sherpasteven.sscte.FriendListActivity;
import com.sherpasteven.sscte.Models.User;

/**
 * Controller for friendlistactivity.
 *
 */
public class FriendsListController extends Controller<FriendListActivity, User> {

    private final FriendListActivity view;
    private final User model;

    public FriendsListController(FriendListActivity view, User model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }

    @Override
    protected void setListeners(FriendListActivity view) {

    }

}
