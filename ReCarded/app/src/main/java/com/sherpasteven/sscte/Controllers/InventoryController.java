package com.sherpasteven.sscte.Controllers;

import com.sherpasteven.sscte.InventoryActivity;
import com.sherpasteven.sscte.Models.Inventory;

/**
 * Controller for InventoryActivity.
 */
public class InventoryController extends Controller<InventoryActivity, Inventory> {
    private final InventoryActivity view;
    private final Inventory model;

    public InventoryController(InventoryActivity view, Inventory model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }
    @Override
    protected void setListeners(InventoryActivity view) {

    }
}
