package com.sherpasteven.sscte.Controllers;

import com.sherpasteven.sscte.EditCardActivity;
import com.sherpasteven.sscte.Models.Card;

/**
 * Controller for EditCardActivity.
 */
public class EditCardController extends Controller<EditCardActivity, Card> {

    private final EditCardActivity view;
    private final Card model;

    public EditCardController(EditCardActivity view, Card model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }
    @Override
    protected void setListeners(EditCardActivity view) {

    }
}
