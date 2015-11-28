package com.sherpasteven.sscte.Models;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sherpasteven.sscte.R;

/**
 * Created by joshua on 28/11/15.
 */
public class ScreenSwipePageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.activity_view_card, container, false);

        return rootView;
    }
}
