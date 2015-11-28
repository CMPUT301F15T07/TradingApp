package com.sherpasteven.sscte.Views.RecyclerView;
/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sherpasteven.sscte.AddCardActivity;
import com.sherpasteven.sscte.Models.Card;
import com.sherpasteven.sscte.Models.Image;
import com.sherpasteven.sscte.R;
import com.sherpasteven.sscte.ViewCardActivity;

import java.util.List;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {
    private static final String TAG = "MediaAdapter";

    private String[] mDataSet;
    List<Bitmap> images;
    static View view;
    static AddCardActivity addCardActivity;

    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView cardPhoto;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   ImageView image = addCardActivity.getImageViewCard();
                    image.setImageBitmap(addCardActivity.getCardImages().get(getPosition()));

                }
            });
            cv = (CardView) v.findViewById(R.id.cv);
            cardPhoto = getCardImage();
            view = v;

        }

        public ImageView getCardImage(){
            return (ImageView)itemView.findViewById(R.id.card_photo);
        }

    }
    // END_INCLUDE(recyclerViewSampleViewHolder)

    /**
     * Initialize the dataset of the Adapter.
     * @param images Initialise list of cards for loading.
     */

    public MediaAdapter(List<Bitmap> images, AddCardActivity addCardActivity){
        this.images = images;
        this.addCardActivity = addCardActivity;
    }

    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.media_item, viewGroup, false);

        return new ViewHolder(v);
    }
    // END_INCLUDE(recyclerViewOnCreateViewHolder)

    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        if(images != null) {
            viewHolder.getCardImage().setImageBitmap(images.get(position));
        }
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    /**
     * @return the size of your dataset (invoked by the layout manager)
     */
    @Override
    public int getItemCount() {
        if (images != null) {
            return images.size();
        } return 0;
    }
}
