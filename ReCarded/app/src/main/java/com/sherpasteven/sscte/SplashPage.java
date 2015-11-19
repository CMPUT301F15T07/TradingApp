package com.sherpasteven.sscte;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sherpasteven.sscte.Controllers.RegisterController;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.IDeSerializer;
import com.sherpasteven.sscte.Models.ISerializer;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
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

    /* this private profile is for the use of testing
            Gui before the serialization to ES is complete.
            It can be removed after serialization can be tested
         */

    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //if a profile already exists, there is no need to register
        //Profile localProfile = getLocalProfile();
        Profile localProfile = CurrentProfile.GetCurrentProfile(this);
        if (localProfile != null) navigateToInventory();

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_page);
        mVisible = true;
        //mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Button enterButton = getEnterButton();
        enterButton.setEnabled(false);
        Registration registration = new Registration();
        registration.addView(this);
        registerController = new RegisterController(this, registration);
    }

    /** (not Javadoc)
     * @see android.app.Activity#onPause()
     */
    @Override
    protected void onPause(){
        super.onPause();
        //registerController.saveRegistration(this);
    }

    /** (not Javadoc)
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume(){
        super.onResume();
        //registerController.loadRegistration(this);
    }

    /**
     * Generates intent and moves application to inventory page.
     */
    public void navigateToInventory(){
        startActivity(new Intent(this, InventoryActivity.class));
    }

    public void setLocalProfile(Profile profile) {
        ISerializer<Profile> serializer = new LocalProfileSerializer();
        serializer.Serialize(profile, this);
    }

    /**
     * Serialises the profile (getter) for application registry.
     * @return deserialized profile information.
     */
    private Profile getLocalProfile() {
        IDeSerializer<Profile> deSerializer = new LocalProfileSerializer();
        return deSerializer.Deserialize(null, this);
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

    /**
     * Updates the registration conditions depending on the accuracy of the application.
     * //@param registration Registration parameters for registering the user.
     */

    public Button getEnterButton(){
        return (Button) findViewById(R.id.btnEnter);
    }

    public EditText getEmailText(){
        return (EditText) findViewById(R.id.emailText);
    }

    public EditText getNameText(){
        return (EditText) findViewById(R.id.nameText);
    }

    public EditText getCityText(){
        return (EditText) findViewById(R.id.cityText);
    }


    public void Update(Registration registration) {
        Button submitButton = getEnterButton();

        EditText emailText = getEmailText();
        EditText nameText = getNameText();
        EditText cityText = getCityText();

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

    /**
     * Determines the status of the registration page; if all entries are satisfactory.
     * @param registration Registration parameters to be checked.
     * @return boolean for correct identification value.
     */
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
