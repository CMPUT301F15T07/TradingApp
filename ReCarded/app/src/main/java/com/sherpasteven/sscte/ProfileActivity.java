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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.sherpasteven.sscte.Models.Profile;
import com.sherpasteven.sscte.Views.IView;

import java.io.File;

public class ProfileActivity extends AppCompatActivity implements IView<Profile>{

    private static int RESULT_LOAD_IMAGE = 1;

    /**
     * @see android.app.Activity#onStart()
     * @param savedInstanceState
     */
    // code graciously provided at http://viralpatel.net/blogs/pick-image-from-galary-android-app/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

    }

    /**
     * Response is generated once load image intent is completed.
     * Finds and decodes image based on path, connects image to activity.
     * @param requestCode
     * @param resultCode
     * @param data
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
     * Generates hamburger menu to select options.
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    /**
     * OnSelect options for option selected from hamburger menu.
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Updates the activity based on raised condition.
     * @param profile
     */
    @Override
    public void Update(Profile profile) {
        
    }


}
