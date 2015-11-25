package com.sherpasteven.sscte.Controllers;

import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;
import com.sherpasteven.sscte.AddTradeActivity;
import com.sherpasteven.sscte.CardTradeActivity;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Trade;
        import java.io.Serializable;


/**
 * Created by ansonli on 2015-11-25.
 */
public class AddTradeController extends Controller<AddTradeActivity, Trade> {

    private final AddTradeActivity view;
    private final Trade model;

    ImageButton addUser;
    ImageButton addOther;

    private LocalProfileSerializer profileSerializer = new LocalProfileSerializer();

    /**
     * Instantiates the controller on the proper activity.
     *
     * @param view
     * @param model
     */
    public AddTradeController(AddTradeActivity view, Trade model) {
        super(view, model);
        this.view = view;
        this.model = model;
    }

    @Override
    protected void setListeners(AddTradeActivity view) {
        addUser = view.getItemUserBtn();
        addOther = view.getItemFriendBtn();
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send to activity with the trade in hand.
                Intent myIntent = new Intent(v.getContext(), CardTradeActivity.class);
                myIntent.putExtra("com.sherpasteven.sscte.trade", (Parcelable)model);
                // pass this to the inventory activity // i made a temp one to hold 'inventory cards'
                // no longer a fragment
                // inventory activity selects a card
                // 'sends' back
                myIntent.putExtra("com.sherpasteven.sscte.user", true);
                myIntent.putExtra("com.sherpasteven.sscte.friend", false);
                v.getContext().startActivity(myIntent);

            }
        });
        addOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), CardTradeActivity.class);
                myIntent.putExtra("com.sherpasteven.sscte.user", false);
                myIntent.putExtra("com.sherpasteven.sscte.friend", true);
                v.getContext().startActivity(myIntent);

            }
        });

    }
}
