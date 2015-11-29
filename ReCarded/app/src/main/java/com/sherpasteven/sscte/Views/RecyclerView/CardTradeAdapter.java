package com.sherpasteven.sscte.Views.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sherpasteven.sscte.CardTradeActivity;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.TradeComposer;
import com.sherpasteven.sscte.R;

import java.util.ArrayList;

/**
 * Created by ansonli on 2015-11-23.
 */
public class CardTradeAdapter extends RecyclerView.Adapter<CardTradeAdapter.ViewHolder>{

    private static final String TAG = "FriendAdapter";

    private String[] mDataSet;
    static ArrayList<Card> cardList;
    static View view;
    static Activity cta;
    static Boolean userState;
    static int deletequantity;
    static Card tradeCard;


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
                    if (userState) {
                        tradeCard = CurrentProfile.getCurrentProfile().getProfile(v.getContext()).getUser().getInventoryItem(getPosition());
                        if (tradeCard != null) {
                            if(tradeCard.isTradable()) {
                                if(!TradeComposer.getTradeComposer().getComponents().getBorrowList().contains(tradeCard)) {
                                    AlertDialog confirmDel = ConfirmDelete().create();
                                    confirmDel.show();
                                }
                                else {
                                    Toast.makeText(v.getContext(), "Card is already in your trade list.", Toast.LENGTH_SHORT).show();
                                    cta.finish();
                                }
                            }

                            else {
                                Toast.makeText(v.getContext(), "Card is not tradeable.", Toast.LENGTH_SHORT).show();
                                cta.finish();

                            }
                        } else {
                            Toast.makeText(v.getContext(), "Card could not be added to trade...", Toast.LENGTH_SHORT).show();
                            cta.finish();
                        }

                    } else { // FIXME: Demo until friend's cards can be pulled
                        tradeCard = cardList.get(getPosition());
                        if (tradeCard != null) {
                            if(tradeCard.isTradable()) {
                                if(!TradeComposer.getTradeComposer().getComponents().getOwnerList().contains(tradeCard)) {
                                    AlertDialog confirmDel = ConfirmDelete().create();
                                    confirmDel.show();


                                }
                                else {
                                    Toast.makeText(v.getContext(), "Card is already in their trade list", Toast.LENGTH_SHORT).show();
                                    cta.finish();
                                }
                            }
                            else {
                                Toast.makeText(v.getContext(), "Card is not tradeable.", Toast.LENGTH_SHORT).show();
                                cta.finish();
                            }
                        } else {
                            Toast.makeText(v.getContext(), "Card could not be added to trade...", Toast.LENGTH_SHORT).show();
                            cta.finish();
                        }

                    }

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

    public CardTradeAdapter(ArrayList<Card> cardList, Boolean state, CardTradeActivity cta){
        this.cardList = cardList;
        this.userState = state;
        this.cta = cta;
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
        viewHolder.cardDescription.setText(cardList.get(position).getCategory());
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
    private static AlertDialog.Builder ConfirmDelete()
    {

        AlertDialog.Builder myQuittingDialogBox =new AlertDialog.Builder(view.getContext());

        myQuittingDialogBox.setTitle("How many would you like to trade?");
        //myQuittingDialogBox.setMessage("Are you sure you want to delete all copies of this card?");
        myQuittingDialogBox.setSingleChoiceItems(getQuantityList(), -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                deletequantity = item;
                deletequantity++; //iterate

            }
        });
        myQuittingDialogBox.setPositiveButton("Trade", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                tradeCard.setQuantity((deletequantity));
                if(userState) {
                    TradeComposer.getTradeComposer().getComponents().addToBorrower(tradeCard);
                } else {
                    TradeComposer.getTradeComposer().getComponents().addToOwner(tradeCard);
                }
                Toast.makeText(view.getContext(), "Card added to your friend's trade list.", Toast.LENGTH_SHORT).show();
                cta.finish();

            }

        });


        myQuittingDialogBox.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        myQuittingDialogBox.create();
        return myQuittingDialogBox;


    }
    private static CharSequence[] getQuantityList(){
        int i;
        CharSequence[] quantityArray = new CharSequence[tradeCard.getQuantity()];

        for(i = 0; i<tradeCard.getQuantity();i++) {
            quantityArray[i] = String.valueOf(i+1);
        }
        return quantityArray;

    }

}
