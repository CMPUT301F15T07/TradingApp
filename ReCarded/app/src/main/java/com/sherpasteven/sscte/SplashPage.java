package com.sherpasteven.sscte;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sherpasteven.sscte.Controllers.RegisterController;
import com.sherpasteven.sscte.Models.ISerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Registration;
import com.sherpasteven.sscte.Views.IView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashPage extends AppCompatActivity implements IView<Registration> {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;

    private View mContentView;
    private View mControlsView;
    private boolean mVisible;
    private RegisterController registerController;
    private ISerializer<Profile> profileSerializer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_page);
        mVisible = true;
        //mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button enterButton = (Button)findViewById(R.id.btnEnter);
        enterButton.setEnabled(false);
        Registration registration = new Registration();
        registration.addView(this);
        registerController = new RegisterController(this, registration);
    }


    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            getSupportActionBar().show();
            //mControlsView.setVisibility(View.VISIBLE);
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    public void Update(Registration registration) {
        Button submitButton = (Button) findViewById(R.id.btnEnter);

        EditText emailText = (EditText) findViewById(R.id.emailText);
        EditText nameText = (EditText) findViewById(R.id.nameText);
        EditText cityText = (EditText) findViewById(R.id.cityText);

        Drawable nameBgd = getResources().getDrawable(R.drawable.input_rect);
        nameBgd.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.MULTIPLY));

        Drawable cityBgd = getResources().getDrawable(R.drawable.input_rect2);
        cityBgd.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.MULTIPLY));

        Drawable emailBgd = getResources().getDrawable(R.drawable.input_rect3);
        emailBgd.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.MULTIPLY));

        Drawable enterBgd = getResources().getDrawable(R.drawable.input_rect4);
        enterBgd.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.MULTIPLY));

        submitButton.setEnabled(canSubmit(registration));

        if (!canSubmit(registration)) {
            submitButton.setBackground(enterBgd);
            submitButton.setVisibility(View.VISIBLE);
        } else {
            submitButton.setVisibility(View.VISIBLE);
            submitButton.getBackground().clearColorFilter();
            submitButton.invalidate();
        }


        if (!registration.isValidEmail() || registration.getUserEmail().isEmpty()) {
            emailText.setBackground(emailBgd);
            emailText.setVisibility(View.VISIBLE);
        } else {
            emailText.setVisibility(View.VISIBLE);
            emailText.getBackground().clearColorFilter();
            emailText.invalidate();
        }


        if (registration.getUserName().isEmpty()) {
            nameText.setBackground(nameBgd);
            nameText.setVisibility(View.VISIBLE);
        } else {
            nameText.setVisibility(View.VISIBLE);
            nameText.getBackground().clearColorFilter();
            nameText.invalidate();
        }


        if (registration.getLocation().isEmpty()) {
            cityText.setBackground(cityBgd);
            cityText.setVisibility(View.VISIBLE);
        } else {
            cityText.setVisibility(View.VISIBLE);
            cityText.getBackground().clearColorFilter();
            cityText.invalidate();
        }


    }

    public boolean canSubmit(Registration registration){
        boolean a =!registration.getLocation().isEmpty();
        boolean b =!registration.getUserName().isEmpty();
        boolean c =!registration.getUserEmail().isEmpty();

        return !registration.getLocation().isEmpty() &&
                !registration.getUserName().isEmpty() &&
                !registration.getUserEmail().isEmpty() &&
                 registration.isValidEmail();
    }
}
