package com.sherpasteven.sscte.Controllers;

import com.sherpasteven.sscte.AddCardActivity;
import com.sherpasteven.sscte.Models.Inventory;

/**
 * Created by jake on 2015-10-30.
 */
public class AddCardController extends Controller<AddCardActivity, Inventory> {

    private final AddCardActivity view;
    private final Inventory model;

    public AddCardController(AddCardActivity view, Inventory model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }
    @Override
    protected void setListeners(AddCardActivity view) {

    }
}
