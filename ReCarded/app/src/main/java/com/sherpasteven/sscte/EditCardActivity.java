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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.sherpasteven.sscte.Controllers.EditCardController;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Image;
import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Views.IView;
import com.sherpasteven.sscte.Views.RecyclerView.MediaAdapter;

import java.util.ArrayList;

public class EditCardActivity extends AppCompatActivity implements IView<Model> {

    private static int RESULT_LOAD_IMAGE = 1;
    private EditCardController editcardcontroller;
    private Profile profile;
    private Card card;
    Integer position;
    View v;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RecyclerView mRecyclerView;
    protected RecyclerView.LayoutManager mLayoutManager;

    Boolean filledMainImage;
    protected MediaAdapter mAdapter;
    private ArrayList<Bitmap> cardimages;


    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);

        cardimages = new ArrayList<Bitmap>();

        setProfile(CurrentProfile.getCurrentProfile().getProfile(this));
        editcardcontroller = new EditCardController(this, getProfile());
        position = getIntent().getExtras().getInt("pointer");

        Spinner spinner = (Spinner) findViewById(R.id.categoryText);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // setup the card details
        /**
         * FIXME: Import this information to controller
         */
        card = getProfile().getUser().getInventoryItem(position);

        EditText nameText = (EditText) findViewById(R.id.nameText);
        EditText seriesText = (EditText) findViewById(R.id.seriesText);
        EditText qualityText = (EditText) findViewById(R.id.qualityText);
        EditText quantityText = (EditText) findViewById(R.id.quantityText);
        EditText commentsText = (EditText) findViewById(R.id.commentsText);

        ImageView imageView = getImageViewCard();
        imageView.setTag("Default");
        if (card.getImages().size() != 0) {
            imageView.setImageBitmap(card.constructImage(0));
        } else {
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.img_no_img));
        }


        nameText.setText(card.getName());
        for (Image tmpImage : card.getImages()) {
            this.cardimages.add(tmpImage.constructImage());
        }
        int spinnerPosition = adapter.getPosition(card.getCategory());
        spinner.setSelection(spinnerPosition);
        seriesText.setText(card.getSeries());
        qualityText.setText(Integer.toString(card.getQuality().getQuality()));
        quantityText.setText(Integer.toString(card.getQuantity()));
        commentsText.setText(card.getComments());
        getCheckBox().setChecked(card.isTradable());


        if (card.getImages().size() != 0) {
            filledMainImage = Boolean.TRUE;
        } else {
            filledMainImage = Boolean.FALSE;
        }


        // BEGIN_INCLUDE(initializeRecyclerView)
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

        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
        mAdapter = new MediaAdapter(getCardImages(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public ImageView getImageCard(){
        return (ImageView) findViewById(R.id.imgCard);
    }


    public ImageView getImageViewCard(){return (ImageView) findViewById(R.id.imgCard);}

    public RecyclerView getMediaText(){
        return (RecyclerView) findViewById(R.id.recyclerView);
    }

    public EditText getNameText(){
        return (EditText) findViewById(R.id.nameText);
    }

    public Spinner getCatagoryText(){
        return (Spinner) findViewById(R.id.categoryText);
    }

    public EditText getSeriesText(){
        return (EditText) findViewById(R.id.seriesText);
    }

    public EditText getQualityText(){
        return (EditText) findViewById(R.id.qualityText);
    }

    public EditText getQuantityText(){
        return (EditText) findViewById(R.id.quantityText);
    }

    public EditText getCommentsText(){
        return (EditText) findViewById(R.id.commentsText);
    }

    public Button getEnterButton(){
        return (Button) findViewById(R.id.btnEnter);
    }

    public CheckBox getCheckBox(){
        return (CheckBox) findViewById(R.id.checkBox);
    }

    public int getPosition() { return position; }

    public void navigateToInventory(){
        finish();
    }

    public void loadImage(){
        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    public ArrayList<Bitmap> getCardImages(){
        return this.cardimages;
    }


    /**
     * Generates hamburger menu options.
     * @param menu Menu item to be created.
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_card, menu);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent1 = new Intent(this, SettingsActivity.class);
            this.startActivity(intent1);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Updates the activity based on raised condition.
     * @param model Card to be shown as edited.
     */
    @Override
    public void Update(Model model) {

    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
