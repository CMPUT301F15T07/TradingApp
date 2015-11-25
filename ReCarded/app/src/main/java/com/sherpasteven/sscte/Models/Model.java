package com.sherpasteven.sscte.Models;

import com.sherpasteven.sscte.Views.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a generic Model class using active model MVC
 * @param <V> The View class to associate with this model
 */
public class Model<V extends IView> {
    private transient List<V> views;

    public Model(){
        views = new ArrayList<V>();
    }

    public void addView(V view){
        if (views != null) {
            if (!views.contains(view)) views.add(view);
        } else {
            views = new ArrayList<V>();
            views.add(view);
        }
    }

    public void deleteView(V view){
        views.remove(view);
    }

    public void notifyViews(){
        if(views != null) {
            for (IView view : views) {
                view.Update(this);
            }
        }
    }
}
