package com.sherpasteven.sscte.Controllers;

import com.sherpasteven.sscte.Models.Model;
import com.sherpasteven.sscte.Views.IView;

/**
 * Created by elias on 16/10/15.
 */
public abstract class Controller<V extends IView, M extends Model> {

    public Controller(V view, M model){
        setListeners(view);
    }

    protected abstract void setListeners(V view);
}
