package com.sherpasteven.sscte.Controllers;

import com.sherpasteven.sscte.AddCardActivity;
import com.sherpasteven.sscte.Models.Inventory;

/**
 *
 */
public class AddCardController extends Controller<AddCardActivity, Inventory> {

    private final AddCardActivity view;
    private final Inventory model;

    /**
     * Instantiates the controller on the proper activity.
     * @param view
     * @param model
     */
    public AddCardController(AddCardActivity view, Inventory model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }
    @Override
    protected void setListeners(AddCardActivity view) {

    }
}
