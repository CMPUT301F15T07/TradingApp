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
    private Trade model;
    private TradeLog tradelog;

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
                model.notifyViews();
                view.finish();

                String subject = "SSCTE Trade Completed" ;
                String body = model.getOwner().getName() + " has accepted a trade with " + model.getBorrower().getName() + ".\n" +
                        "+=================================+\n" +
                        " " + model.getOwner().getName() + "'s cards traded:\n";
                for (Card card : model.getOwnerList()) {
                    body = body + " [" + card.getQuantity() + "] " + card.getName() + "\n";
                }
                body = body +
                        "+=====================+\n" +
                        " " + model.getBorrower().getName() + "'s cards traded:\n";
                for (Card card : model.getBorrowList()) {
                    body = body + " [" + card.getQuantity() + "] " + card.getName() + "\n";
                }
                body = body +
                        "+=================================+\n\n" +
                        " [Add some comments for continuing trade here]";


                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",model.getOwner().getEmail() + ","+model.getBorrower().getEmail(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, body);
                view.startActivity(Intent.createChooser(emailIntent, "Send email..."));

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
}
