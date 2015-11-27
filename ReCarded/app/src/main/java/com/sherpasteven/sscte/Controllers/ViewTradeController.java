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
import com.sherpasteven.sscte.Models.TradeLog;
import com.sherpasteven.sscte.ViewTradeActivity;


/**
 * Created by ansonli on 2015-11-25.
 */
public class ViewTradeController extends Controller<ViewTradeActivity, Trade> {

    private final ViewTradeActivity view;
    private final Trade model;
    private final TradeLog tradelog;

    Button acceptButton;
    Button declineButton;
    Button counterofferButton;

    private LocalProfileSerializer profileSerializer = new LocalProfileSerializer();

    /**
     * Instantiates the controller on the proper activity.
     *
     * @param view
     * @param model
     */
    public ViewTradeController(ViewTradeActivity view, Trade model) {
        super(view, model);
        this.view = view;
        this.model = model;
        this.tradelog = CurrentProfile.getCurrentProfile().getProfile(view.getApplicationContext()).getUser().getTrades();
    }

    @Override
    protected void setListeners(final ViewTradeActivity view) {

        acceptButton = view.getAcceptButton();
        declineButton = view.getDeclineButton();
        counterofferButton = view.getCounterOfferButton();

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setStatus("ACCEPTED");
                tradelog.tradeFinalized(model);
            }
        });
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setStatus("DECLINED");
                tradelog.tradeFinalized(model);
            }
        });
        counterofferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trade countertrade = model.counterOffer();
                // removes from pending trades for counteroffer
                tradelog.getPendingTrades().remove(model);
                //tradelog.addCounterOfferTrade(model, countertrade);
                TradeComposer.getTradeComposer().getComponents().setOwner(countertrade.getOwner());
                TradeComposer.getTradeComposer().getComponents().setBorrower(countertrade.getBorrower());
                TradeComposer.getTradeComposer().getComponents().setOwnerList(countertrade.getOwnerList());
                TradeComposer.getTradeComposer().getComponents().setBorrowList(countertrade.getBorrowList());
                int position = tradelog.getPendingTrades().indexOf(model);
                Intent intent = new Intent(v.getContext(), AddTradeActivity.class);
                intent.getIntExtra("com.sherpasteven.sscte.counterindex", position);
                v.getContext().startActivity(intent);
            }
        });

        /*
        addUser = view.getItemUserBtn();
        addOther = view.getItemFriendBtn();
        addUser.setOnClickListener(new View.OnClickListener() {
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
            @Override
            public void onClick(View v){
                Trade trade = TradeComposer.getTradeComposer().composeTrade();
                if (trade != null) {
                    Toast.makeText(v.getContext(), "Trade submitted!", Toast.LENGTH_SHORT).show();
                    CurrentProfile.getCurrentProfile().getProfile(v.getContext()).getUser().addPendingTrade(trade);
                    LocalProfileSerializer lps = new LocalProfileSerializer();
                    lps.Serialize(CurrentProfile.getCurrentProfile().getProfile(view), view);
                    view.finish();
                } else {
                    Toast.makeText(v.getContext(), "Trade failed...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        */

    }
}
