package com.sherpasteven.sscte.Controllers;

import com.sherpasteven.sscte.Models.Registration;
import com.sherpasteven.sscte.Views.RegisterView;

/**
 * Created by elias on 17/10/15.
 */
public class RegisterController extends Controller<RegisterView, Registration> {
    private final RegisterView view;
    private final Registration model;

    public RegisterController(RegisterView view, Registration model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }

    @Override
    protected void setListeners(RegisterView view) {

    }
}
