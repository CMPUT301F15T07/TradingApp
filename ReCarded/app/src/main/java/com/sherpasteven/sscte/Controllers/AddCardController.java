package com.sherpasteven.sscte.Controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sherpasteven.sscte.AddCardActivity;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Image;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;

import java.util.ArrayList;

/**
 * Controller for AddCardActivity. Mainly
 * handles the submit button on click event
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
            /**
             * Handles the model changes when the submit add card
             * button is hit such as constructing the card and
             * adding it to the user's inventory
             * @param v
             */
            @Override
            public void onClick(View v) {

                //retrieve items required for a new card
                String name = view.getNameText().getText().toString();
                int quantity = 0;
                try{
                    quantity = Integer.parseInt(view.getQuantityText().getText().toString());
                }catch(NumberFormatException e) {
                    quantity = 1;
                }
                Quality quality = new Quality(0);
                try {
                    quality.setQuality(Integer.parseInt(view.getQualityText().getText().toString()));
                } catch(NumberFormatException e){
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
                User owner = model.getUser();
                Bitmap cardimage = null;
                ArrayList<Image> cardImages = new ArrayList<Image>();
                if(view.getImageViewCard().getTag().equals("Changed")) {
                    for(Bitmap bmp: view.getCardImages()) {
                        //cardimage = ((BitmapDrawable) view.getImageViewCard().getDrawable()).getBitmap();
                        cardImages.add(new Image(bmp));
                    }
                }
                else {
                    cardimage = BitmapFactory.decodeResource(view.getResources(), R.drawable.img_no_img);
                    cardImages.add(new Image(cardimage));
                }
                    Toast.makeText(view, "Submitted a card...",
                        Toast.LENGTH_SHORT).show();
                Card card = new Card(name, quantity, quality, catagory, series, tradable, comments, cardImages, owner);
                model.getUser().addInventoryItem(card);
                //model.getUser().addInventoryItem(new Card(name, new Image(cardimage), quantity, quality, catagory, series, tradable, comments, owner));
                    //cardimage.recycle();
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

        Button addmedia = (Button) view.findViewById(R.id.addmoreimages);
        addmedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.loadImage();
            }
        });

    }


}
