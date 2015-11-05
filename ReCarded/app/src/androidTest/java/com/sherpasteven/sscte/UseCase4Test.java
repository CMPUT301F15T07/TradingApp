package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.User;

/**
 * Created by elias on 17/10/15.
 */
public class UseCase4Test extends ApplicationTestCase<Application> {
    public UseCase4Test() {
        super(Application.class);
    }

//Use Case Name: A Borrower trades with a friend
//Participating Actors: Borrower, Owner, Inventory, Trade
//Goal: A borrower can trade 0 to many cards for 0 to many cards that are owned by the
    //borrowers friend. The friend can either accept or decline the trade.
    //Trigger: Borrower clicks the trade button to initiate a trade with a friend.
//Precondition: Borrower has a friend in which the borrower can trade with.
//Postcondition: Borrower has sent the trade request to the borrower’s friend
    //and the friend has accepted the trade.
//Related User Stories: US04.01.01 - US04.07.01

    //Basic Flow
// 1)Borrower click the trade button and chooses a friend to trade with.
// 2)Borrower adds and removes 0 to many cards that they are willing to trade.
// 3)Borrower adds and removes 0 to many cards that they want from their friend.
// 4)Borrower presses the send trade button to send the trade notification,
    //to their friend.
    // 5)Owner gets the trade notification and opens it up.
// 6)Owner accepts trade.
// 7)Email opens up for the owner.
// 7)Owner adds comments to send to the borrower telling that person how
    //they will be able to go about doing the trade.
// 8)An email is sent to the Owner and the Borrower with the trade information.

    //Exceptions
// 2) User decides to stop the trade.
// 2.1) User presses the back button. The state will not be saved.
// 2.2) The system exits the use case
// 3) User decides to stop the trade.
// 3.1) User presses the back button. The state will not be saved.
// 3.2) The system exits the use case
// 6.1) Owner presses the decline button to decline the trade
// 6.1.1) The app will remove the notification and save the trade in the
    //trades list.
    // 6.1.2) The system will exit the use case.
// 6.2) Owner presses the counter offer button to offer another trade
// 6.2.1) System resets to step 2, except the Owner will become the Borrower
    //and the Borrower will become the Owner.

// US04.01.01
// Borrower wants to trade a card with the owner.
// Send a trade request to an owner of a card and check if the owner has that request.

    public void testUS040101() {


        String name = "Charizard";
        int quantity = 2;
        Quality quality = new Quality(73);
        String catagory = "Pokemon";
        String series = "Basic XY Red";
        boolean tradable = true;
        String comments = "Gently bent edge, 100HP";

        String newname = "Blue Eyes White Dragon";
        int newquantity = 9;
        Quality newquality = new Quality(3);
        String newcatagory = "YuGiOh";
        String newseries = "stupid series";
        boolean newtradable = true;
        String newcomments = "Dime a dozen";


        User Joshua = new User("Joshua", "Canada", "jjwhite@ualberta.ca");
        User Salim = new User("Salim", "Canada", "salim@ualberta.ca");

        Card Charizard = new Card(name, R.drawable.mask_image, quantity, quality, catagory, series, tradable, comments, Joshua);
        Card BEWD = new Card(newname, R.drawable.mask_image, newquantity, newquality, newcatagory, newseries, newtradable, newcomments, Salim);

        Joshua.addInventoryItem(Charizard);
        Salim.addInventoryItem(BEWD);

        // Joshua sees the Blue Eyes white Dragon that he wants to borrow from Salim
        // He clicks the card and initializses the trade and that card is listed under Salim (Owner) in the
        // Trade list becaus it is Salims Card, and Josh's Charizard shows up in the Borrower list because
        //He is the borrower
        Trade trade = new Trade(Joshua, Salim);
        trade.addBorrowList(Joshua.returnInventoryItem(Charizard));
        trade.addOwnerList(Salim.returnInventoryItem(BEWD));



        // Send trade offer button clicked.
        trade.sendTrade();
        //Cant test sending network functionality at this time

        //trade.setNotification();


        assertTrue(!Joshua.getTrades().getPendingTrades().isEmpty());

        //the trade view should then show these cards listed to the current owners for reivew
    }

// US04.02.01
// Test to see if the owner has a trade notification.

