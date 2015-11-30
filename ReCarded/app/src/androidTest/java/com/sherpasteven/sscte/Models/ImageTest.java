package com.sherpasteven.sscte.Models;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Base64;

import com.sherpasteven.sscte.AddCardActivity;
import com.sherpasteven.sscte.ApplicationTest;
import com.sherpasteven.sscte.InventoryActivity;
import com.sherpasteven.sscte.R;

import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by joshua on 29/11/15.
 */
public class ImageTest extends ActivityInstrumentationTestCase2 {


    public ImageTest() {
        super(com.sherpasteven.sscte.InventoryActivity.class);
    }


    public void testStart() throws Exception {
        Activity activity = getActivity();
    }

    private int id;
    private Bitmap staticbmp;
    private Bitmap bmp;
    private Image image;
    private InventoryActivity invactivity;

/*
    public void setUp() throws Exception {
        super.setUp();
        id = R.drawable.splash_page;
        invactivity = (InventoryActivity) getActivity();
        staticbmp = BitmapFactory.decodeResource(invactivity.getResources(), id);
        bmp = Bitmap.createScaledBitmap(staticbmp, 500, 500, Boolean.FALSE);
        ByteArrayOutputStream baos= new  ByteArrayOutputStream();

        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);

        byte [] b = baos.toByteArray();
        String string= Base64.encodeToString(b, Base64.DEFAULT);

        byte[] encodeByte = Base64.decode(string, Base64.DEFAULT);
        bmp = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);


    }

    public void tearDown() throws Exception {


    }

    public void testConstructImage() throws Exception {
        image = new Image(id, getActivity());
        assertEquals(bmp, image.constructImage());

    }

    public void testConstructImageByBitmap() throws Exception {
        image = new Image(staticbmp);
        assertEquals(bmp, image.constructImage());

    }
    */
}

