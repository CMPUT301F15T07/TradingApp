package com.sherpasteven.sscte.Controllers;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Registration;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.SplashPage;

/**
 * This controller controls a registration model via the splash page.
 * The majority of the work that is done is taking in text input for registering the user
 * and then updating the model to match. The controller also prompts the registration to save
 * once the user has provided all relevant information and submits.
 */
public class RegisterController extends Controller<SplashPage, Registration> {
    private final SplashPage view;
    private final Registration model;

    public RegisterController(SplashPage view, Registration model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }

    /**
     * save registration using user provided information
     * @param context app context
     */
    public void saveRegistration(Context context){
        model.saveRegistration(context);
    }

    /**
     * load registration if it already exists from previous program executions
     * @param context app context
     */
    public void loadRegistration(Context context){
        model.loadRegistration(context);
    }


    /**
     * set all the listeners on the spash page that correspond to user input.
     * This lets the controller update the model when the user inputs information
     * @param view splash page to set listeners on
     */
    @Override
    protected void setListeners(final SplashPage view) {
        Button submitButton = (Button)view.findViewById(R.id.btnEnter);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set profile can be removed once serialization is operational
                //used to be model.generateProfile(view);
                Profile newProfile = model.generateProfile(view);
                view.setLocalProfile(newProfile);
                view.setCloudProfile();
                view.navigateToInventory();
            }
        });

        TextView usernameView = (TextView)view.findViewById(R.id.nameText);
        usernameView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                model.setUserName(s.toString());
            }
        });

        TextView location = (TextView)view.findViewById(R.id.cityText);
        location.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                model.setLocation(s.toString());
            }
        });

        TextView email = (TextView)view.findViewById(R.id.emailText);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                model.setUserEmail(s.toString());
            }
        });

    }
}
