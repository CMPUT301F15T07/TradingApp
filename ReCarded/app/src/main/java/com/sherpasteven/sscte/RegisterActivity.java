package com.sherpasteven.sscte;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.sherpasteven.sscte.Controllers.Controller;
import com.sherpasteven.sscte.Controllers.RegisterController;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.Registration;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Views.RegisterView;

public class RegisterActivity extends AppCompatActivity {

    private Registration newRegistration;
    private RegisterController registerController;
    private RegisterView registerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerView = new RegisterView();
        newRegistration = new Registration("userInfo.sav", this);
        registerController = new RegisterController(registerView, newRegistration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
