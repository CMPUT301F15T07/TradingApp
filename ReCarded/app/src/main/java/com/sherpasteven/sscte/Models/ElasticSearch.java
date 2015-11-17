package com.sherpasteven.sscte.Models;


import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * Created by elias on 16/11/15.
 */
public class ElasticSearch {

    public static String SearchUrl = "http://cmput301.softwareprocess.es:8080/cmput301f15t07/profile/_search";

    private Gson gson;
    private Profiles profiles;

    public ElasticSearch(){
        gson = new Gson();
        //this should get all profiles. Do this on background thread
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                searchProfiles("*", null);
            }
        });
    }



    LastModified GetLastModified(ProfileId profileId){
        return null;
    }

    /**
     * search method from cmput 301 lab
     * @param searchString
     * @param field
     */

    public void searchProfiles(String searchString, String field) {
        profiles = new Profiles();

        /**
         * Creates a search request from a search string and a field
         */

        HttpPost searchRequest = new HttpPost(SearchUrl);

        String[] fields = null;
        if (field != null) {
            throw new UnsupportedOperationException("Not implemented!");
        }

        SimpleSearchCommand command = new SimpleSearchCommand(searchString);

        String query = gson.toJson(command);

        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(query);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        searchRequest.setHeader("Accept", "application/json");
        searchRequest.setEntity(stringEntity);

        HttpClient httpClient = new DefaultHttpClient();

        HttpResponse response = null;
        try {
            response = httpClient.execute(searchRequest);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /**
         * Parses the response of a search
         */

        Type searchResponseType = new TypeToken<SearchResponse<Profile>>() {
        }.getType();
        SearchResponse<Profile> esResponse;
        try {
            esResponse = gson.fromJson(
                    new InputStreamReader(response.getEntity().getContent()),
                    searchResponseType);
        } catch (JsonIOException e) {
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException(e);
        } catch (IllegalStateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Extract the movies from the esResponse and put them in result
        Hits<Profile> getHits = esResponse.getHits();

        for (SearchHit<Profile> hit : getHits.getHits()){
            profiles.add(hit.getSource());
        }
    }
}
