package com.sherpasteven.sscte.Models;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by joshua on 28/11/15.
 */
public class SearchSingleton {

    private ArrayList<Card> inventory = null;
    private ArrayList<Card> searched = null;
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
    public ArrayList<Card> getSearchedInventory(){
        return this.searched;
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
        this.searched = null;
    }

    public ArrayList<Card> search(){

        searched = new ArrayList<Card>();

        for(Card card: getInventory()){
            if(searcher(getSearchterm(), card)){
                searched.add(card);
            }
        }

        return searched;

    }

    private Boolean searcher(String term, Card card){

        String line = card.getCatagory() + " " + card.getName() + " " + card.getComments();
        String pattern = "(.*" + term + ".*)";

        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        return m.matches();
    }
}
