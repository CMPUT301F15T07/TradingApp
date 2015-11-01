package com.sherpasteven.sscte.Controllers;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.sherpasteven.sscte.AddCardActivity;
import com.sherpasteven.sscte.EditCardActivity;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.Views.InventoryTab;

/**
 * Created by jake on 2015-10-31.
 */
public class InventoryTabController extends Controller<InventoryTab,Inventory>{
    private final InventoryTab view;
    private final Inventory model;

    public InventoryTabController(InventoryTab view, Inventory model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }
    @Override
    protected void setListeners(final InventoryTab view) {
        Button addItem = (Button) view.getView().findViewById(R.id.btnAddItem);
        addItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.navigateToAddCardActivity();
            }
        });

        Button editItem = (Button) view.getView().findViewById(R.id.btnEditItem);
        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.navigateToEditCardActivity();
            }
        });

    }
}