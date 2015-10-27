package com.sherpasteven.sscte.Controllers;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sherpasteven.sscte.Models.Registration;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.SplashPage;

/**
 * Created by elias on 17/10/15.
 */
public class RegisterController extends Controller<SplashPage, Registration> {
    private final SplashPage view;
    private final Registration model;

    public RegisterController(SplashPage view, Registration model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }

    public void saveRegistration(Context context){
        model.saveRegistration(context);
    }

    public void loadRegistration(Context context){
        model.loadRegistration(context);
    }



    @Override
    protected void setListeners(final SplashPage view) {
        Button submitButton = (Button)view.findViewById(R.id.btnEnter);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saving shit here
                model.generateProfile(view);
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
