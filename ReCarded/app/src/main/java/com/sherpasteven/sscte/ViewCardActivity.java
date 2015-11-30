package com.sherpasteven.sscte;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sherpasteven.sscte.Controllers.ViewCardController;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Friend;
import com.sherpasteven.sscte.Models.IDeSerializer;
import com.sherpasteven.sscte.Models.Image;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Models.SearchSingleton;
import com.sherpasteven.sscte.Models.Trade;
import com.sherpasteven.sscte.Models.User;
import com.sherpasteven.sscte.Views.IView;
import com.sherpasteven.sscte.Views.RecyclerView.MediaAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewCardActivity extends AppCompatActivity implements IView<Model> {

   private Card card;
    View v;
    private ViewCardController c;
    private Integer position;
    private Profile profile;
    private List<Trade> trades;
    int menuselector;
    private Friend friend;

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    protected LayoutManagerType mCurrentLayoutManagerType;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }


    protected RecyclerView mRecyclerView;
    protected MediaAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Bitmap> cardimages;


    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_card);

        menuselector = 0;

        Intent intent = getIntent();
        setProfile(CurrentProfile.getCurrentProfile().getProfile(this));
        setPosition(intent.getIntExtra("com.sherpasteven.sscte.viewcard", 0));

        if (intent.hasExtra("com.sherpasteven.sscte.trades")) {
            menuselector = 1;
            invalidateOptionsMenu();
            String tradescon = intent.getStringExtra("com.sherpasteven.sscte.trades");
            int tradelistpos = intent.getIntExtra("com.sherpasteven.sscte.tradepos", 0);
            trades = new ArrayList<>();
            trades.addAll(getProfile().getUser().getTrades().getPendingTrades());
            trades.addAll(getProfile().getUser().getTrades().getPastTrades());
            if (tradescon.equals("owner")) {
                setCard(trades.get(tradelistpos).getOwnerList().get(position));
            } else {
                setCard(trades.get(tradelistpos).getBorrowList().get(position));
            }
        } else if(intent.hasExtra("com.sherpasteven.sscte.friendscard")){
            friend = getProfile().getUser().getFriends().get(intent.getIntExtra("com.sherpasteven.sscte.friendscard",0));
            setCard(friend.getInventoryItem(getPosition()));
            setTitle("View Card - " + card.getName());
            menuselector = 1;
            invalidateOptionsMenu();
        } else if (intent.hasExtra("com.sherpasteven.sscte.searched") && SearchSingleton.getSearchSingleton().getSearchedInventory() != null){
            setCard(SearchSingleton.getSearchSingleton().getSearchedInventory().get(getPosition()));
            setTitle("View Card - " + card.getName());
            if(!card.getOwner().equals(getProfile().getUser().getOwnerInfo())) {
                menuselector = 1;
                invalidateOptionsMenu();
            }
        } else{

            setCard(getProfile().getUser().getInventoryItem(getPosition()));
        }

        mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerView);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(this);

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }

        setCardImages( createCardImageBitmaps());
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        mAdapter = new MediaAdapter(getCardImages(), this);
        mRecyclerView.setAdapter(mAdapter);

        getCard().addView(this);

        c = new ViewCardController(this, getCard());
        retrieveCardInfo(getCard());
        v = this.findViewById(android.R.id.content);
    }

    /**
     * Generates hamburger menu options.
     * @param menu Menu item to be created.
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (menuselector == 0) {
            getMenuInflater().inflate(R.menu.menu_view_card, menu);
        }
        if(menuselector == 1){
            getMenuInflater().inflate(R.menu.menu_view_friend_card, menu);
        }
        return true;
    }

    /**
     * OnSelect options for option selected from hamburger menu.
     * @param item Item selected by user.
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        c.menuOptions(id);
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
    public void retrieveCardInfo(Card card){

        //set header
        String tradable;
        if (card.isTradable()){
            tradable = "Tradable";
        } else {
            tradable = "Not Tradable";
        }
        String headertext = card.getCategory()+ " - " + card.getSeries()+ " - " + tradable;
        TextView header = (TextView) findViewById(R.id.txtStatus);
        header.setText(headertext);

        //set name
        TextView cardname = (TextView) findViewById(R.id.txtName);
        cardname.setText(card.getName());

        //set quality
        TextView cardquality = (TextView) findViewById(R.id.qualityInfo);
        cardquality.setText(String.valueOf(card.getQuality().getQuality()));

        //set quantity
        TextView cardquantity = (TextView) findViewById(R.id.quantityInfo);
        cardquantity.setText(String.valueOf(card.getQuantity()));

        //set comments
        TextView cardcomments = (TextView) findViewById(R.id.commentsInfo);
        cardcomments.setText(card.getComments());

        if(!card.getImages().isEmpty()){
            ImageView viewcard = getImageCard();
            if (card.getImagebyIndex(0) != null) {
                viewcard.setImageBitmap(card.constructImage(0));
            }
        }
        mAdapter.notifyDataSetChanged();
    }


    /**
     * Serialises the profile (getter) for application registry.
     * @return deserialized profile information.
     */
    private Profile getLocalProfile() {
        IDeSerializer<Profile> deSerializer = new LocalProfileSerializer();
        return deSerializer.Deserialize(null, this);
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    public ImageView getImageCard(){
        return (ImageView) findViewById(R.id.greyRect);
    }


    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public void Update(Model model) { //model is user, not card...
        retrieveCardInfo(getCard());
    }

    // development for AddCardActivity update card
    public void Update(User user) {
        retrieveCardInfo(getCard());
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public ArrayList<Bitmap> getCardImages(){
        return this.cardimages;
    }

    public void setCardImages(ArrayList<Bitmap> bmps){
        this.cardimages = bmps;
    }

    public ArrayList<Bitmap> createCardImageBitmaps(){
        ArrayList<Bitmap> bmps = new ArrayList<>();
        for(Image image: getCard().getImages()){
            bmps.add(image.constructImage());
        }

        return bmps;
    }

    @Override
    public void onBackPressed() {
        card.deleteView(this);
        super.onBackPressed();

    }
}
