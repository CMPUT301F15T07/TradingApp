package com.sherpasteven.sscte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Views.IView;

public class SettingsActivity extends AppCompatActivity implements IView<Profile>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    @Override
    public void Update(Profile profile) {

    }
}
