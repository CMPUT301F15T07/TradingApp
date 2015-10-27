package com.sherpasteven.sscte.Models;

import android.content.Context;

import com.sherpasteven.sscte.Controllers.Controller;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by elias on 17/10/15.
 */
public class Registration extends Model {

    private String userEmail;
    private String userName;
    private String location;
    private transient ISerializer<Profile> profileSerializer;
    private transient RegistrationSerializer registrationSerializer;

    public Registration(){
        userEmail = new String();
        userName = new String();
        location = new String();
        profileSerializer = new LocalProfileSerializer();
        registrationSerializer = new RegistrationSerializer();
    }

    public String getUserName() {
        return userName;
    }

    public String getLocation() {
        return location;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyViews();
    }

    public void setLocation(String location) {
        this.location = location;
        notifyViews();
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        notifyViews();
    }

    // Check to see if the user has entered a valid email address.
    public Boolean isValidEmail() {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(userEmail);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public void generateProfile(Context context){
        User user = new User(getUserName(), getLocation(), getLocation());
        Profile profile = new Profile(user);
        profileSerializer.Serialize(profile, context);
    }

    public void saveRegistration(Context context){
        registrationSerializer.Serialize(this, context);
    }

    public void loadRegistration(Context context){
        Registration loaded = registrationSerializer.Deserialize(null, context);
        if (loaded != null){
            copy(loaded);
        }
    }

    private void copy(Registration registration){
        setUserEmail(registration.getUserEmail());
        setUserName(registration.getUserName());
        setLocation(registration.getLocation());
    }
}
