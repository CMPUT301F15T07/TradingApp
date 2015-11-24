package com.sherpasteven.sscte;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.IDeSerializer;
import com.sherpasteven.sscte.Models.ISerializer;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;

public class ViewCardActivity extends AppCompatActivity {

    Inventory inventory;
    Card card;
    View v;

    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);
        Intent intent = getIntent();
        int position = intent.getIntExtra("com.sherpasteven.sscte.viewcard", 0);
        inventory = CurrentProfile.GetCurrentProfile(this).getUser().getInventory();
        card = inventory.getCard(position);
        retrieveCardInfo(card);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent1 = new Intent(this, SettingsActivity.class);
            this.startActivity(intent1);
        } else if (id == R.id.edit_card) {
            Intent intent2 = new Intent(this, EditCardActivity.class);
            this.startActivity(intent2);
        } else if (id == R.id.delete_card) {
            AlertDialog confirmDel = ConfirmDelete();
            confirmDel.show();
        }

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
        ImageView viewcard = (ImageView) findViewById(R.id.greyRect);
        viewcard.setImageBitmap(card.constructImage(0));}
    }

    private AlertDialog ConfirmDelete()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete all copies of this card?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        inventory.removeCard(card, card.getQuantity());
                        Profile profile = getLocalProfile();
                        profile.getUser().setInventory(inventory);
                        setLocalProfile(profile);
                        Intent intent = new Intent(v.getContext(), InventoryActivity.class);
                        v.getContext().startActivity(intent);
                        dialog.dismiss();
                    }

                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    private void setLocalProfile(Profile profile) {
        ISerializer<Profile> serializer = new LocalProfileSerializer();
        serializer.Serialize(profile, this);
    }

    /**
     * Serialises the profile (getter) for application registry.
     * @return deserialized profile information.
     */
    private Profile getLocalProfile() {
        IDeSerializer<Profile> deSerializer = new LocalProfileSerializer();
        return deSerializer.Deserialize(null, this);
    }
}
