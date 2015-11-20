package com.sherpasteven.sscte.Controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sherpasteven.sscte.AddCardActivity;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.ISerializer;
import com.sherpasteven.sscte.Models.Image;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;

/**
 * Controller for AddCardActivity.
 */
public class AddCardController extends Controller<AddCardActivity, Profile>{

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

                //retrieve items required for a new card
                String name = view.getNameText().getText().toString();
                int quantity = Integer.parseInt(view.getQuantityText().getText().toString());
                Quality quality = new Quality(Integer.parseInt(view.getQualityText().getText().toString()));
                String catagory = view.getCatagoryText().getSelectedItem().toString();
                String series = view.getSeriesText().getText().toString();
                Boolean tradable = view.getCheckBox().isChecked();
                String comments = view.getCommentsText().getText().toString();
                User owner = CurrentProfile.GetCurrentProfile(view).getUser();
                Bitmap cardimage = null;
                if(view.getImageViewCard().getTag().equals("Changed")) {
                    cardimage = ((BitmapDrawable) view.getImageViewCard().getDrawable()).getBitmap();
                }
                else{ cardimage = BitmapFactory.decodeResource(view.getResources(), R.drawable.img_no_img);}
                    Toast.makeText(view, "Submitted a card...",
                        Toast.LENGTH_SHORT).show();
                model.getUser().getInventory().addCard(new Card(name, new Image(cardimage), quantity, quality, catagory, series, tradable, comments, owner));
                    cardimage.recycle();
                    cardimage = null;

                profileSerializer.Serialize(model, view);
                view.navigateToInventory();
            }
        });

        ImageButton buttonLoadImage = (ImageButton) view.findViewById(R.id.btnCardImage);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                view.loadImage();

            }
        });

    }


}
