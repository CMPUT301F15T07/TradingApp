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
 * Created by elias on 17/10/15.
 */
//Code base off of:
//https://github.com/joshua2ua/lonelyTwitter/blob/f15monday/app/src/main/java/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivity.java
public class LocalProfileSerializer implements ISerializer<Profile>, IDeSerializer<Profile> {

    private String profileLocation = "userProfile.sav";

    @Override
    public Profile Deserialize(Object id, Context context) {
        Profile profile;
        try {
            FileInputStream fis = context.openFileInput(profileLocation);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html retrieved 2015-09-21
            Type listType = new TypeToken<Profile>() {}.getType();
            profile = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return profile;
    }

    @Override
    public void Serialize(Profile item, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(profileLocation,
                    0);
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
