package com.sherpasteven.sscte.Controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sherpasteven.sscte.EditCardActivity;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.ISerializer;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.SettingsActivity;
import com.sherpasteven.sscte.ViewCardActivity;

import java.util.ArrayList;

/**
 * Created by joshua on 23/11/15.
 */

/**
 * controller used to implement functionality used in ViewCardActivity.
 * Includes Menu function and
 */
public class ViewCardController extends Controller<ViewCardActivity, Card>{

    ViewCardActivity view;
    Card model;
    int deletequantity;

    public ViewCardController(ViewCardActivity view, Card model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }

    @Override
    protected void setListeners(ViewCardActivity view) {

    }

    public void menuOptions(int id){

        if (id == R.id.action_settings) {
            Intent intent1 = new Intent(view, SettingsActivity.class);
            view.startActivity(intent1);
        } else if (id == R.id.edit_card) {
            Intent intent2 = new Intent(view, EditCardActivity.class);
            intent2.putExtra("pointer", view.getPosition());
            view.startActivity(intent2);
        } else if (id == R.id.delete_card) {
            AlertDialog confirmDel = ConfirmDelete().create();
            confirmDel.show();
        } else if (id == R.id.clone_card){
            AlertDialog confirmclone = ConfirmClone();
            confirmclone.show();
        }

    }


    private AlertDialog.Builder ConfirmDelete()
    {

        AlertDialog.Builder myQuittingDialogBox =new AlertDialog.Builder(view);

        myQuittingDialogBox.setTitle("Delete selected quantity?");
        //myQuittingDialogBox.setMessage("Are you sure you want to delete all copies of this card?");
        myQuittingDialogBox.setSingleChoiceItems(getQuantityList(), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                 deletequantity = item;
                 deletequantity++; //iterate
            }
        });
        myQuittingDialogBox.setPositiveButton("Delete", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                Card card = view.getCard();
                Profile profile = CurrentProfile.getCurrentProfile().getProfile(view);
                view.finish();
                profile.getUser().removeInventoryItem(card, deletequantity);
                setLocalProfile(profile);


            }

        });


        myQuittingDialogBox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        myQuittingDialogBox.create();
        return myQuittingDialogBox;


    }
    private AlertDialog ConfirmClone()
    {
        AlertDialog myCloningDialogBox =new AlertDialog.Builder(view)
                //set message, title, and icon
                .setTitle("Clone")
                .setMessage("Add a copy of this card to your inventory?")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Card card = view.getCard();
                        Profile profile = CurrentProfile.getCurrentProfile().getProfile(view);
                        view.finish();
                        profile.getUser().getInventory().addCard(card);
                        setLocalProfile(profile);

                    }

                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myCloningDialogBox;


    }
    private void setLocalProfile(Profile profile) {
        ISerializer<Profile> serializer = new LocalProfileSerializer();
        serializer.Serialize(profile, view);
    }
    private CharSequence[] getQuantityList(){
        int i;
        CharSequence[] quantityArray = new CharSequence[model.getQuantity()];

        for(i = 0; i<model.getQuantity();i++) {
            quantityArray[i] = String.valueOf(i+1);
        }
        return quantityArray;

    }


}
