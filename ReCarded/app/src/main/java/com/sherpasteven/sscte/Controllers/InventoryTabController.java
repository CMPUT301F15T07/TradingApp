package com.sherpasteven.sscte.Controllers;

import android.view.View;
import android.widget.ImageButton;

import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.Views.InventoryTab;

/**
 * Controller for InventoryTab.
 * Used for navigating to the add card activity
 * when the add card button is hit
 */
public class InventoryTabController extends Controller<InventoryTab,Inventory>{
    private final InventoryTab view;
    private final Inventory model;

    public InventoryTabController(InventoryTab view, Inventory model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }

    /**
     * Set listeners for buttons to change activities.
     * @param view view to set listeners on.
     */
    @Override
    protected void setListeners(final InventoryTab view) {
        ImageButton addItem = (ImageButton) view.getView().findViewById(R.id.btnAddItem);
        addItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.navigateToAddCardActivity();
            }
        });
    }
}
