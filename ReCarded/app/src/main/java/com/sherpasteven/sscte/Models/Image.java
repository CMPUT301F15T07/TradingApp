package com.sherpasteven.sscte.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;

import com.sherpasteven.sscte.R;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * Created by joshua on 05/11/15.
 */
public class Image extends Model {


    private String imageserial;


    public Image(Bitmap image){

        setImageserial(BitMapToString(image));
        image.recycle();
        image = null;

    }

    public Image(int imageID, Context context){

        Bitmap image = BitmapFactory.decodeResource(context.getResources(), imageID);
        setImageserial(BitMapToString(image));
        image.recycle();
        image = null;

    }


    private Bitmap resizeBitmap(Bitmap image) {
        Double width = (double) image.getWidth();
        Double height = (double) image.getHeight();
        Double max = 500.0;

        if (width > height) {
            height = max * (height / width);
            width = max;
        } else {
            width = max * (width / height);
            height = max;
        }
        Bitmap bmp = Bitmap.createScaledBitmap(image, width.intValue(), height.intValue(), Boolean.FALSE);
        image.recycle();
        image = null;

        return bmp;


    }

    public String getImageserial() {
        return imageserial;
    }

    public void setImageserial(String imageserial) {
        this.imageserial = imageserial;
    }

    // Taken from http://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versahttp://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
    // Credit goes to: Shyam Deore
    public Bitmap constructImage() {
        try {
            byte[] encodeByte = Base64.decode(getImageserial(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            encodeByte = null;
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    // Taken from http://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versahttp://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
    // Credit goes to: Shyam Deore
    public String BitMapToString(Bitmap bitmap){
        bitmap = resizeBitmap(bitmap);

        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte [] b = baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);

        bitmap.recycle();
        bitmap = null;
        b = null;

        return temp;
    }


}