   /* public void testUS040201 () {
        User borrower = new User(currentUser.Name, "Canada");
        User owner = new User("Mr. Bean", "Canada");

        // Borrower adds cards with the "plus" button.
        Trade trade = new Trade(borrower, owner);
        trade.list1.add(borrower.getInventory().getCard("Darkrai"));
        trade.list2.add(owner.getInventory().getCard("Mewtwo"));

        // Send trade offer button clicked.
        trade.sendTrade(owner);
        trade.setNotification(owner);

        assertTrue(owner.getNotifications().contains(trade.setNotification(owner)));
    } */

// US04.03.01
// Owner can accept or decline notification.

    public void testUS040301() {
        String name = "Charizard";
        int quantity = 2;
        Quality quality = new Quality(73);
        String catagory = "Pokemon";
        String series = "Basic XY Red";
        boolean tradable = true;
        String comments = "Gently bent edge, 100HP";

        String newname = "Blue Eyes White Dragon";
        int newquantity = 9;
        Quality newquality = new Quality(3);
        String newcatagory = "YuGiOh";
        String newseries = "stupid series";
        boolean newtradable = true;
        String newcomments = "Dime a dozen";

        String name3 = "Gingy";
        int quantity3 = 6;
        Quality quality3 = new Quality(100);
        String catagory3 = "Shrek";
        String series3 = "Super rare";
        boolean tradable3 = true;
        String comments3 = "This is worth a million dollars";

        String name4 = "K.K. Slider";
        int quantity4 = 1;
        Quality quality4 = new Quality(10);
        String catagory4 = "Amiibo";
        String series4 = "Nintendo";
        boolean tradable4 = true;
        String comments4 = "Got dorito finger prints, sorry";




        User Joshua = new User("Joshua", "Canada", "jjwhite@ualberta.ca");
        User Salim = new User("Salim", "Canada", "salim@ualberta.ca");

        Card Charizard = new Card(name, R.drawable.mask_image, quantity, quality, catagory, series, tradable, comments, Joshua);
        Card BEWD = new Card(newname, R.drawable.mask_image, newquantity, newquality, newcatagory, newseries, newtradable, newcomments, Salim);
        Card Gingy = new Card(name3, R.drawable.mask_image, quantity3, quality3, catagory3, series3, tradable3, comments3, Joshua);
        Card KKSlider = new Card(name4, R.drawable.mask_image, quantity4, quality4, catagory4, series4, tradable4, comments4, Joshua);

        Joshua.addInventoryItem(Charizard);
        Salim.addInventoryItem(BEWD);
        Joshua.addInventoryItem(Gingy);
        Salim.addInventoryItem(KKSlider);

        Trade trade1 = new Trade(Joshua, Salim);
        trade1.addBorrowList(Joshua.returnInventoryItem(Charizard));
        trade1.addOwnerList(Salim.returnInventoryItem(BEWD));

        Trade trade2 = new Trade(Joshua, Salim);
        trade2.addBorrowList(Joshua.returnInventoryItem(Gingy));
        trade2.addOwnerList(Salim.returnInventoryItem(KKSlider));

        // Send trade offer button clicked.
        trade1.sendTrade();
        //trade1.setNotification(owner);
        trade2.sendTrade();
        //trade2.setNotification(owner);

        //Owner clicks the accept button
        trade1.setStatus("ACCEPTED");
        assertEquals(trade1.getStatus(),"ACCEPTED");

        //Owner clicks the decline button
        trade2.setStatus("DECLINED");
        assertEquals(trade2.getStatus(), "DECLINED");
    }

// US04.04.01
// Owner can set a counter offer if the owner declines a trade

