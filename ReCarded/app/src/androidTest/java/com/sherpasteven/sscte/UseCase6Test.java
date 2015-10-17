package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * Created by elias on 17/10/15.
 */
public class UseCase6Test extends ApplicationTestCase<Application> {
    public UseCase6Test(Class<Application> applicationClass) {
        super(applicationClass);
    }

//Use Case Name: User uploads and deletes photos of their cards
//Participating Actors: Owner, Other Users
//Goal: User can upload and delete pictures of cards they can own.
//Trigger: User selects edit photos on a card in thier inventory
//Precondition: if a user want to upload a card image they have it available.
//Postcondition: On success, pitures are uploaded for owner and other users to see on upload. On delete, owner and other users cannot see deleted picture
//Related User Stories: US06.01.01 - US06.04.01

//Basic Flow
// 1.) Owner selects a card from thier inventory to add an image
// 2.) Owner initiates add dialog and a picture browsing routine is brought up
// 3.) Owner selects image(s) to be uploaded to card display
// 4.) Owner and Other Users can now see uploaded images of card
// 5.) Owner selects a card from thier inventory to delete an image from it
// 6.) Owner selects image(s) from dialog to remove from card
// 7.) Image(s) removed can no longer be seen by Owner or Other Users

//Exceptions
// 3.1.) If image is over 65536 bytes
// 3.2.) System displays an error describing limitation
// 3.3.) System returns to step 3
// 7.1.) Other Users have deleted image downloaded locally (See US06.05.01, US09.03.01, and US10.01.01)
// 7.2.) Locally downloaded copies of an image on Other User's devices are deleted once they reconnect to the service if they were offline.



//Claimant attaches photograph to an item
    void testUS030201(){
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
    void testUS060201(){
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
    void testUS060301(){
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
    void testUS060401{
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


//Use Case Name: Downloading Specific Images
//Participating Actors: Borrower, Owner
//Goal: Borrower can select specific images to download even if they have downloads disabled
//Trigger: Borrower is browsing through a photo album of a Owner's card
//Precondition: Borrower must select image to be downloaded
//Postcondition: On success, pictures that were selected to be downloaded will be available offline
//Related User Stories: US06.05.01

//Basic Flow
// 1.) Borrower is browsing through images of one of Owner's cards, but has local downloads turned off (See US10.01.01)
// 2.) Borrower select an image in the album
// 3.) On first time image selection, system prompts message about being able to download images
// 4.) Image is highlighted or watermarked to notify that image is locally cached
// 5.) Borrower can disconnect from internet and still browse specific images that they downloaded


//Borrower has photo downloads disabled
//Borrower manually downloads item images
    void testUS600501{
        User user = GetMockUser();
        Inventory inventory = owner.getInventory();
        Card card = inventory.searchByText("Black Lotus");
        List<Image> images = card.getImages();
        //saveLocalResource(int ownerHashCode, string key, object value);
        saveLocalResource(card.hashCode, "images", images);
    }
}
