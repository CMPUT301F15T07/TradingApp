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
 * This class is responsible for doing the
 * raw http requests to the elasticsearch server.
 */
public class ElasticSearch extends Model {

    private static String SearchUrl = "http://cmput301.softwareprocess.es:8080/cmput301f15t07/profile/_search";
    private static String insertUrl = "http://cmput301.softwareprocess.es:8080/cmput301f15t07/profile/";

    private Gson gson;
    public Friends friends;

    public ElasticSearch(){
        gson = new Gson();
    }


    public Friends getFriends(){
        return friends;
    }

    /**
     * Insert a friend into elasticsearch.
     * This will use the friend.ProfileId to
     * index in elasticsearch. The profileId is
     * a unique string from the android device so
     * there can only be one instance of a friend
     * in elasticsearch per android device.
     * This method is asynchronous and there is no
     * return value.
     * @param friend friend to insert/update in elasticsearch
     */
    public void InsertFriend(final Friend friend) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    _insertFriend(friend);
                } catch (IOException ex){
                    //TODO(what do we do when we cant get to elasticsearch?)
                }
            }
        });

    }

    /**
     * Asynchronous operation of inserting a friend
     * into elasticsearch. This will insert the friend
     * or overwrite an existing friend with the same
     * profileId.
     * @param friend friend to insert/update into elasticsearch
     * @throws IllegalStateException
     * @throws IOException
     */
    private void _insertFriend(Friend friend) throws IllegalStateException, IOException{
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(insertUrl+friend.getProfileId().GetId());
        StringEntity stringentity = null;
        try {
            stringentity = new StringEntity(gson.toJson(friend));
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
        if (response == null) return;
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
     * Search friends in elasticsearch. This is an async
     * method that will request profiles from elasticsearch
     * and then call notifyviews() when the profiles have
     * succesfully been loaded into the attribute friends.
     * To search all profiles simply call searchFriends("*", null)
     * @param searchString search query
     * @param field fields to search on
     */
    public void searchFriends(final String searchString, final String field) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    _searchFriends(searchString, field);
                } catch (RuntimeException ex){
                    //TODO(what to do when we cant get to elasticsearch?)
                }
            }
        });
    }

    /**
     * Asynchronous operation of searching a friend
     * from elasticsearch. This will get the result
     * from the request and then call notifyviews()
     * after the "friends" attirbute is populated
     * @param searchString search query
     * @param field fields to search on
     */
    private void _searchFriends(String searchString, String field) {
        friends = new Friends();

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

        Type searchResponseType = new TypeToken<SearchResponse<Friend>>() {
        }.getType();
        SearchResponse<Friend> esResponse;
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
        Hits<Friend> getHits = esResponse.getHits();

        for (SearchHit<Friend> hit : getHits.getHits()){
            friends.add(hit.getSource());
        }
        notifyViews();
    }
}
