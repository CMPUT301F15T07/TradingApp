package com.sherpasteven.sscte.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Image processes Bitmaps or Drawable Paths.  It trims them down to a set width and height and then
 * compresses them to JPEG format.  Fially is sets the bitmap to a string and stores that as an
 * object attribute "imageserial"
 */
public class Image extends Model {




    private String imageserial;

    /**
     *
     * @param bitmap 
     */
    public Image(Bitmap bitmap){

        setImageserial(BitMapToString(bitmap));
        bitmap.recycle();
        bitmap = null;

    }

    /**
     *
     * @param imageID
     * @param context
     */
    public Image(int imageID, Context context){

        Bitmap image = BitmapFactory.decodeResource(context.getResources(), imageID);
        setImageserial(BitMapToString(image));
        image.recycle();
        image = null;

    }

    /**
     *
     * @param bitmap
     * @return bmpresized
     */
    private Bitmap resizeBitmap(Bitmap bitmap) {
        Double width = (double) bitmap.getWidth();
        Double height = (double) bitmap.getHeight();
        Double max = 500.0;

        if (width > height) {
            height = max * (height / width);
            width = max;
        } else {
            width = max * (width / height);
            height = max;
        }
        Bitmap bmpresized = Bitmap.createScaledBitmap(bitmap, width.intValue(), height.intValue(), Boolean.FALSE);
        bitmap.recycle();
        bitmap = null;

        return bmpresized;


    }

    public String getImageserial() {
        return imageserial;
    }

    public void setImageserial(String imageserial) {
        this.imageserial = imageserial;
    }




    /**
     *  Taken from http://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versahttp://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
     * Credit goes to: Shyam Deore
     * Creates a Bitmap from the imageserial associated with this image object
     * @return bitmap
     */
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

    /**
     * Taken from http://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versahttp://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
     * Credit goes to: Shyam Deore
     * Used to produce a String from a given when a new Image object is created. This string is what the imageserial attribute is set to
     * @param bitmap
     * @return string
     */
    private String BitMapToString(Bitmap bitmap){
        bitmap = resizeBitmap(bitmap);

        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte [] b = baos.toByteArray();
        String string=Base64.encodeToString(b, Base64.DEFAULT);

        bitmap.recycle();
        bitmap = null;
        b = null;

        return string;
    }


}
