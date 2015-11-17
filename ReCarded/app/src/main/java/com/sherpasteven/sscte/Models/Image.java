package com.sherpasteven.sscte.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

    public Image(Bitmap image){

        image = formatBitmap(image);
        setHeight(image.getHeight());
        setWidth(image.getWidth());
        setConfigname(image.getConfig().name());
        setImageserial(convertBitmap(image));

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
        int scale = 0;
        //Bitmap newimage;
        //Bitmap retainimage = image;

        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, scale, outstream);
        Bitmap newimage = BitmapFactory.decodeStream(new ByteArrayInputStream(outstream.toByteArray()));

        //while(true){
        //while(scale >= 0){

            //ByteArrayOutputStream outstream = new ByteArrayOutputStream();
            //newimage.compress(Bitmap.CompressFormat.JPEG, scale, outstream);
            //newimage = BitmapFactory.decodeStream(new ByteArrayInputStream(outstream.toByteArray()));

            //if((newimage.getByteCount()/1024) <  64){
                return newimage;
           // }

            //image = retainimage;
            //scale -= 10;
        //}

        //return null;
    }

    private Bitmap resizeBitmap(Bitmap image){
        Double width = (double) image.getWidth();
        Double height = (double) image.getHeight();
        Double max = 20.0;



        if(width > height){
           height = max * (height/width);
           width = max; }
        else {
           width = max * (width/height);
           height = max;
        }

        return  Bitmap.createScaledBitmap(image, width.intValue(), height.intValue(), Boolean.FALSE);


    }

    //taken from http://stackoverflow.com/questions/10191871/converting-bitmap-to-bytearray-android
    private byte[] convertBitmap(Bitmap image) {

        //int bytes = image.getByteCount();
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

}
