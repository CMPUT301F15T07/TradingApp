package com.sherpasteven.sscte.Views.RecyclerView;
/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;

import java.util.ArrayList;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class NewFriendAdapter extends RecyclerView.Adapter<NewFriendAdapter.ViewHolder> {
    private static final String TAG = "FriendAdapter";

    private String[] mDataSet;
    ArrayList<User> friendsList;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView userName;
        TextView userDescription;
        ImageView userPhoto;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Element " + getPosition() + " to be shown");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();                }
            });
            cv = (CardView) v.findViewById(R.id.cv);
            userName = (TextView) v.findViewById(R.id.friend_name);
            userDescription = (TextView) v.findViewById(R.id.friend_text);
            userPhoto = (ImageView)itemView.findViewById(R.id.friend_photo);

        }

    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     * @param friendsList User data loaded to identify friends used by adapter.
     */

    public NewFriendAdapter(ArrayList<User> friendsList){
        this.friendsList = friendsList;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.friend_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.userName.setText(friendsList.get(position).getName());
        viewHolder.userDescription.setText(friendsList.get(position).getLocation());
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    /** Gets item for dynamic loading.
     * @return size of dataset (invoked by layout manager)
     */    @Override
    public int getItemCount() {
        return friendsList.size();
    }
}