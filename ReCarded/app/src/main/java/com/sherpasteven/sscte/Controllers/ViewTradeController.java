package com.sherpasteven.sscte.Controllers;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sherpasteven.sscte.AddTradeActivity;
import com.sherpasteven.sscte.CardTradeActivity;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Friend;
import com.sherpasteven.sscte.Models.ISerializer;
import com.sherpasteven.sscte.Models.Email;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.TradeComposer;
import com.sherpasteven.sscte.Models.TradeLog;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.ViewTradeActivity;

import java.util.ArrayList;


/**
 * Created by ansonli on 2015-11-25.
 */
public class ViewTradeController extends Controller<ViewTradeActivity, Trade> {

    private final ViewTradeActivity view;
    private Trade model;
    private TradeLog tradelog;

    private Profile profile;
    private User owner;
    private User borrower;
    private ArrayList<Card> newborrowlist;
    private ArrayList<Card> newownerlist;

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

    /**
     * Sets the listener activity on the view in order to process response.
     * Lets the user accept, decline and counteroffer trades.
     * @param view view to set listeners on.
     */
    @Override
    protected void setListeners(final ViewTradeActivity view) {

        acceptButton = view.getAcceptButton();
        declineButton = view.getDeclineButton();
        counterofferButton = view.getCounterOfferButton();

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                owner = model.getOwner();
                borrower = model.getBorrower();
                profile = CurrentProfile.getCurrentProfile().getProfile(view.getApplicationContext());
                User localUser = profile.getUser();

                if(borrower.equals(profile.getUser())){
                    for(Card bc: model.getBorrowList()){
                        //remove borrow cards from user
                        localUser.getInventory().removeCard(bc, bc.getQuantity());
                    }
                    for(Card oc: model.getOwnerList()){
                        //add owner cards to user
                        localUser.getInventory().addCard(oc);
                    }
                    profile.setUser(borrower);
                } else {
                    //user is owner
                    for(Card bc: model.getBorrowList()){
                        //add borrower cards
                        localUser.getInventory().addCard(bc);
                    }
                    for(Card oc: model.getOwnerList()){
                        //remove owner cards
                        localUser.getInventory().removeCard(oc, oc.getQuantity());
                    }
                }
                localUser.incrementRating();
                model.setStatus("ACCEPTED");
                tradelog.tradeFinalized(model);
                setLocalProfile(profile);
                model.notifyViews();
                //Email email = new Email();
                //email.tradeEmail(model, view);
                view.finish();

            }
        });
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setStatus("DECLINED");
                tradelog.tradeFinalized(model);
                model.notifyViews();
                view.finish();
            }
        });
        counterofferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trade countertrade = model.counterOffer();
                // removes from pending trades for counteroffer]
                int position = tradelog.getPendingTrades().indexOf(model);
                //tradelog.getPendingTrades().remove(model);
                //tradelog.addCounterOfferTrade(model, countertrade);
                /*TradeComposer.getTradeComposer().getComponents().setOwner(countertrade.getOwner());
                TradeComposer.getTradeComposer().getComponents().setBorrower(countertrade.getBorrower());
                TradeComposer.getTradeComposer().getComponents().setOwnerList(countertrade.getOwnerList());
                TradeComposer.getTradeComposer().getComponents().setBorrowList(countertrade.getBorrowList());*/
                TradeComposer.getTradeComposer().getComponents().setOwner(countertrade.getBorrower());
                TradeComposer.getTradeComposer().getComponents().setBorrower(countertrade.getOwner());
                TradeComposer.getTradeComposer().getComponents().setOwnerList(countertrade.getOwnerList());
                TradeComposer.getTradeComposer().getComponents().setBorrowList(countertrade.getBorrowList());
                TradeComposer.getTradeComposer().getComponents().setTradeId(countertrade.getId());
                //tradelog.addCounterOfferTrade();
                Intent intent = new Intent(v.getContext(), AddTradeActivity.class);
                intent.putExtra("com.sherpasteven.sscte.counterindex", position);
                model.notifyViews();
                view.finish();
                v.getContext().startActivity(intent);
            }
        });
    }

    private void setLocalProfile(Profile profile) {
        ISerializer<Profile> serializer = new LocalProfileSerializer();
        serializer.Serialize(profile, view);
    }
}
