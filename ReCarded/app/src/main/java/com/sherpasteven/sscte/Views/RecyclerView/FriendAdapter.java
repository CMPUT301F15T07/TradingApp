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

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sherpasteven.sscte.AddTradeActivity;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.ViewFriendActivity;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    private static final String TAG = "FriendAdapter";

    private String[] mDataSet;
    static boolean tradeState = false;
    static User currentUser;
    static Activity a;

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
            if (tradeState == false) { // you're selecting from friendtab
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*
                        AlertDialog alertDialog = new AlertDialog.Builder(v.getContext()).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Element " + getPosition() + " to be shown");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                        */
                        Intent myIntent = new Intent(v.getContext(), ViewFriendActivity.class);
                        myIntent.putExtra("com.sherpasteven.sscte.viewfriend", getPosition());
                        v.getContext().startActivity(myIntent);
                    }
                });
            } else { // you're selecting from tradetab -> add new trade
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(v.getContext(), AddTradeActivity.class);
                        myIntent.putExtra("com.sherpasteven.sscte.friend", getPosition());
                        a.finish();
                        v.getContext().startActivity(myIntent);
                        //ArrayList<User> friends = CurrentProfile.getCurrentProfile().getProfile(v.getContext()).getUser().getFriends();
                        //TradeComposer.getTradeComposer().getComponents().setOwner(friends.get(getPosition()));

                    }
                });
            }

            cv = (CardView) v.findViewById(R.id.cv);
            userName = (TextView) v.findViewById(R.id.friend_name);
            userDescription = (TextView) v.findViewById(R.id.friend_text);
            userPhoto = (ImageView) itemView.findViewById(R.id.friend_photo);
        }

    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     * @param user User data loaded to identify friends used by adapter.
     */

    public FriendAdapter(User user){
        this.currentUser = user;
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
        viewHolder.userName.setText(currentUser.getFriends().get(position).getName());
        viewHolder.userDescription.setText(currentUser.getFriends().get(position).getLocation());
        if (currentUser.getFriends().get(position).getProfilePic() != null) {
            viewHolder.userPhoto.setImageBitmap(currentUser.getFriends().get(position).constructProfilePic());
        }
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    /** Gets item for dynamic loading.
     * @return size of dataset (invoked by layout manager)
     */    @Override
    public int getItemCount() {
        if (currentUser == null || currentUser.getFriends() == null) {
            return 0;
        } else {
            return currentUser.getFriends().size();
        }
    }

    public void setTradeState(Boolean cond) {
        tradeState = cond;
    }

    public void setActivity(Activity acc) {
        a = acc;
    }
}
