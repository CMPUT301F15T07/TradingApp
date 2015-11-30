package com.sherpasteven.sscte.Controllers;

import android.graphics.drawable.BitmapDrawable;
import android.widget.EditText;
import android.widget.ImageView;

import com.sherpasteven.sscte.Models.ISerializer;
import com.sherpasteven.sscte.Models.Image;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.ProfileActivity;
import com.sherpasteven.sscte.R;

/**
 * Controller for the ProfileActivity.
 * Used for updated the Profile model when the
 * fields of the profile are submitted.
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

    /**
     * populate the view text using the
     * previous values of profile.
     */
    public void setFields(){
        final EditText nameText = view.getNameText();
        final EditText cityText = view.getCityText();
        final EditText emailText = view.geEmailText();
        final ImageView imageView = view.getImageProfile();

        nameText.setText(view.getProfile().getUser().getName());
        cityText.setText(view.getProfile().getUser().getLocation());
        emailText.setText(view.getProfile().getUser().getEmail());
        imageView.setImageBitmap(view.getProfile().getUser().constructProfilePic());
        imageView.setTag("Default");
    }

    /**
     * update the profile model using the values
     * of the views.
     */
    public void submit(){
        view.getProfile().getUser().setName(view.getNameText().getText().toString());
        view.getProfile().getUser().setLocation(view.getCityText().getText().toString());
        view.getProfile().getUser().setEmail(view.geEmailText().getText().toString());

        if(view.getImageProfile().getTag().equals("Changed")) {
            view.getProfile().getUser().setProfilePic(new Image(((BitmapDrawable) view.getImageProfile().getDrawable()).getBitmap()));
        }
        setLocalProfile(view.getProfile());
        view.finish();
    }

    private void setLocalProfile(Profile profile) {
        ISerializer<Profile> serializer = new LocalProfileSerializer();
        serializer.Serialize(profile, view);
    }
}
