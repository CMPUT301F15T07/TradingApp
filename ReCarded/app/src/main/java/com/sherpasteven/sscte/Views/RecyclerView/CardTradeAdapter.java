package com.sherpasteven.sscte.Views.RecyclerView;

import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.R;

import java.util.ArrayList;

/**
 * Created by ansonli on 2015-11-23.
 */
public class CardTradeAdapter extends RecyclerView.Adapter<CardTradeAdapter.ViewHolder>{

    private static final String TAG = "FriendAdapter";

    private String[] mDataSet;
    ArrayList<Card> cardList;
    static View view;


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
                }
            });
            cv = (CardView) v.findViewById(R.id.cv);
            cardName = (TextView) v.findViewById(R.id.card_name);
            cardDescription = (TextView) v.findViewById(R.id.card_text);
            cardPhoto =  (ImageView) v.findViewById(R.id.card_photo);
            cardStatus = (ImageView)itemView.findViewById(R.id.imgStatus);
            view = v;

        }

    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     * @param cardList User data loaded to identify friends used by adapter.
     */

    public CardTradeAdapter(ArrayList<Card> cardList){
        this.cardList = cardList;
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
        viewHolder.cardName.setText(cardList.get(position).getName());
        viewHolder.cardDescription.setText(cardList.get(position).getCatagory());
        if (cardList.get(position).getImagebyIndex(0) != null) {
            viewHolder.cardPhoto.setImageBitmap(cardList.get(position).constructImage(0));
        }
        if (cardList.get(position).isTradable() == true) {
            viewHolder.cardStatus.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_trade_available));
        } else if (cardList.get(position).isTradable() == false) {
            viewHolder.cardStatus.setImageDrawable(ContextCompat.getDrawable(view.getContext(), R.drawable.ic_trade_unavailable));
        }
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    /** Gets item for dynamic loading.
     * @return size of dataset (invoked by layout manager)
     */    @Override
    public int getItemCount() {
        return cardList.size();
    }

}
