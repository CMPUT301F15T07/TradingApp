package com.sherpasteven.sscte;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Email;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.User;

/**
 * Created by elias on 17/10/15.
 */
public class TradesTest extends ApplicationTestCase<Application> {
    public TradesTest() {
        super(Application.class);
    }

    public Card setupCards(int i, User user){
        switch(i){
            case 1:
               return new Card("charizard", 2, new Quality(73), "Pokemon", "Basic XY Red", 
                true, "Gently bent edge, 100HP", user);
            case 2:
                return new Card("Blue Eyes White Dragon", 9, new Quality(3), "YuGiOh", "stupid series", 
                true, "Dime a dozen", user);
            case 3:
                return new Card("gingy", 6, new Quality(100), "Shrek", "Super rare", 
                true, "This is worth a million dollars", user);
            case 4:
                return new Card("k.k slider", 1, new Quality(10), "Amiibo", "Nintendo", 
                true, "Got dorito finger prints, sorry", user);
            default:
                return null;
        }
    }

    public User setupSalim(){
        User salim = new User("salim", "Canada", "salim@ualberta.ca");
        salim.addInventoryItem(setupCards(2, salim));
        salim.addInventoryItem(setupCards(4, salim));
        return salim;

    }

    public User setupJoshua(){
        User joshua = new User("joshua", "Canada", "jjwhite@ualberta.ca");;
        joshua.addInventoryItem(setupCards(1, joshua));
        joshua.addInventoryItem(setupCards(3, joshua));
        return joshua;

    }

    public void testCreateTrade() {
        User joshua = setupJoshua();
        User salim = setupSalim();

        Card charizard = setupCards(1, joshua);
        Card bEWD = setupCards(2, salim);

        // joshua sees the Blue Eyes white Dragon that he wants to borrow from salim
        // He clicks the card and initializses the trade and that card is listed under salim (Owner) in the
        // Trade list becaus it is salims Card, and Josh's charizard shows up in the Borrower list because
        //He is the borrower
        Trade trade = new Trade(joshua, salim);
        trade.addBorrowList(joshua.returnInventoryItem(charizard));
        trade.addOwnerList(salim.returnInventoryItem(bEWD));

        trade.sendTrade();
        //Cant test sending network functionality at this time

        //trade.setNotification();


        assertTrue(!joshua.getTrades().getPendingTrades().isEmpty());

        //the trade view should then show these cards listed to the current owners for reivew
    }

