package com.sherpasteven.sscte.Models;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by joshua on 28/11/15.
 */
public class SearchSingleton {

    private ArrayList<Card> inventory = null;
    private String searchterm = null;
    private static SearchSingleton ourInstance = new SearchSingleton();

    public static SearchSingleton getSearchSingleton() {
        return ourInstance;
    }

    private SearchSingleton() {
    }

    public ArrayList<Card> getInventory(){
        return this.inventory;
    }

    public String getSearchterm(){
        return this.searchterm;
    }

    public void setInventory(ArrayList<Card> inventory){
        this.inventory = inventory;
    }

    public void setSearchterm(String searchterm){
        this.searchterm = searchterm;
    }

    public void reset(){
        this.inventory = null;
        this.searchterm= null;
    }

    public ArrayList<Card> search(){

        ArrayList<Card> searched = new ArrayList<Card>();

        for(Card card: getInventory()){
            if(searcher(getSearchterm(), card)){
                searched.add(card);
            }
        }

        return searched;

    }

    private Boolean searcher(String term, Card card){

        String line = card.getCategory() + " " + card.getName() + " " + card.getComments();
        String pattern = "(.*" + term + ".*)";

        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        return m.matches();
    }
}
