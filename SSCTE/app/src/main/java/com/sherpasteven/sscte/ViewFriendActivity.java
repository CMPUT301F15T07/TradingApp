package com.sherpasteven.sscte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sherpasteven.sscte.Controllers.ViewFriendController;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Friend;
import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Views.IView;

import java.util.ArrayList;

public class ViewFriendActivity extends AppCompatActivity implements IView<Model> {

    ArrayList<Friend> listOfFriends;
    Friend friend;
    int position;

    private ViewFriendController viewfriendcontroller;

    ImageButton removeFriends;
    private Profile profile;

    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_friend);

        viewfriendcontroller = new ViewFriendController(this, friend);

        Intent intent = getIntent();
        position = intent.getIntExtra("com.sherpasteven.sscte.viewfriend", 1);
        listOfFriends = CurrentProfile.getCurrentProfile().getProfile(this).getUser().getFriends();
        friend = listOfFriends.get(position);
        retrieveUserInfo(friend);
    }

    public void retrieveUserInfo(Friend user){

        //set name
        TextView username = (TextView) findViewById(R.id.txtName);
        username.setText(user.getName());

        //set city Info
        TextView usercity = (TextView) findViewById(R.id.cityInfo);
        usercity.setText(String.valueOf(user.getLocation()));

        //set user email
        TextView useremail = (TextView) findViewById(R.id.emailinfo);
        useremail.setText(String.valueOf(user.getEmail()));

        TextView userrating = (TextView) findViewById(R.id.ratingInfo);
        userrating.setText(String.valueOf(user.getRating()));


        if(!user.getProfilePic().equals(null)){
            ImageView viewuser = (ImageView) findViewById(R.id.greyRect);
            viewuser.setImageBitmap(user.constructProfilePic());
        }
    }

    public ImageButton getDeclineButton() {
        return (ImageButton) findViewById(R.id.btnDeleteFriend);

        /**
         * Generates hamburger menu options.
         * @param menu Menu item to be created.
         * @return true
         */
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_friend, menu);
        return true;
    }

    /**
     * OnSelect options for option selected from hamburger menu.
     * @param item Item selected by user.
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_inventory) {
            Intent intent1 = new Intent(this, FriendInventoryActivity.class);
            intent1.putExtra("com.sherpasteven.sscte.friend_inventory", position);
            this.startActivity(intent1);
        }

        return super.onOptionsItemSelected(item);
    }

    public Friend getFriend() {
        return friend;
    }

    public Profile getProfile() {
        return profile;
    }

    @Override
    public void Update(Model model) {

    }
}
