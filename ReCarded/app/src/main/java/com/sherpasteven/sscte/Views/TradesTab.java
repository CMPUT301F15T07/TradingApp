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
import com.sherpasteven.sscte.EditCardActivity;
import com.sherpasteven.sscte.EditTradeActivity;
import com.sherpasteven.sscte.R;

public class TradesTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.trades_tab,container,false);

        Button addItem = (Button) v.findViewById(R.id.btnAddTrade);
        addItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), AddTradeActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        Button editItem = (Button) v.findViewById(R.id.btnEditTrade);
        editItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), EditTradeActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        return v;
    }
}