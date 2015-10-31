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
import com.sherpasteven.sscte.AddFriendActivity;
import com.sherpasteven.sscte.EditCardActivity;
import com.sherpasteven.sscte.R;

public class InventoryTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.inventory_tab,container,false);


        Button addItem = (Button) v.findViewById(R.id.btnAddItem);
        addItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), AddCardActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        Button editItem = (Button) v.findViewById(R.id.btnEditItem);
        editItem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), EditCardActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        return v;
    }
}