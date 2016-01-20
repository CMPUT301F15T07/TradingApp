package com.sherpasteven.sscte.Controllers;

import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Views.IView;

/**
 * Generic controller for active model MVC
 * @param <V> View to associate controller with
 * @param <M> Model to associate controller with
 */
public abstract class Controller<V extends IView, M extends Model> {

    public Controller(V view, M model){
        setListeners(view);
    }

    /**
     * set listeners on View so that the model can be updated
     * when user input comes in.
     * @param view view to set listeners on.
     */
    protected abstract void setListeners(V view);
}
