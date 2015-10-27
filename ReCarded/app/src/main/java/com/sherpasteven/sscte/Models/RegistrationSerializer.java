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

/**
 * Created by elias on 26/10/15.
 */
public class RegistrationSerializer implements ISerializer<Registration>, IDeSerializer<Registration> {

    private String registrationLocation = "registration.sav";

    @Override
    public Registration Deserialize(Object id, Context context) {
        Registration registration;
        try {
            FileInputStream fis = context.openFileInput(registrationLocation);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html retrieved 2015-09-21
            Type listType = new TypeToken<Registration>() {}.getType();
            registration = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            registration = null;
        } catch (IOException e) {
            registration = null;
        }
        return registration;
    }

    @Override
    public void Serialize(Registration item, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(registrationLocation, 0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(item, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
