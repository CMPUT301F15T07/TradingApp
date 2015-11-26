package com.sherpasteven.sscte.Controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sherpasteven.sscte.AddCardActivity;
import com.sherpasteven.sscte.EditCardActivity;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Image;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;

/**
 * Controller for EditCardActivity.
 */
public class EditCardController extends Controller<EditCardActivity, Profile> {

    private final EditCardActivity view;
    private final Profile model;
    private LocalProfileSerializer profileSerializer = new LocalProfileSerializer();

    /**
     * Instantiates the controller on the proper activity.
     * @param view
     * @param model
     */
    public EditCardController(EditCardActivity view, Profile model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }

    @Override
    protected void setListeners(final EditCardActivity view) {
        Button submitButton = view.getEnterButton();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //retrieve items required for a new card
                String name = view.getNameText().getText().toString();
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(view.getQuantityText().getText().toString());
                } catch (NumberFormatException e) {
                    quantity = 1;
                }
                Quality quality = new Quality(0);
                try {
                    quality.setQuality(Integer.parseInt(view.getQualityText().getText().toString()));
                } catch (NumberFormatException e) {
                    quality.setQuality(0);
                }
                String catagory = view.getCatagoryText().getSelectedItem().toString();
                String series = view.getSeriesText().getText().toString();
                if (series.equals("")) {
                    series = "Unknown";
                }
                Boolean tradable = view.getCheckBox().isChecked();
                String comments = view.getCommentsText().getText().toString();
                if (comments.equals("")) {
                    comments = "No comments entered for this card...";
                }

                if (view.getImageViewCard().getTag().equals("Changed")) {
                    Bitmap cardimage = ((BitmapDrawable) view.getImageViewCard().getDrawable()).getBitmap();
                    model.getUser().getInventoryItem(view.getPosition()).setImageByPosition(new Image(cardimage), 0);
                    cardimage.recycle();
                    cardimage = null;
                }
                Toast.makeText(view, "Updated a card...",
                        Toast.LENGTH_SHORT).show();

                //model.getUser().getInventory().addCard(new Card(name, new Image(cardimage), quantity, quality, catagory, series, tradable, comments, owner));
                model.getUser().getInventoryItem(view.getPosition()).setName(name);
                model.getUser().getInventoryItem(view.getPosition()).setQuantity(quantity);
                model.getUser().getInventoryItem(view.getPosition()).setQuality(quality);
                model.getUser().getInventoryItem(view.getPosition()).setCatagory(catagory);
                model.getUser().getInventoryItem(view.getPosition()).setSeries(series);
                model.getUser().getInventoryItem(view.getPosition()).setTradable(tradable);
                model.getUser().getInventoryItem(view.getPosition()).setComments(comments);

                //model.getUser().getInventory().notifyViews();
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
