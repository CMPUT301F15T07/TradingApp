package com.sherpasteven.sscte;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.User;

import java.util.ArrayList;

public class ViewFriendActivity extends AppCompatActivity {

    ArrayList<User> listOfFriends;
    User friend;

    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_friend);

        Intent intent = getIntent();
        int position = intent.getIntExtra("com.sherpasteven.sscte.viewfriend", 0);
        listOfFriends = CurrentProfile.GetCurrentProfile(this).getUser().getFriends();
        friend = listOfFriends.get(position);
        retrieveUserInfo(friend);
    }

    public void retrieveUserInfo(User user){

        //set name
        TextView username = (TextView) findViewById(R.id.txtName);
        username.setText(user.getName());

        //set city Info
        TextView usercity = (TextView) findViewById(R.id.cityInfo);
        usercity.setText(String.valueOf(user.getLocation()));

        //set user email
        TextView useremail = (TextView) findViewById(R.id.emailinfo);
        useremail.setText(String.valueOf(user.getEmail()));

        if(!user.getProfilePic().equals(null)){
            ImageView viewuser = (ImageView) findViewById(R.id.greyRect);
            viewuser.setImageBitmap(user.constructProfilePic());
        }
    }
}
