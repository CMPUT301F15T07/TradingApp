package com.sherpasteven.sscte.Models;

/**
 * Created by elias on 16/10/15.
 */

import com.sherpasteven.sscte.Views.IView;

import java.util.ArrayList;
import java.util.List;

public class Model<V extends IView> {
    private List<V> views;

    public Model(){
        views = new ArrayList<V>();
    }

    public void addView(V view){
        if (!views.contains(view)) views.add(view);
    }

    public void deleteView(V view){
        views.remove(view);
    }

    public void notifyViews(){
        for (IView view: views){
            view.Update(this);
        }
    }
}
