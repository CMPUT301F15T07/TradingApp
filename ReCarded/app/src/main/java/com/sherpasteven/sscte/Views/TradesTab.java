package com.sherpasteven.sscte.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sherpasteven.sscte.AddCardActivity;
import com.sherpasteven.sscte.AddTradeActivity;
import com.sherpasteven.sscte.Controllers.TradesTabController;
import com.sherpasteven.sscte.EditCardActivity;
import com.sherpasteven.sscte.EditTradeActivity;
import com.sherpasteven.sscte.Models.TradeLog;
import com.sherpasteven.sscte.R;

public class TradesTab extends Fragment implements IView<TradeLog> {

    private TradeLog trades;
    private TradesTabController tradestabcontroller;
    private View inflate_view;

    public TradesTab(TradeLog trades){
        super();
        this.trades = trades;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.trades_tab,container,false);
        inflate_view = v;
        trades.addView(this);
        tradestabcontroller = new TradesTabController(this, trades);

        return v;
    }

    @Override
    public void Update(TradeLog tradeLog) {

    }
    public void navigateToAddTradeActivity(){
        Intent myIntent = new Intent(getActivity(), AddTradeActivity.class);
        getActivity().startActivity(myIntent);
    }
    public void navigateToEditTradeActivity(){
        Intent myIntent = new Intent(getActivity(), EditTradeActivity.class);
        getActivity().startActivity(myIntent);
    }
    public View getView(){
        return inflate_view;
    }
}