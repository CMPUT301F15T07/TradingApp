package com.sherpasteven.sscte.Controllers;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.Views.InventoryTab;

/**
 * Controller for InventoryTab.
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



        /*
        Button editItem = (Button) view.getView().findViewById(R.id.btnEditItem);
        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.navigateToEditCardActivity();
            }
        });

        Button viewItem = (Button) view.getView().findViewById(R.id.btnViewItem);
        viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.navigateToViewCardActivity();
            }
        });
        */

    }
}
