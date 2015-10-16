package com.sherpasteven.recarded;

/**
 * Created by Joshua on 2015-10-12.
 */
public class Card {

    private String name;
    private int quantity;
    private Quality quality;
    private String catagory;
    private String series;
    private Boolean tradable;
    private String comments;
    private ArrayList<images> images;
    private User owner;


    public Card(String name, int quantity, Quality quality, String catagory,
               String series, Boolean tradable, String comments, ArrayList<images> images, User owner){

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

    public Boolean getTradable() {
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

    public ArrayList<images> getImages() {
        return images;
    }

    public void setImages(ArrayList<images> images) {
        this.images = images;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
