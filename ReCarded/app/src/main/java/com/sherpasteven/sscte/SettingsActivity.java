package com.sherpasteven.sscte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Views.IView;

public class SettingsActivity extends AppCompatActivity implements IView<Model>{

    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
    }

    /**
     * Updates the activity based on raised condition.
     * @param model Updates profile with settings with load.
     */
    @Override
    public void Update(Model model) {

    }
}
