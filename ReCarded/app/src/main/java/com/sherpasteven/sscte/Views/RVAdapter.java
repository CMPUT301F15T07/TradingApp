package com.sherpasteven.sscte.Views;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.R;

import java.util.List;

/**
 * Created by ansonli on 2015-10-30.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {

    List<Card> cardlist;

    public RVAdapter(List<Card> cards){
        this.cardlist = cards;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView cardTitle;
        TextView cardDescription;
        ImageView cardImage;

        CardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            cardTitle = (TextView)itemView.findViewById(R.id.card_title);
            cardDescription = (TextView)itemView.findViewById(R.id.card_descriptor);
            cardImage = (ImageView)itemView.findViewById(R.id.basicimageone);
        }
    }

    @Override
    public int getItemCount() {
        return cardlist.size();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        CardViewHolder cvh = new CardViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
        cardViewHolder.cardTitle.setText(cardlist.get(i).getName());
        cardViewHolder.cardDescription.setText(cardlist.get(i).getCatagory());
        cardViewHolder.cardImage.setImageResource(cardlist.get(i).returnImage());
    }




}