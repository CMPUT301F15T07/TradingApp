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

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.ViewCardActivity;

import java.util.List;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private static final String TAG = "CardAdapter";

    private String[] mDataSet;
    List<Card> cards;
    static View view;
    static boolean wassearched;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView cardName;
        TextView cardDescription;
        ImageView cardPhoto;
        ImageView cardStatus;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
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
                    alertDialog.show(); */
                    Intent myIntent = new Intent(view.getContext(), ViewCardActivity.class);
                    myIntent.putExtra("com.sherpasteven.sscte.viewcard", getPosition());
                    if (wassearched){
                        myIntent.putExtra("com.sherpasteven.sscte.searched", 1);
                    }
                    view.getContext().startActivity(myIntent);

                }
            });
            cv = (CardView) v.findViewById(R.id.cv);
            cardName = (TextView) v.findViewById(R.id.card_name);
            cardDescription = (TextView) v.findViewById(R.id.card_text);
            cardPhoto = getCardImage();
            cardStatus = (ImageView)itemView.findViewById(R.id.imgStatus);
            view = v;

        }

        public ImageView getCardImage(){
            return (ImageView)itemView.findViewById(R.id.card_photo);
        }

    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     * @param card Initialise list of cards for loading.
     */

    public CardAdapter(List<Card> card, boolean checkforsearch){
        this.cards = card;
        this.wassearched = checkforsearch;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.cardName.setText(cards.get(position).getName());
        viewHolder.cardDescription.setText(cards.get(position).getCategory());
        if (cards.get(position).getImagebyIndex(0) != null) {
            viewHolder.cardPhoto.setImageBitmap(cards.get(position).constructImage(0));
        }
        Card card = cards.get(position);
        if (cards.get(position).isTradable() == true) {
            viewHolder.cardStatus.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_trade_available));
        } else if (cards.get(position).isTradable() == false) {
            viewHolder.cardStatus.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_trade_unavailable));
        }
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    /**
     * @return the size of your dataset (invoked by the layout manager)
     */
    @Override
    public int getItemCount() {
        if (cards != null) {
            return cards.size();
        } return 0;
    }
}
