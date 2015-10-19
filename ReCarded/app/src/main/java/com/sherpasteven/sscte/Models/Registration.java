package com.sherpasteven.sscte.Models;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by elias on 17/10/15.
 */
public class Registration extends Model {
    private String fileName;
    private Context context;

    // Will contain [userName, userCity, userEmail]
    private ArrayList<String> userInformation;

    public Registration(String fileName, Context context) {
        this.fileName = fileName;
        this.context = context;
        userInformation = new ArrayList<>();
        loadFromFile();
    }

    // Get the user information from the Register UI.
    private void getUserInfo() {
        // Add getters once the UI is built.
        // userInformation.add(0, userName);
        // userInformation.add(0, userCity);
        // userInformation.add(0, userEmail);
    }

    // Check to see if the user has entered a valid email address.
    private Boolean validEmailChecker() {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(userInformation.get(2));
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    // The following save and load are public domain written by Dr. Abram Hindle
    //Save the users information into app data.
    public void saveToFile() {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(userInformation, writer);
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
    public void loadFromFile() {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html
            Type listType = new TypeToken<ArrayList<String>>() {}.getType();
            userInformation = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            userInformation = new ArrayList<>();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
    }
}
