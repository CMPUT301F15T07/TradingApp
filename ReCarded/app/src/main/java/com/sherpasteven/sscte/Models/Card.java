package com.sherpasteven.sscte.Models;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * This class represents a trading card and all of the data associated with it.
 * These cards will be the basis of the app since they are the objects that users will trade.
 *
 * Issues: the ability for cards to have images needs to be added.
 */
public class Card extends Model {

    private String name;
    private int quantity;
    private Quality quality;
    private String catagory;
    private String series;
    private Boolean tradable;
    private String comments;
    private ArrayList<Image> images;
    private User owner;


    public Card(String name, int quantity, Quality quality, String catagory,
               String series, Boolean tradable, String comments, ArrayList<Image> images , User owner){

        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
        this.catagory = catagory;
        this.series = series;
        this.tradable = tradable;
        this.comments = comments;
        this.images = images;
        this.owner = owner;


    }



    public Card(String name, int quantity, Quality quality, String catagory,
                String series, Boolean tradable, String comments , User owner){

        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
        this.catagory = catagory;
        this.series = series;
        this.tradable = tradable;
        this.comments = comments;
        this.images = new ArrayList<Image>();
        this.owner = owner;


    }

    public Card(String name, int imageID, int quantity, Quality quality, String catagory,
                String series, Boolean tradable, String comments , User owner, Context context){

        BitmapFactory bmf = new BitmapFactory();

        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
        this.catagory = catagory;
        this.series = series;
        this.tradable = tradable;
        this.comments = comments;
        this.images = new ArrayList<Image>();
        this.owner = owner;
        addImage(new Image(imageID, context));
    }

    public Card(String name, Image image, int quantity, Quality quality, String catagory,
                String series, Boolean tradable, String comments , User owner){

        BitmapFactory bmf = new BitmapFactory();

        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
        this.catagory = catagory;
        this.series = series;
        this.tradable = tradable;
        this.comments = comments;
        this.images = new ArrayList<Image>();
        this.owner = owner;
        addImage(image);
    }

    public Image getImagebyIndex(int index){
        if (index < getImages().size()) {
            return getImages().get(index);
        }
        return null;
    }

    public void addImage(Image image){

        getImages().add(image);
    }

    public void addImage(Bitmap image){

        getImages().add(new Image(image));
    }

    public void removeImage(int i){
        getImages().remove(i);

    }

    public void removeImage(Image image){
        getImages().remove(image);

    }

    public Bitmap constructImage(int index){
        return getImages().get(index).constructImage();
    }


    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }


    //public int getImageID() { return imageID; }

    //public void setImageID(int imageID) { this.imageID = imageID; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Boolean isTradable() {
        return tradable;
    }

    public void setTradable(Boolean tradable) {
        this.tradable = tradable;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

  /*  public ArrayList<images> getImages() {
        return images;
    }

    public void setImages(ArrayList<images> images) {
        this.images = images;
    } */

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    /**
     * Identify whether two cards match. Key - the cards must be identical
     * in all values except for quantity.
     * @param card
     * @return true if all used values are equal, or returns false if not.
     */
    public Boolean equals(Card card){
        return this.getName().equals(card.getName()) &&
                this.getQuality().getQuality().equals(card.getQuality().getQuality()) &&
                this.getCatagory().equals(card.getCatagory()) &&
                this.getSeries().equals(card.getSeries()) &&
                this.isTradable().equals(card.isTradable()) &&
                this.getComments().equals(card.getComments()) &&
                this.getOwner().equals(card.getOwner());
    }

    /**
     * get the different cards categories that can be traded
     * @return names of different card types that can be traded
     */
    public static String[] getRelevantCatagories(){

        String relevantcatagories[] = {"Magic The Gathering","Pokemon","YuGiOh","Digimon","Sports","Steam Trading Card",
                "Neopets", "Amiibo Cards", "Shrek Trading Cards", "MISC"};
        return relevantcatagories;
    }
}
