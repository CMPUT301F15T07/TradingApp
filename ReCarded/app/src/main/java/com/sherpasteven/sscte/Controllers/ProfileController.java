package com.sherpasteven.sscte.Controllers;

import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.ProfileActivity;

/**
 * Controller for ProfileActivity.
 */
public class ProfileController extends Controller<ProfileActivity, Profile> {
    private final ProfileActivity view;
    private final Profile model;

    public ProfileController(ProfileActivity view, Profile model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }
    @Override
    protected void setListeners(ProfileActivity view) {

    }
}
