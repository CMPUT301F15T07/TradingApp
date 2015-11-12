package com.sherpasteven.sscte.Models;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Create an email to send to a user.
 */
public class Email extends Model{
    private User user;
    private String extraMessage;
    private String status;

    public Email(User user, String extraMessage) {
        this.user = user;
        this.extraMessage = extraMessage;
        status = "NOT SENT";
    }

    public String getStatus() {
        return status;
    }

    /**
     * send email to user and set the status of the email.
     */
    public void sendEmail() {
        // create and send email
        // Code from stack overflow from user ɥʇᴉɾuɐɹ
        Log.i("Send email", "");

        String[] TO = {user.getEmail()};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email Statistics.");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "details" + extraMessage);

        try {
            //startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            //finish();
            Log.i("Finished sending email", "");
            status = "SENT";
        } catch (android.content.ActivityNotFoundException ex) {
            status = "NOT SENT";
        }
    }
}
