package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.User;

/**
 * Created by elias on 17/10/15.
 */
public class PhotographTest extends ApplicationTestCase<Application> {
    public PhotographTest(Class<Application> applicationClass) {
        super(applicationClass);
    }


//Claimant attaches photograph to an item
    void testAddImage(){
        String name = "Charizard";
        Integer quantity = 2;
        Quality quality = new Quality(73);
        String catagory = "Pokemon";
        String series = "series";
        boolean tradable = true;
        String comments = "Gently bent edge, 100HP";
        Card card = new Card(name, quantity, quality, catagory, series, tradable, comments);

        //user navigates in the create a card GUI to the "Add images"
        // section and can add images from camera or take a new one
        Image firstImage = new Image("myImageUrl");
        Image secondImage = new Image("myImageUrl");
        card.addImage(firstImage);
        card.addImage(secondImage);
        List<Image> cardImages = card.getImages();
        assertTrue(cardImages.contains(firstImage));
        assertTrue(cardImages.contains(secondImage));
    }


//Owner has an Item
//Owner gets photos attached to items
    void testGetImage(){
        String name = "Charizard";
        Integer quantity = 2;
        Quality quality = new Quality(73);
        String catagory = "Pokemon";
        String series = "series";
        boolean tradable = true;
        String comments = "Gently bent edge, 100HP";
        Card card = new Card(name, quantity, quality, catagory, series, tradable, comments);


        //user navigates in the create a card GUI to the "Add images"
        // section and can add images from camera or take a new one
        Image firstImage = new Image("myImageUrl");
        Image secondImage = new Image("myImageUrl");
        card.addImage(firstImage);
        card.addImage(secondImage);
        List<Image> cardImages = card.getImages();
        assertTrue(cardImages.contains(firstImage));
        assertTrue(cardImages.contains(secondImage));
    }


//User has an Item
//User deletes image from item
    void testDeleteImage(){
        String name = "Charizard";
        Integer quantity = 2;
        Quality quality = new Quality(73);
        String catagory = "Pokemon";
        String series = "series";
        boolean tradable = true;
        String comments = "Gently bent edge, 100HP";
        Card card = new Card(name, quantity, quality, catagory, series, tradable, comments);
        Image firstImage = new Image("myImageUrl");
        Image secondImage = new Image("myImageUrl");
        card.addImage(firstImage);
        card.addImage(secondImage);

        List<Image> cardImages = card.getImages();
        assertTrue(cardImages.contains(firstImage));
        assertTrue(cardImages.contains(secondImage));

        //user from the edit inventory item GUI navigates to the
        //images view and can remove, add or rearrange images
        card.deleteImage(firstImage);
        List<Image> cardImages = card.getImages();
        assertTrue(!cardImages.contains(firstImage));
        assertTrue(cardImages.contains(secondImage));
    }


//User adds an Image larger than 65536 bytes
//Card notifies that it cannot add a image file of that size
    //NOTE!: App is being deigned to scale any image to fit the size limit - Josh
    void testAddImageTooLarge(){
        Boolean exceptionThrown = false;
        Image image = new Image("largeImageUrl");
        try {
            String name = "Charizard";
            Integer quantity = 2;
            Quality quality = new Quality(73);
            String catagory = "Pokemon";
            String series = "series";
            boolean tradable = true;
            String comments = "Gently bent edge, 100HP";

            Card card = new Card(name, quantity, quality, catagory, series, tradable, comments);
            card.addImage(image); //this should throw IOexption

        } catch (IOexception ex) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }


    void testSaveLocalResource{
        User user = GetMockUser();
        Inventory inventory = owner.getInventory();
        Card card = inventory.searchByText("Black Lotus");
        List<Image> images = card.getImages();
        //saveLocalResource(int ownerHashCode, string key, object value);
        saveLocalResource(card.hashCode, "images", images);
    }
}
