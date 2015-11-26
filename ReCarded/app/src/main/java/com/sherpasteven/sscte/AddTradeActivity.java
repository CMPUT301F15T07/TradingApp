package com.sherpasteven.sscte;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sherpasteven.sscte.Controllers.AddTradeController;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Views.IView;

public class AddTradeActivity extends AppCompatActivity implements IView<Model> {

    private User user;
    private User friend;
    private Trade trade;
    private AddTradeController addtradecontroller;

    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trade);
        setUser(CurrentProfile.getCurrentProfile().getProfile(this).getUser());
        trade = new Trade(user, friend);
        addtradecontroller = new AddTradeController(this, trade);

        if (getIntent().hasExtra("com.sherpasteven.sscte.friend")) {
            friend = user.getFriends().get(getIntent().getIntExtra("com.sherpasteven.sscte.friend", 0));
            setTitle("Trade with " + friend.getName());
        } else {
            Toast.makeText(this, "Invalid friend selected, returning...", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public ImageButton getItemUserBtn() {
        return (ImageButton) this.findViewById(R.id.btnAddUser);
    }

    public ImageButton getItemFriendBtn() {
        return (ImageButton) this.findViewById(R.id.btnAddOther);
    }


    private void setUser(User currentuser) {
        user = currentuser;
    }

    @Override
    public void Update(Model model) {

    }
}
