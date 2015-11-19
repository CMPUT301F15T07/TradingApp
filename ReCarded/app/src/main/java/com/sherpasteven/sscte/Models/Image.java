package com.sherpasteven.sscte.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import com.sherpasteven.sscte.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.ByteBuffer;

/**
 * Created by joshua on 05/11/15.
 */
public class Image extends Model {


    private byte[] imageserial;



    private String configname;
    private int height;
    private int width;
    private int rowbytes;


    public Image(Bitmap image){

        image = formatBitmap(image);
        setHeight(image.getHeight());
        setWidth(image.getWidth());
        setConfigname(image.getConfig().name());
        setImageserial(convertBitmap(image));
        image.recycle();
        image = null;

    }

    public Image(int imageID, Context context){

        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), imageID);
        bmp = formatBitmap(bmp);
        setHeight(bmp.getHeight());
        setWidth(bmp.getWidth());
        setConfigname(bmp.getConfig().name());
        setImageserial(convertBitmap(bmp));
        bmp.recycle();
        bmp = null;

    }

    public String getConfigname() {
        return configname;
    }

    public void setConfigname(String configname) {
        this.configname = configname;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }



    private Bitmap formatBitmap(Bitmap image){

        return scaleQuality(resizeBitmap(image));
    }




    private Bitmap scaleQuality(Bitmap image){
        int scale = 50;
        //Bitmap newimage;
        //Bitmap retainimage = image;
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, scale, outstream);
        image.recycle();
        image = null;
        Bitmap newimage = BitmapFactory.decodeStream(new ByteArrayInputStream(outstream.toByteArray()));
        setHeight(newimage.getHeight());
        setWidth(newimage.getWidth());
        setRowbytes(newimage.getRowBytes());

                return newimage;
    }


    private Bitmap resizeBitmap(Bitmap image){
        Double width = (double) image.getWidth();
        Double height = (double) image.getHeight();
        Double max = 100.0;

        if(width > height){
           height = max * (height/width);
           width = max; }
        else {
           width = max * (width/height);
           height = max;
        }

        Bitmap bmp = Bitmap.createScaledBitmap(image, width.intValue(), height.intValue(), Boolean.FALSE);
        image.recycle();
        image = null;

        return  bmp;


    }

    //taken from http://stackoverflow.com/questions/10191871/converting-bitmap-to-bytearray-android

    private byte[] convertBitmap(Bitmap image) {

        int bytes = image.getHeight() * image.getRowBytes();
        ByteBuffer buffer = ByteBuffer.allocate(bytes);
        image.copyPixelsToBuffer(buffer);
        return buffer.array();
    }


    public byte[] getImageserial() {
        return imageserial;
    }

    public void setImageserial(byte[] imageserial) {
        this.imageserial = imageserial;
    }

    Bitmap constructImage(){


        //Bitmap convert = BitmapFactory.decodeByteArray(getImageserial(), 0, getImageserial().length-1);

        Bitmap.Config configBmp = Bitmap.Config.valueOf(getConfigname());
        Bitmap construct = Bitmap.createBitmap(getWidth(), getHeight(), configBmp);
        ByteBuffer buffer = ByteBuffer.wrap(getImageserial());

        construct.copyPixelsFromBuffer(buffer);


        return construct;
    }

    public int getRowbytes() {
        return rowbytes;
    }

    public void setRowbytes(int rowbytes) {
        this.rowbytes = rowbytes;
    }

}
