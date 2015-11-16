package com.sherpasteven.sscte;

import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.sherpasteven.sscte.Models.IDeSerializer;
import com.sherpasteven.sscte.Models.ISerializer;
import com.sherpasteven.sscte.Models.LocalProfileSerializer;
import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Views.IView;

import java.io.File;

public class ProfileActivity extends AppCompatActivity implements IView<Profile>{

    private Profile profile;
    private static int RESULT_LOAD_IMAGE = 1;
    private IDeSerializer<Profile> profileIDeSerializer;
    private ISerializer<Profile> profileISerializer;


    public Profile getProfile() {
        profile = profileIDeSerializer.Deserialize(profile, this);
        return profile;
    }

    /** (not Javadoc)
     * @see android.app.Activity#onStart()
     * FIXME: Implement a working serialiser - the button submit path is broken.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final Profile localProfile = getLocalProfile();

        ImageButton buttonLoadImage = (ImageButton) findViewById(R.id.btnProfileImage);
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

        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText cityText = (EditText) findViewById(R.id.cityText);
        final EditText emailText = (EditText) findViewById(R.id.emailText);

        nameText.setText(localProfile.getUser().getName());
        cityText.setText(localProfile.getUser().getLocation());
        emailText.setText(localProfile.getUser().getEmail());

        Button submitButton = (Button) findViewById(R.id.btnEnter);
        submitButton.setOnClickListener( new View.OnClickListener() {

            public void onClick(View v) {
                localProfile.getUser().setName(nameText.getText().toString());
                localProfile.getUser().setLocation(cityText.getText().toString());
                localProfile.getUser().setEmail(emailText.getText().toString());
                setLocalProfile(localProfile);
                Intent myIntent = new Intent(ProfileActivity.this, InventoryActivity.class);
                startActivity(myIntent);
            }
        });

    }

    private void setLocalProfile(Profile profile) {
        ISerializer<Profile> serializer = new LocalProfileSerializer();
        serializer.Serialize(profile, this);
    }

    /**
     * Serialises the profile (getter) for application registry.
     * @return deserialized profile information.
     */
    private Profile getLocalProfile() {
        IDeSerializer<Profile> deSerializer = new LocalProfileSerializer();
        return deSerializer.Deserialize(null, this);
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
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.profile_image);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
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
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
     * @param profile Profile to be updated on load.
     */
    @Override
    public void Update(Profile profile) {
        
    }


}
