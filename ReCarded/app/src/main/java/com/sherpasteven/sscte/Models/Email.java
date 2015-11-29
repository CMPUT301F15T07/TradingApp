package com.sherpasteven.sscte.Models;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Create an email to send to a both the borrower and the owner.
 * Given a trade, the model parses critical information and provides them for the user.
 */
public class Email extends Model{

    public Email() {
    }

    /**
     * Takes the model information and parses the data to send via email.
     * Email host inherently on the application starts the email,
     * and will send to both the borrower and the owner.
     * @param model An accepted trade, to be converted for email for borrower/owner passing.
     * @param view The view to send the activity from - usually ViewTradeActivity.
     */
    public void tradeEmail(Trade model, Activity view) {
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
}