    public void testAcceptDeline() {
        User joshua = setupJoshua();
        User salim = setupSalim();

        Card charizard = setupCards(1 , joshua);
        Card bEWD = setupCards(2 , salim);
        Card gingy = setupCards(3 , joshua);
        Card kkslider = setupCards(4 , salim);

        Trade trade1 = new Trade(joshua, salim);
        trade1.addBorrowList(joshua.returnInventoryItem(charizard));
        trade1.addOwnerList(salim.returnInventoryItem(bEWD));

        Trade trade2 = new Trade(joshua, salim);
        trade2.addBorrowList(joshua.returnInventoryItem(gingy));
        trade2.addOwnerList(salim.returnInventoryItem(kkslider));

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

    public void testCounterOffer() {
        User joshua = setupJoshua();
        User salim = setupSalim();

        Card charizard = setupCards(1 , joshua);
        Card bEWD = setupCards(2 , salim);
        Card gingy = setupCards(3 , joshua);
        Card kkslider = setupCards(4 , salim);

        Trade trade1 = new Trade(joshua, salim);

        trade1.addBorrowList(joshua.returnInventoryItem(charizard));
        trade1.addOwnerList(salim.returnInventoryItem(bEWD));

        // Send trade offer button clicked.
        trade1.sendTrade();

        //salim is all like "HECK NO, not even" But I
        //Will trade bEWD for charizard and Gigny
        Trade trade2 = trade1.counterOffer();
        trade2.addOwnerList(gingy);
        trade2.sendTrade();

        // Owner declines the trade.
        // Owner makes a new trade offer when the counter offer button is clicked.
        assertTrue(trade1.getStatus().equals("DECLINED"));
        assertEquals(trade1.getOwnerList(), trade2.getBorrowList());
        assertNotSame(trade1.getBorrowList(), trade2.getOwnerList());
        assertEquals(trade2.getOwnerList().size(), 2);
    }

    public void testEditTrade() {
        User joshua = setupJoshua();
        User salim = setupSalim();

        Card charizard = setupCards(1 , joshua);
        Card bEWD = setupCards(2 , salim);
        Card gingy = setupCards(3 , joshua);
        Card kkslider = setupCards(4 , salim);

        Trade trade1 = new Trade(joshua, salim);

        trade1.addBorrowList(joshua.returnInventoryItem(charizard));
        trade1.addOwnerList(salim.returnInventoryItem(bEWD));

        // Send trade offer button clicked.
        trade1.sendTrade();

        //salim is all like "HECK NO, not even"
        trade1.counterOffer();

        // Owner declines the trade.
        // Owner makes a new trade offer when the counter offer button is clicked.
        if(trade1.getStatus() == "DECLINED") {
            // Owner adds and removes cards with the plus and minus button.
            Trade trade2 = new Trade(salim, joshua);
            trade1.addOwnerList(joshua.returnInventoryItem(charizard));
            trade1.addBorrowList(salim.returnInventoryItem(bEWD));
            trade2.removeBorrowList(bEWD);
            trade2.addBorrowList(salim.returnInventoryItem(kkslider));

            assertTrue(trade2.getBorrowList().contains(kkslider));
            assertTrue(trade2.getOwnerList().contains(charizard));
            assertFalse(trade2.getBorrowList().contains(bEWD));

            trade2.sendTrade();
            //trade2.setNotification(borrower);
        }
    }

    public void testDeleteTrade() {
        User joshua = setupJoshua();
        User salim = setupSalim();

        Card charizard = setupCards(1 , joshua);
        Card bEWD = setupCards(2 , salim);
        Card gingy = setupCards(3 , joshua);
        Card kkslider = setupCards(4 , salim);

        Trade trade1 = new Trade(joshua, salim);

        trade1.addBorrowList(joshua.returnInventoryItem(charizard));
        trade1.addOwnerList(salim.returnInventoryItem(bEWD));

        // Send trade offer button clicked.
        trade1.sendTrade();

        //salim says "No way I am wasting time with this and declines the trade"
        trade1.setStatus("DECLINED");

        //The trade gets finalized
        salim.finalizeTrade(trade1);
        joshua.finalizeTrade(trade1);

        //salim decides to remove the trade from his past trades because it is
        // so rediculous he is insulted by even seeing the trade was offered
        salim.deletePastTrade(trade1);


        //joshua can view the old trade but salim does not
        assertFalse(salim.getTrades().containsPastTrade(trade1));
        assertTrue(joshua.getTrades().containsPastTrade(trade1));

    }

    public void testConfirmTrade() {
        User joshua = setupJoshua();
        User salim = setupSalim();

        Card charizard = setupCards(1 , joshua);
        Card bEWD = setupCards(2 , salim);
        Card gingy = setupCards(3 , joshua);
        Card kkslider = setupCards(4 , salim);

        Trade trade1 = new Trade(joshua, salim);

        // Owner clicks the accept button. An input box
        // will be displayed for extra comments.
        String extraComments = "Meet me in the bask alley behind McDonalds and I will give you the stuff."; //inputTextView.text;
        Email emailBorrower = new Email(joshua, extraComments);
        Email emailOwner = new Email(salim, "");

        emailBorrower.sendEmail();
        emailOwner.sendEmail();

        assertNotNull(extraComments);
        assertEquals(emailOwner.getStatus() ,"SENT");
        assertEquals(emailBorrower.getStatus(), "SENT");
    }


}
