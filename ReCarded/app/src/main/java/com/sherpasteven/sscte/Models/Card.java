package com.sherpasteven.sscte.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * This class represents a trading card and all of the data associated with it.
 * These cards will be the basis of the app since they are the objects that users will trade.
 */
public class Card extends Model {

    private String name;
    private int quantity;
    private Quality quality;
    private String category;
    private String series;
    private Boolean tradeable;
    private String comments;
    private ArrayList<Image> images;
    private String owner;


    public Card(String name, int quantity, Quality quality, String catagory,
               String series, Boolean tradable, String comments, ArrayList<Image> images , User owner){

        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
        this.category = catagory;
        this.series = series;
        this.tradeable = tradable;
        this.comments = comments;
        this.images = images;
        setOwner(owner);
    }

    public Card(String name, int quantity, Quality quality, String catagory,
                String series, Boolean tradable, String comments, ArrayList<Image> images , String owner){

        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
        this.category = catagory;
        this.series = series;
        this.tradeable = tradable;
        this.comments = comments;
        this.images = images;
        this.owner = owner;


    }



    public Card(String name, int quantity, Quality quality, String category,
                String series, Boolean tradable, String comments , User owner){

        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
        this.category = category;
        this.series = series;
        this.tradeable = tradable;
        this.comments = comments;
        this.images = new ArrayList<>();
        setOwner(owner);


    }

    public Card(String name, int imageID, int quantity, Quality quality, String catagory,
                String series, Boolean tradeable, String comments , User owner, Context context){

        BitmapFactory bmf = new BitmapFactory();

        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
        this.category = catagory;
        this.series = series;
        this.tradeable = tradeable;
        this.comments = comments;
        this.images = new ArrayList<>();
        setOwner(owner);
        addImage(new Image(imageID, context));
    }

    public Card(String name, Image image, int quantity, Quality quality, String catagory,
                String series, Boolean tradable, String comments , User owner){

        this.name = name;
        this.quantity = quantity;
        this.quality = quality;
        this.category = catagory;
        this.series = series;
        this.tradeable = tradable;
        this.comments = comments;
        this.images = new ArrayList<>();
        setOwner(owner);
        addImage(image);
    }

    /**
     * Retrieves the image from the album of a card.
     * @param index of image to pull
     * @return image from card structure
     */
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

    /**
     * Constructs the image in the card.
     * @param index of images' position in the album.
     * @return the bitmap constructed from the image.
     */
    public Bitmap constructImage(int index){
        return getImages().get(index).constructImage();
    }


    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyViews();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        notifyViews();
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
        notifyViews();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        notifyViews();
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
        notifyViews();
    }

    public Boolean isTradable() {
        return tradeable;
    }

    public void setTradable(Boolean tradable) {
        this.tradeable = tradable;
        notifyViews();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
        notifyViews();
    }

    public void setImageByPosition(Image image, int position){
        getImages().set(position, image);
        notifyViews();
    }


    public String getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner.getEmail() + "," + owner.getName() + "," + owner.getLocation();
    }


    /**
     * Identify whether two cards match. Key - the cards must be identical
     * in all values except for quantity.
     * @param card tested for equality.
     * @return true if all used values are equal, or returns false if not.
     */
    public Boolean equals(Card card){
        return this.getName().equals(card.getName()) &&
                this.getQuality().getQuality().equals(card.getQuality().getQuality()) &&
                this.getCategory().equals(card.getCategory()) &&
                this.getSeries().equals(card.getSeries()) &&
                this.isTradable().equals(card.isTradable()) &&
                this.getComments().equals(card.getComments()) &&
                this.getOwner().equals(card.getOwner());
    }

    /**
     * Get the different cards categories that can be traded
     * @return names of different card types that can be traded
     */
    public static String[] getRelevantCategories(){

        String relevantcategories[] = {"Magic The Gathering","Pokemon","YuGiOh","Digimon","Sports","Steam Trading Card",
                "Neopets", "Amiibo Cards", "Shrek Trading Cards", "MISC"};
        return relevantcategories;
    }
}
