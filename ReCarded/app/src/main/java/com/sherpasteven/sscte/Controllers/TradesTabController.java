package com.sherpasteven.sscte.Controllers;

import android.view.View;
import android.widget.Button;

import com.sherpasteven.sscte.Models.TradeLog;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.Views.TradesTab;

/**
 * Created by jake on 2015-10-31.
 */
public class TradesTabController extends Controller<TradesTab, TradeLog> {
    private final TradesTab view;
    private final TradeLog model;

    public TradesTabController(TradesTab view, TradeLog model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }
    @Override
    protected void setListeners(final TradesTab view) {
        Button addItem = (Button) view.getView().findViewById(R.id.btnAddTrade);
        addItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.navigateToAddTradeActivity();
            }
        });

        Button editItem = (Button) view.getView().findViewById(R.id.btnEditTrade);
        editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.navigateToEditTradeActivity();
            }
        });

    }
}