    public void testUS040401() {
        String name = "Charizard";
        int quantity = 2;
        Quality quality = new Quality(73);
        String catagory = "Pokemon";
        String series = "Basic XY Red";
        boolean tradable = true;
        String comments = "Gently bent edge, 100HP";

        String newname = "Blue Eyes White Dragon";
        int newquantity = 9;
        Quality newquality = new Quality(3);
        String newcatagory = "YuGiOh";
        String newseries = "stupid series";
        boolean newtradable = true;
        String newcomments = "Dime a dozen";

        String name3 = "Gingy";
        int quantity3 = 6;
        Quality quality3 = new Quality(100);
        String catagory3 = "Shrek";
        String series3 = "Super rare";
        boolean tradable3 = true;
        String comments3 = "This is worth a million dollars";

        String name4 = "K.K. Slider";
        int quantity4 = 1;
        Quality quality4 = new Quality(10);
        String catagory4 = "Amiibo";
        String series4 = "Nintendo";
        boolean tradable4 = true;
        String comments4 = "Got dorito finger prints, sorry";




        User Joshua = new User("Joshua", "Canada", "jjwhite@ualberta.ca");
        User Salim = new User("Salim", "Canada", "salim@ualberta.ca");

        Card Charizard = new Card(name, R.drawable.mask_image, quantity, quality, catagory, series, tradable, comments, Joshua);
        Card BEWD = new Card(newname, R.drawable.mask_image, newquantity, newquality, newcatagory, newseries, newtradable, newcomments, Salim);
        Card Gingy = new Card(name3, R.drawable.mask_image, quantity3, quality3, catagory3, series3, tradable3, comments3, Joshua);
        Card KKSlider = new Card(name4, R.drawable.mask_image, quantity4, quality4, catagory4, series4, tradable4, comments4, Joshua);

        Joshua.addInventoryItem(Charizard);
        Salim.addInventoryItem(BEWD);
        Joshua.addInventoryItem(Gingy);
        Salim.addInventoryItem(KKSlider);

        Trade trade1 = new Trade(Joshua, Salim);

        trade1.addBorrowList(Joshua.returnInventoryItem(Charizard));
        trade1.addOwnerList(Salim.returnInventoryItem(BEWD));

        // Send trade offer button clicked.
        trade1.sendTrade();


        //Salim is all like "HECK NO, not even" But I
        //Will trade BEWD for Charizard and Gigny
        Trade trade2 = trade1.counterOffer();
        trade2.addOwnerList(Gingy);
        trade2.sendTrade();

        // Owner declines the trade.
        // Owner makes a new trade offer when the counter offer button is clicked.
        assertTrue(trade1.getStatus().equals("DECLINED"));
        assertEquals(trade1.getOwnerList(), trade2.getBorrowList());
        assertNotSame(trade1.getBorrowList(), trade2.getOwnerList());
        assertEquals(trade2.getOwnerList().size(), 2);
    }

// US04.05.01
// The user or borrower can edit the trade or counter-trade.

    /*

    public void testUS040501 () {
        String name = "Charizard";
        int quantity = 2;
        Quality quality = new Quality(73);
        String catagory = "Pokemon";
        String series = "Basic XY Red";
        boolean tradable = true;
        String comments = "Gently bent edge, 100HP";

        String newname = "Blue Eyes White Dragon";
        int newquantity = 9;
        Quality newquality = new Quality(3);
        String newcatagory = "YuGiOh";
        String newseries = "stupid series";
        boolean newtradable = true;
        String newcomments = "Dime a dozen";

        String name3 = "Gingy";
        int quantity3 = 6;
        Quality quality3 = new Quality(100);
        String catagory3 = "Shrek";
        String series3 = "Super rare";
        boolean tradable3 = true;
        String comments3 = "This is worth a million dollars";

        String name4 = "K.K. Slider";
        int quantity4 = 1;
        Quality quality4 = new Quality(10);
        String catagory4 = "Amiibo";
        String series4 = "Nintendo";
        boolean tradable4 = true;
        String comments4 = "Got dorito finger prints, sorry";




        User Joshua = new User("Joshua", "Canada", "jjwhite@ualberta.ca");
        User Salim = new User("Salim", "Canada", "salim@ualberta.ca");

        Card Charizard = new Card(name, quantity, quality, catagory, series, tradable, comments, Joshua);
        Card BEWD = new Card(newname, newquantity, newquality, newcatagory, newseries, newtradable, newcomments, Salim);
        Card Gingy = new Card(name3, quantity3, quality3, catagory3, series3, tradable3, comments3, Joshua);
        Card KKSlider = new Card(name4, quantity4, quality4, catagory4, series4, tradable4, comments4, Joshua);

        Joshua.addInventoryItem(Charizard);
        Salim.addInventoryItem(BEWD);
        Joshua.addInventoryItem(Gingy);
        Salim.addInventoryItem(KKSlider);

        Trade trade1 = new Trade(Joshua, Salim);

        trade1.addBorrowList(Joshua.returnInventoryItem(Charizard));
        trade1.addOwnerList(Salim.returnInventoryItem(BEWD));

        // Send trade offer button clicked.
        trade1.sendTrade();


        //Salim is all like "HECK NO, not even"
        trade1.counterOffer();

        // Owner declines the trade.
        // Owner makes a new trade offer when the counter offer button is clicked.
        if(trade.status() == "DECLINED") {
            // Owner adds and removes cards with the plus and minus button.
            Trade trade2 = newTrade(owner, borrower);
            trade2.list1.add(owner.getInventory().getCard("Deoxys"));
            trade2.list1.add(owner.getInventory().getCard("Register"));
            trade2.list1.remove(1);
            trade2.list2.add(borrower.getInventory().getCard("Mew");

            assertTrue(trade.list1.contains("Deoxys"));
            assertTrue(trade.list2.contains("Mew"));
            assertFalse(trade.list1.contains("Register"));

            trade2.sendTrade(owner);
            trade2.setNotification(borrower);
        }
    } */

// US04.06.01
// The borrower can delete the trade or counter-trade.

