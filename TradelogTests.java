package com.sherpasteven.sscte;


import android.app.Application;
import android.test.ApplicationTestCase;

import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Quality;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.TradeLog;
import com.sherpasteven.sscte.Models.User;

public class TradelogTests extends ApplicationTestCase<Application> {
    public TradelogTests() {
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

    public void testFinalizedTrade() {
        User joshua = setupJoshua();
        User salim = setupSalim();

        Card charizard = setupCards(1, joshua);
        Card bEWD = setupCards(2 , salim);
        Card gingy = setupCards(3 , joshua);
        Card kkslider = setupCards(4 , salim);

        TradeLog tradelog = new TradeLog();

        Trade trade1 = new Trade(joshua, salim);
        trade1.addBorrowList(joshua.returnInventoryItem(charizard));
        trade1.addOwnerList(salim.returnInventoryItem(bEWD));

        Trade trade2 = new Trade(joshua, salim);
        trade2.addBorrowList(joshua.returnInventoryItem(gingy));
        trade2.addOwnerList(salim.returnInventoryItem(kkslider));

        //Owner clicks the accept button
        trade1.setStatus("ACCEPTED");

        tradelog.addTrade(trade1);
        tradelog.addTrade(trade2);

        tradelog.tradeFinalized(trade1);
        tradelog.tradeFinalized(trade2);

        assertTrue(tradelog.getPastTrades().contains(trade1));
        assertTrue(tradelog.getPendingTrades().contains(trade2));

        tradelog.removeFinalizedTrade(trade1);
        tradelog.removeFinalizedTrade(trade2);

        assertFalse(tradelog.getPastTrades().contains(trade1));
        assertTrue(tradelog.getPendingTrades().contains(trade2));
    }
}
