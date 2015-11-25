package com.sherpasteven.sscte;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sherpasteven.sscte.Controllers.Controller;
import com.sherpasteven.sscte.Controllers.ViewCardController;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.IDeSerializer;
import com.sherpasteven.sscte.Models.ISerializer;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Views.IView;

public class ViewCardActivity extends AppCompatActivity implements IView<Card> {


    private Card card;
    View v;
    private ViewCardController c;
    private Integer position;
    private Profile profile;

    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);

        Intent intent = getIntent();
        setProfile(CurrentProfile.getCurrentProfile().getProfile(this));
        setPosition(intent.getIntExtra("com.sherpasteven.sscte.viewcard", 0));

        setCard(getProfile().getUser().getInventoryItem(getPosition()));

        getCard().addView(this);
        getProfile().getUser().addView(this);

        c = new ViewCardController(this, getCard());
        retrieveCardInfo(getCard());
        v = this.findViewById(android.R.id.content);
    }

    /**
     * Generates hamburger menu options.
     * @param menu Menu item to be created.
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_card, menu);
        return true;
    }

    /**
     * OnSelect options for option selected from hamburger menu.
     * @param item Item selected by user.
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        c.menuOptions(id);
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
    public void retrieveCardInfo(Card card){

        //set header
        String tradable;
        if (card.isTradable()){
            tradable = "Tradable";
        } else {
            tradable = "Not Tradable";
        }
        String headertext = card.getCatagory()+ " - " + card.getSeries()+ " - " + tradable;
        TextView header = (TextView) findViewById(R.id.txtStatus);
        header.setText(headertext);

        //set name
        TextView cardname = (TextView) findViewById(R.id.txtName);
        cardname.setText(card.getName());

        //set quality
        TextView cardquality = (TextView) findViewById(R.id.qualityInfo);
        cardquality.setText(String.valueOf(card.getQuality().getQuality()));

        //set quantity
        TextView cardquantity = (TextView) findViewById(R.id.quantityInfo);
        cardquantity.setText(String.valueOf(card.getQuantity()));

        //set comments
        TextView cardcomments = (TextView) findViewById(R.id.commentsInfo);
        cardcomments.setText(card.getComments());

        if(!card.getImages().isEmpty()){
            ImageView viewcard = getImageCard();
            viewcard.setImageBitmap(card.constructImage(0));
            }
        }


    /**
     * Serialises the profile (getter) for application registry.
     * @return deserialized profile information.
     */
    private Profile getLocalProfile() {
        IDeSerializer<Profile> deSerializer = new LocalProfileSerializer();
        return deSerializer.Deserialize(null, this);
    }

    public ImageView getImageCard(){
        return (ImageView) findViewById(R.id.greyRect);
    }


    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public void Update(Card card) { //model is user, not card...
        retrieveCardInfo(getCard());
    }

    // development for AddCardActivity update card
    public void Update(User user) {
        retrieveCardInfo(getCard());
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
