package com.sherpasteven.sscte.Views.RecyclerView;

/*
* Copyright (C) 2015 Sherpa Steven Ltd., The Android Open Source Project
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
import com.sherpasteven.sscte.ViewTradeActivity;

import java.util.List;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class BorrowerViewTradeAdapter extends RecyclerView.Adapter<BorrowerViewTradeAdapter.ViewHolder> {
    private static final String TAG = "CardAdapter";

    private String[] mDataSet;
    List<Card> cards;
    static View view;
    static ViewTradeActivity act;
    static int tradelistpos;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView cardName;
        TextView cardDescription;
        ImageView cardPhoto;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(view.getContext(), ViewCardActivity.class);
                    myIntent.putExtra("com.sherpasteven.sscte.viewcard", getPosition());
                    myIntent.putExtra("com.sherpasteven.sscte.trades", "borrower");
                    myIntent.putExtra("com.sherpasteven.sscte.tradepos", tradelistpos);
                    view.getContext().startActivity(myIntent);
                }
            });

            cv = (CardView) v.findViewById(R.id.cv);
            cardName = (TextView) v.findViewById(R.id.card_name);
            cardDescription = (TextView) v.findViewById(R.id.card_text);
            cardPhoto = getCardImage();
            view = v;

        }

        public ImageView getCardImage(){
            return (ImageView)itemView.findViewById(R.id.card_photo);
        }

    }

    /**
     * Initialize the dataset of the Adapter.
     * @param card Initialise list of cards for loading.
     * @param activity
     */

    public BorrowerViewTradeAdapter(List<Card> card, ViewTradeActivity activity, int tradelistpos){
        this.cards = card;
        this.act = activity;
        this.tradelistpos = tradelistpos;
    }

    /**
     * Creates the view holder using the xml style to process.
     * @param viewGroup Sets the group view style
     * @param viewType Sets the viewtype
     * @return viewholder to process
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_item_mini, viewGroup, false);

        return new ViewHolder(v);
    }

    /**
     * Overrides the image set for the card. Adds styles and structure.
     * @param viewHolder to style
     * @param position of card in the tradelist.
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.cardName.setText(cards.get(position).getName());
        viewHolder.cardDescription.setText(cards.get(position).getCategory());
        if (cards.get(position).getImagebyIndex(0) != null) {
            viewHolder.cardPhoto.setImageBitmap(cards.get(position).constructImage(0));
        }

    }

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
