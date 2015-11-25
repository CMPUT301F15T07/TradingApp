package com.sherpasteven.sscte.Controllers;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;

import com.sherpasteven.sscte.AddTradeActivity;
import com.sherpasteven.sscte.CardTradeActivity;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Trade;

import java.io.Serializable;

/**
 * Created by ansonli on 2015-11-25.
 */
public class AddTradeController extends Controller<AddTradeActivity, Trade> {

    private final AddTradeActivity view;
    private final Trade model;

    ImageButton addUser;
    ImageButton addOther;

    private LocalProfileSerializer profileSerializer = new LocalProfileSerializer();

    /**
     * Instantiates the controller on the proper activity.
     *
     * @param view
     * @param model
     */
    public AddTradeController(AddTradeActivity view, Trade model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }

    @Override
    protected void setListeners(AddTradeActivity view) {
        addUser = view.getItemUserBtn();
        addOther = view.getItemFriendBtn();
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send to activity with the trade in hand.
                Intent myIntent = new Intent(v.getContext(), CardTradeActivity.class);
                myIntent.putExtra("com.sherpasteven.sscte.trade", (Serializable)model);
                v.getContext().startActivity(myIntent);
            }
        });
        addOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
