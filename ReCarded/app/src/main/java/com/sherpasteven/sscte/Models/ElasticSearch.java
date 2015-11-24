package com.sherpasteven.sscte.Models;


import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

/**
 * Created by elias on 16/11/15.
 */
public class ElasticSearch {

    private static String SearchUrl = "http://cmput301.softwareprocess.es:8080/cmput301f15t07/profile/_search";
    private static String insertUrl = "http://cmput301.softwareprocess.es:8080/cmput301f15t07/profile/";

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

    public Profiles getProfiles(){
        return profiles;
    }

    public void InsertProfile(final Profile profile) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    _insertProfile(profile);
                } catch (IOException ex){
                    throw new RuntimeException(ex);
                }

            }
        });

    }

    /**
     * Consumes the POST/Insert operation of the service
     * @throws IOException
     * @throws IllegalStateException
     */
    private void _insertProfile(Profile profile) throws IllegalStateException, IOException{
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(insertUrl+profile.getProfileId());
        StringEntity stringentity = null;
        try {
            stringentity = new StringEntity(gson.toJson(profile));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        httpPost.setHeader("Accept","application/json");

        httpPost.setEntity(stringentity);
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String status = response.getStatusLine().toString();
        System.out.println(status);
        HttpEntity entity = response.getEntity();
        BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
        String output;
        System.err.println("Output from Server -> ");
        while ((output = br.readLine()) != null) {
            System.err.println(output);
        }

        try {
            entity.consumeContent();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        httpPost.getEntity().getContent().close();
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