    public void testUS040601() {

        String name = "Charizard";
        int quantity = 2;
        Quality quality = new Quality(73);
        String catagory = "Pokemon";
        String series = "Basic XY Red";
        boolean tradable = true;
        String comments = "Gently bent edge, 100HP";

        String newname = "Blue Eyes White Dragon";
        int newquantity = 9;
        Quality newquality = new Quality(3);
        String newcatagory = "YuGiOh";
        String newseries = "stupid series";
        boolean newtradable = true;
        String newcomments = "Dime a dozen";

        String name3 = "Gingy";
        int quantity3 = 6;
        Quality quality3 = new Quality(100);
        String catagory3 = "Shrek";
        String series3 = "Super rare";
        boolean tradable3 = true;
        String comments3 = "This is worth a million dollars";

        String name4 = "K.K. Slider";
        int quantity4 = 1;
        Quality quality4 = new Quality(10);
        String catagory4 = "Amiibo";
        String series4 = "Nintendo";
        boolean tradable4 = true;
        String comments4 = "Got dorito finger prints, sorry";




        User Joshua = new User("Joshua", "Canada", "jjwhite@ualberta.ca");
        User Salim = new User("Salim", "Canada", "salim@ualberta.ca");

        Card Charizard = new Card(name, R.drawable.mask_image, quantity, quality, catagory, series, tradable, comments, Joshua);
        Card BEWD = new Card(newname, R.drawable.mask_image, newquantity, newquality, newcatagory, newseries, newtradable, newcomments, Salim);
        Card Gingy = new Card(name3, R.drawable.mask_image, quantity3, quality3, catagory3, series3, tradable3, comments3, Joshua);
        Card KKSlider = new Card(name4, R.drawable.mask_image, quantity4, quality4, catagory4, series4, tradable4, comments4, Joshua);

        Joshua.addInventoryItem(Charizard);
        Salim.addInventoryItem(BEWD);
        Joshua.addInventoryItem(Gingy);
        Salim.addInventoryItem(KKSlider);

        Trade trade1 = new Trade(Joshua, Salim);

        trade1.addBorrowList(Joshua.returnInventoryItem(Charizard));
        trade1.addOwnerList(Salim.returnInventoryItem(BEWD));

        // Send trade offer button clicked.
        trade1.sendTrade();

        //Salim says "No way I am wasting time with this and declines the trade"
        trade1.setStatus("DECLINED");

        //The trade gets finalized
        Salim.finalizeTrade(trade1);
        Joshua.finalizeTrade(trade1);

        //Salim decides to remove the trade from his past trades because it is
        // so rediculous he is insulted by even seeing the trade was offered
        Salim.deletePastTrade(trade1);


        //Joshua can view the old trade but Salim does not
        assertFalse(Salim.getTrades().containsPastTrade(trade1));
        assertTrue(Joshua.getTrades().containsPastTrade(trade1));

    }



// US04.07.01
// If the owner of a trade accepts, both parties are email relevant trace information,
// as well as the owner will supply comments for how to continue with the trade.

