package com.sherpasteven.sscte;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Views.IView;

public class SettingsActivity extends AppCompatActivity implements IView<Profile>{

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
     * @param profile Updates profile with settings with load.
     */
    @Override
    public void Update(Profile profile) {

    }
}
