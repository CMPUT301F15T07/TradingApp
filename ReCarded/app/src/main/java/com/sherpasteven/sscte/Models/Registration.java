package com.sherpasteven.sscte.Models;

import android.content.Context;

import com.sherpasteven.sscte.Controllers.Controller;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * This class is the main model object that represents
 * registering a new user into the app. This class takes
 * in the arguments needed to generate a new user and then
 * can generate the user once all required fields are filled;
 *
 * FIXME: There is an issue serializing the Registration object using GSON
 * FIXME: Getting a security exception when serializing the object to file.
 */
public class Registration extends Model {

    private String userEmail;
    private String userName;
    private String location;
    private transient ISerializer<Profile> profileSerializer;
    //private transient RegistrationSerializer registrationSerializer;

    public Registration(){
        userEmail = "";
        userName = "";
        location = "";
        profileSerializer = new LocalProfileSerializer();
        //registrationSerializer = new RegistrationSerializer();
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


    /**
     * Checks if an email is in the correct format.
     * Does not check if the email actually exists.
     * @return is email valid
     */
    public Boolean isValidEmail() {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(getUserEmail());
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    /**
     * Generates a new user profile and saves it.
     * @param context context of app
     */
    //changed from void to Profile for Gui testing of the Splash page
    //Can be removed once serializtion becomes operational and tested
    public Profile generateProfile(Context context){
        User user = new User(getUserName(), getLocation(), getUserEmail());
        Profile profile = new Profile(user);
        profileSerializer.Serialize(profile, context);
        return profile;
    }

    /**
     * save this registration object so that it can
     * be reloaded at a later time
     * @param context context of app
     */
    public void saveRegistration(Context context){
        //registrationSerializer.Serialize(this, context);
    }

    /**
     * load a registration object from file and copy it into
     * this registration
     * @param context context of app
     */
    public void loadRegistration(Context context){
        //Registration loaded = registrationSerializer.Deserialize(null, context);
        //if (loaded != null){
        //    copy(loaded);
        //}
    }

    /**
     * copy a registration object into this registration object
     * @param registration object to copy from (source)
     */
    private void copy(Registration registration){
        setUserEmail(registration.getUserEmail());
        setUserName(registration.getUserName());
        setLocation(registration.getLocation());
    }
}
