package com.sherpasteven.sscte;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.sherpasteven.sscte.Controllers.AddCardController;
import com.sherpasteven.sscte.Controllers.EditCardController;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.CurrentProfile;
import com.sherpasteven.sscte.Models.Inventory;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Views.IView;

public class EditCardActivity extends AppCompatActivity implements IView<Card> {

    private static int RESULT_LOAD_IMAGE = 1;
    private EditCardController editcardcontroller;
    private Profile profile;
    private Card card;
    Integer position;
    View v;

    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);

        setProfile(CurrentProfile.getCurrentProfile().getProfile(this));
        editcardcontroller = new EditCardController(this, getProfile());

        ImageButton buttonLoadImage = (ImageButton) findViewById(R.id.btnCardImage);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            /**
             * OnClick to enable camera switch.
             * @param arg0
             */
            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

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
        card.addView(this);

        EditText nameText = (EditText) findViewById(R.id.nameText);
        EditText seriesText = (EditText) findViewById(R.id.seriesText);
        EditText qualityText = (EditText) findViewById(R.id.qualityText);
        EditText quantityText = (EditText) findViewById(R.id.quantityText);
        EditText commentsText = (EditText) findViewById(R.id.commentsText);

        ImageView cardimage = getImageViewCard();
        cardimage.setTag("Default");
        if(getIntent().hasExtra("com.sherpasteven.sscte.bitmap")) {
            Bitmap b = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("com.sherpasteven.sscte.bitmap"),0,getIntent().getByteArrayExtra("com.sherpasteven.sscte.bitmap").length);
            cardimage.setImageBitmap(b);
        }

        nameText.setText(card.getName());
        int spinnerPosition = adapter.getPosition(card.getCatagory());
        spinner.setSelection(spinnerPosition);
        seriesText.setText(card.getSeries());
        qualityText.setText(Integer.toString(card.getQuality().getQuality()));
        quantityText.setText(Integer.toString(card.getQuantity()));
        commentsText.setText(card.getComments());
        getCheckBox().setChecked(card.isTradable());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public ImageView getImageCard(){
        return (ImageView) findViewById(R.id.greyRectMask);
    }


    public ImageView getImageViewCard(){return (ImageView) findViewById(R.id.imgCard);}

    public EditText getMediaText(){
        return (EditText) findViewById(R.id.mediaText);
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

    /**
     * Response is generated once load image intent is completed.
     * Finds and decodes image based on path, connects image to activity.
     * @param requestCode Identifies intent of the process.
     * @param resultCode Result of the previous image intent.
     * @param data Resultant data set from intent.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = getImageViewCard();
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            imageView.setTag("Changed");
        }
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
     * @param card Card to be shown as edited.
     */
    @Override
    public void Update(Card card) {

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
