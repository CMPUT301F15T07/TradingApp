package com.sherpasteven.sscte;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Views.IView;

public class ViewFriendActivity extends AppCompatActivity implements IView<Model> {

    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_friend);
    }

    @Override
    public void Update(Model model) {

    }
}
