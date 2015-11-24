package com.sherpasteven.sscte.Controllers;

import android.view.View;

import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.ViewCardActivity;

/**
 * Created by joshua on 23/11/15.
 */
public class ViewCardController extends Controller<ViewCardActivity, Profile>{

    View view;
    Model model;


        public ViewCardController(ViewCardActivity view, Profile model) {
            super(view, model);
            this.view = view;
            this.model = model;
        }

    @Override
    protected void setListeners(ViewCardActivity view) {

    }


}
