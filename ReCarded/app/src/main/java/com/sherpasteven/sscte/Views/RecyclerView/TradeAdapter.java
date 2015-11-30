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
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.ViewTradeActivity;

import java.util.List;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class TradeAdapter extends RecyclerView.Adapter<TradeAdapter.ViewHolder> {
    private static final String TAG = "TradeAdapter";

    private String[] mDataSet;
    List<Trade> trades;
    private User currentUser;
    static int pendingCount;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tradeName;
        TextView tradeDescription;
        ImageView tradePhoto;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ViewTradeActivity.class);
                    intent.putExtra("com.sherpasteven.sscte.position", getPosition());
                    v.getContext().startActivity(intent);

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
                    alertDialog.show();                */
                }
            });
            cv = (CardView) v.findViewById(R.id.cv);
            tradeName = (TextView) v.findViewById(R.id.trade_name);
            tradeDescription = (TextView) v.findViewById(R.id.trade_text);
            tradePhoto = (ImageView)itemView.findViewById(R.id.trade_photo);

        }

    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     * @param trades List of trades initialised for loading.
     */
    public TradeAdapter(List<Trade> trades, User currentUser, int pendingCount){
        this.trades = trades;
        this.currentUser = currentUser;
        this.pendingCount = pendingCount;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.trade_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        if (trades.get(position).getOwnerList().get(0).getImagebyIndex(0) != null) {
            viewHolder.tradePhoto.setImageBitmap(trades.get(position).getOwnerList().get(0).constructImage(0));
        } else {
            if (trades.get(position).getBorrowList().get(0).getImagebyIndex(0) != null) {
                viewHolder.tradePhoto.setImageBitmap(trades.get(position).getBorrowList().get(0).constructImage(0));
            }
        }

        Integer cardsum = trades.get(position).getOwnerList().size() + trades.get(position).getBorrowList().size();

        //we want to print "Trade with Other User" so we need to find if they are the owner or borrower
        if (trades.get(position).getOwner().getProfileId().equals(currentUser.getProfileId())) {
            viewHolder.tradeName.setText("Trade with " + trades.get(position).getBorrower().getName());
        } else {
            viewHolder.tradeName.setText("Trade with " + trades.get(position).getOwner().getName());
        }
        viewHolder.tradeDescription.setText(trades.get(position).getStatus() + ": " + cardsum + " cards in trade.");

        final int lightgreen = 0xFFCCFFC3;
        final int lightred = 0xFFFFDED4;

        if(trades.get(position).getStatus().equals("ACCEPTED")){
            viewHolder.cv.setCardBackgroundColor(lightgreen); // will change the background color of the card view to green
        }
        else if(trades.get(position).getStatus().equals("DECLINED")){
            viewHolder.cv.setCardBackgroundColor(lightred); // will change the background color of the card view to green
        } else {
            viewHolder.cv.setCardBackgroundColor(0xFFFFFFFF);
        }
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    /** Gets item for dynamic loading.
     * @return size of dataset (invoked by layout manager)
     */
    @Override
    public int getItemCount() {
        return trades.size();
    }
}
