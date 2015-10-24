package com.sherpasteven.sscte.Models;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by elias on 17/10/15.
 */
public class Registration extends Model {

    private String userEmail;
    private String userName;
    private String location;

    public Registration(){
        userEmail = new String();
        userName = new String();
        location = new String();
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




    // The following save and load are public domain written by Dr. Abram Hindle
    //Save the users information into app data.
    /*
    public void saveToFile(String fileName, Context context) {
        try {
            userInfo.clear();
            userInfo.add(0, userName);
            userInfo.add(1, location);
            userInfo.add(2, userEmail);

            FileOutputStream fos = context.openFileOutput(fileName, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(userInfo, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }

    //Get users information from app data.
    public void loadFromFile(String fileName, Context context) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            Type listType = new TypeToken<ArrayList<String>>() {}.getType();
            userInfo = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            userInfo = new ArrayList<>();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }*/
}
