package com.sherpasteven.sscte.Models;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used to provide the necessary info to the SearchInventoryClass when trying to search
 * a specific inventory
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

    /**
     * Resets the componants for the search
     */
    public void reset(){
        this.inventory = null;
        this.searchterm= null;
        this.searched = null;
    }

    /**
     * Iterates through the searched Inventory and provides the cards that contain
     * information that is searched for.
     * @return searched cards
     */
    public ArrayList<Card> search(){

        searched = new ArrayList<Card>();

        for(Card card: getInventory()){
            if(searcher(getSearchterm(), card)){
                searched.add(card);
            }
        }

        return searched;

    }

    /**
     * Searches the card's title, comments and catagory for the given search term using regular
     * expressions and returns if there was a match found
     * @param term
     * @param card
     * @return matcher.matches
     */
    private Boolean searcher(String term, Card card){

        String line = card.getCategory() + " " + card.getName() + " " + card.getComments();
        String pattern = "(.*" + term + ".*)";

        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        return m.matches();
    }
}
