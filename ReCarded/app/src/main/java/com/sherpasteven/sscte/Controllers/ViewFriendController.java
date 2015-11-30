package com.sherpasteven.sscte.Controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageButton;

import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Friend;
import com.sherpasteven.sscte.Models.Friends;
import com.sherpasteven.sscte.Models.ISerializer;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.TradeLog;
import com.sherpasteven.sscte.ViewFriendActivity;
import com.sherpasteven.sscte.ViewTradeActivity;

/**
 * Created by salim_000 on 2015-11-28.
 */

/**
 * Controller used to for the ViewFriendActivity, in which we use
 * to implement the delete friend button.
 */
public class ViewFriendController extends Controller<ViewFriendActivity, Friend>{

    private final ViewFriendActivity view;
    private final Friend model;

    ImageButton deleteFriendButton;

    public ViewFriendController(ViewFriendActivity view, Friend model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }

    /**
     * Set the listeners for the viewFriendActivity buttons.
     * @param view view to set listeners on.
     */
    @Override
    protected void setListeners(ViewFriendActivity view) {
        deleteFriendButton = view.getDeclineButton();
        deleteFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog confirmDel = ConfirmDelete();
                confirmDel.show();
            }
        });
    }

    /**
     * Alert dialog to ask whether to remove friend or not from the user friend list.
     * @return
     */
    private AlertDialog ConfirmDelete()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(view)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete Friend?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Friend friend = view.getFriend();
                        Profile profile = CurrentProfile.getCurrentProfile().getProfile(view);
                        profile.getUser().removeFriend(friend);
                        setLocalProfile(profile);
                        view.finish();
                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    /**
     * Updates the profiles data through out the app, by saving to the data file.
     * @param profile
     */
    private void setLocalProfile(Profile profile) {
        ISerializer<Profile> serializer = new LocalProfileSerializer();
        serializer.Serialize(profile, view);
    }
}
