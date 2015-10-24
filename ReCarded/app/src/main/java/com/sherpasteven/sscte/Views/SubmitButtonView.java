package com.sherpasteven.sscte.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.sherpasteven.sscte.Models.Registration;

/**
 * Created by elias on 24/10/15.
 */
public class SubmitButtonView extends Button implements IView<Registration> {


    public SubmitButtonView(Context context) {
        super(context);
    }

    public SubmitButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SubmitButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void Update(Registration registration) {
        setEnabled(canSubmit(registration));
    }

    public boolean canSubmit(Registration registration){
        return true;
        //return !registration.getLocation().isEmpty() &&
                //!registration.getUserName().isEmpty() &&
                //!registration.getUserEmail().isEmpty() &&
                //registration.isValidEmail();
    }
}
