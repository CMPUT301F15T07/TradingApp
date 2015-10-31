package com.sherpasteven.sscte.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sherpasteven.sscte.AddFriendActivity;
import com.sherpasteven.sscte.R;

public class FriendsTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.friends_tab,container,false);

        Button addFriend = (Button) v.findViewById(R.id.btnAddFriend);
        addFriend.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), AddFriendActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        return v;
    }
}