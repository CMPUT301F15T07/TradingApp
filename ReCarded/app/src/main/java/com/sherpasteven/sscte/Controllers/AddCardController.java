package com.sherpasteven.sscte.Controllers;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sherpasteven.sscte.AddCardActivity;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.User;

/**
 * Controller for AddCardActivity.
 */
public class AddCardController extends Controller<AddCardActivity, Profile> {

    private final AddCardActivity view;
    private final Profile model;
    private LocalProfileSerializer profileSerializer = new LocalProfileSerializer();

    /**
     * Instantiates the controller on the proper activity.
     * @param view
     * @param model
     */
    public AddCardController(AddCardActivity view, Profile model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }
    @Override
    protected void setListeners(final AddCardActivity view) {
        Button submitButton = view.getEnterButton();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set profile can be removed once serialization is operational
                //used to be model.generateProfile(view);

                /*
                 public Card(String name, int quantity, Quality quality, String catagory,
                String series, Boolean tradable, String comments , User owner){
                 */


                String name = view.getNameText().getText().toString();
                int quantity = Integer.parseInt(view.getQuantityText().getText().toString());
                Quality quality = new Quality(Integer.parseInt(view.getQualityText().getText().toString()));
                String catagory = view.getCatagoryText().getText().toString();
                String series = view.getSeriesText().getText().toString();
                Boolean tradable = view.getCheckBox().isChecked();
                String comments = view.getCommentsText().getText().toString();
                User owner = CurrentProfile.GetCurrentProfile(view).getUser();
                Toast.makeText(view, "Submitted a card...",
                        Toast.LENGTH_SHORT).show();
                model.getUser().getInventory().addCard(new Card(name, quantity, quality, catagory, series, tradable, comments, owner));
                profileSerializer.Serialize(model, view);
                view.navigateToInventory();
            }
        });

    }

}
