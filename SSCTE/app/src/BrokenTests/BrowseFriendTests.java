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
public class BrowseFriendTest extends ApplicationTestCase<Application> {
public BrowseFriendTest(Class<Application> applicationClass) {
        super(applicationClass);
        }

        //Helper Functions:

        User GetMockUser(){
                User user = new User("Snape", "Hogwartz");
                String name = "Black Lotus";
                Integer quantity = 2;
                Quality quality = new Quality(73);
                String category = "Magic The Gathering";
                String series = "series";
                boolean tradable = true;
                String comments = "Gently bent edge, 100HP";
                Card blackLotus = new Card(name, quantity, quality, category, series, tradable, comments);

                name = "Time Walk";
                Card timeWalk = new Card(name, quantity, quality, category, series, tradable, comments);

                Inventory inventory = new Inventory();
                inventory.addCard(blackLotus);
                inventory.addCard(timeWalk);
                user.addInventory(inventory);
                return user;
        }

        User GetMockFriend(){
                User user = GetMockUser();
                user.changeUsername("Harry Potter");
                return user;
        }

//The User opens a friends' profile and then opens thier inventory
//The User can then browse the friends' cards
        void testBrowseFriendsCards(){
                User myuser = GetMockUser();
                String myTestFriendName = "Harry Potter";
                User myTestFriend = GetMockFriend();
                myuser.friends.add(myTestFriend);
                User friend = myuser.friends.getFriend(myTestFriendName);
                Inventory friendInventory = friend.getInventory();
        }

//The User has thier friends profile
//The User searches thier friends profile by category
        void testSearchFriendsCardsCatagory(){
                User friend = GetMockFriend();
                Inventory friendInventory = friend.getInventory();
                String searchCategory = "Magic The Gathering";
                List<Card> results = friendInventory.searchByCategory(searchCategory);
                //serach results shownn to user
        }


//The User has thier friends profile
//The User searches thier friends profile by text
        void testSearchFriendsCardsText(){
                User friend = GetMockFriend();
                Inventory friendInventory = friend.getInventory();
                String searchQuery = "Black Lotus";
                List<Card> results = friendInventory.searchByText(searchQuery);
                //serach results shownn to user
        }


//The Owner Has Publicly Available Items
//The Owner's Friends Can Browse Those Items
        void testFriendsCanBrowse(){
                User owner = GetMockUser();
                Inventory inventory = owner.getInventory();
                Card publicCard = inventory.searchByText("Black Lotus");
                Card privateCard = inventory.serachByText("Time Walk");
                publicCard.setPublic();
                privateCard.setPrivate();
                User friend = GetMockFriend();
                friend.friends.add(owner);
                User anotheOwner = friend.friends.getFriend("Snape");
                Inventory ownerInventory = anotherOwner.getInventory();
                List<Card> ownerCards = ownerInventory.searchByText("*");
                assertEqual(ownerCards.get(0), publicCard);
                assertEqual(ownerCards.get(1), null); //we should only get the public card
        }

}