    /*
    public void testUS040701 () {
        User borrower = new User(currentUser.Name, "Canada");
        User owner = new User("Mr. Bean", "Canada");

        // Borrower adds cards with the "plus" button.
        Trade trade = new Trade(borrower, owner);
        trade.list1.add(borrower.getInventory().getCard("Darkrai"));
        trade.list2.add(owner.getInventory().getCard("Mewtwo"));

        // Send trade offer button clicked.
        trade.sendTrade(owner);
        trade.setNotification(owner);

        // Owner clicks the accept button. An input box
        // will be displayed for extra comments.
        String extraComments = inputTextView.text;
        Email emailBorrower = new Email(borrower, extraComments);
        Email emailOwner = new Email(owner, "");

        emailBorrower.send();
        emailOwner.send();

        assertNotNull(extraComments);
        assertEquals(emailOwner.status() ,"SENT");
        assertEquals(emailBorrower.status(), "SENT");
    }

    //Trade With Friends:

//Use Case Name: Users List Of Trades
//Participating Actors: User, Trade
//Goal: The user can view a list of their trades and check to see if they
    //were a borrower or an owner in each trade.
    //Trigger: User clicks the trades menu option
//Precondition: User is in the main menu
//Postcondition: User has a list of trades
    //and the friend has accepted the trade.
//Related User Stories: US04.08.01 - US04.09.01

//Basic Flow
// 1) User clicks the trade menu option
// 2) A scrollable list of trades opens.
// 3) User selects a trade that they were in.
// 4) User checks to see if they were a borrower or an owner in that trade
// 5) User exits the current trade

//Exceptions
// 5) User presses the back button
// 5.1) The list of trades will be closed and the use case will be exited.

// US04.08.01
// Owners and Borrowers can view all past trades with themselves.

    public void testUS040801 () {
        User borrower = new User(currentUser.Name, "Canada");
        User owner = new User("Mr. Bean", "Canada");

        // Borrower adds cards with the "plus" button.
        Trade trade1 = new Trade(borrower, owner);
        Trade trade2 = new Trade(borrower, owner);

        // Send trade offer button clicked.
        trade1.sendTrade(owner);
        trade2.sendTrade(owner);

        // Owner accepts trade1 and leaves trade2 alone
        // User exits and checks there trades
        List<Trade> ownerTrades = owner.getTrades();
        List<Trade> borrowerTrades = owner.getTrades();

        assertTrue(ownerTrades.contains(trade1));
        assertTrue(ownerTrades.contains(trade2));
        assertTrue(borrowerTrades.contains(trade1));
        assertTrue(borrowerTrades.contains(trade2));
    }

// US04.09.01
// Owners and Borrowers can view all past trades with themselves as
// either a borrower or an owner.

    public void testUS040901 () {
        User user1 = new User(currentUser.Name, "Canada");
        User user2 = new User("Mr. Bean", "Canada");

        // Borrower adds cards with the "plus" button.
        Trade trade1 = new Trade(user1, user2); //user 1 sends a trade to user 2
        Trade trade2 = new Trade(user2, user1); //user 2 sends a trade to user 1

        // Send trade offer button clicked.
        trade1.sendTrade(user1); //is the user parameter necessary? we already specified in "new Trade(...,...)"
        trade2.sendTrade(user2);

        // Owner accepts trade1 and leaves trade2 alone
        // User exits and checks there trades
        List<Trade> user1Trades = user1.getTrades();
        List<Trade> user2Trades = user2.getTrades();

        assertTrue(user1Trades.get(trade1).isOwner()); //make sure user 1 owns trade 1
        assertTrue(user2Trades.get(trade1).isBorrower()); //make sure user 2 is borrowing trade 1
        assertTrue(user1Trades.get(trade2).isBorrower()); //make sure user 1 is borrowing trade 1
        assertTrue(user2Trades.get(trade2).isOwner()); //make sure user 2 owns trade 1

        assertTrue(!user1Trades.get(trade2).isOwner()); //make sure user 1 doesnt own trade 2
        assertTrue(!user2Trades.get(trade2).isBorrower()); //make sure user 2 doesnt borrow trade 2

        //Viewing trades and separating them will be done in UI.
    } */


}
