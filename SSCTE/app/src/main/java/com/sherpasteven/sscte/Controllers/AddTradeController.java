package com.sherpasteven.sscte.Controllers;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sherpasteven.sscte.AddTradeActivity;
import com.sherpasteven.sscte.CardTradeActivity;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.TradeComposer;

/**
 * This controller is used with the addtradeactivity.
 * The main functions for this controller are adding cards to
 * the trade when the add card button is hit and also construct
 * and submit the trade when the submit button is hit
 */
public class AddTradeController extends Controller<AddTradeActivity, Trade> {

    private final AddTradeActivity view;
    private final Trade model;

    ImageButton addUser;
    ImageButton addOther;
    Button submit;

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
    protected void setListeners(final AddTradeActivity view) {
        addUser = view.getItemUserBtn();
        addOther = view.getItemFriendBtn();


        addUser.setOnClickListener(new View.OnClickListener() {
            /**
             * This starts a new cardtradeactivity when
             * the adduser button was hit
             * @param v
             */
            @Override
            public void onClick(View v) {
                // send to activity with the trade in hand.
                Intent myIntent = new Intent(v.getContext(), CardTradeActivity.class);
                // myIntent.putExtra("com.sherpasteven.sscte.trade", (Parcelable)model);
                // pass this to the inventory activity // i made a temp one to hold 'inventory cards'
                // no longer a fragment
                // inventory activity selects a card
                // 'sends' back
                myIntent.putExtra("com.sherpasteven.sscte.user", true);
                myIntent.putExtra("com.sherpasteven.sscte.friend", false);
                v.getContext().startActivity(myIntent);

            }
        });
        addOther.setOnClickListener(new View.OnClickListener() {
            /**
             * This starts a new cardtradeactivity when
             * the addother button is hit
             * @param v
             */
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), CardTradeActivity.class);
                myIntent.putExtra("com.sherpasteven.sscte.user", false);
                myIntent.putExtra("com.sherpasteven.sscte.friend", true);
                v.getContext().startActivity(myIntent);

            }
        });

        submit = view.getSubmitButton();
        submit.setOnClickListener(new View.OnClickListener() {
            /**
             * This constructs and submits the trade when
             * the submit trade button is hit.
             * @param v
             */
            @Override
            public void onClick(View v){
                Trade trade = TradeComposer.getTradeComposer().composeTrade();
                if (trade != null) {
                    Toast.makeText(v.getContext(), "Trade submitted!", Toast.LENGTH_SHORT).show();
                    if (view.getCounter()) {
                        CurrentProfile.getCurrentProfile().getProfile(v.getContext()).getUser().getTrades().removeTrade(view.getPosition());
                    }
                    CurrentProfile.getCurrentProfile().getProfile(v.getContext()).getUser().addPendingTrade(trade);
                    LocalProfileSerializer lps = new LocalProfileSerializer();
                    lps.Serialize(CurrentProfile.getCurrentProfile().getProfile(view), view);
                    view.finish();
                } else {
                    Toast.makeText(v.getContext(), "Trade failed...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
