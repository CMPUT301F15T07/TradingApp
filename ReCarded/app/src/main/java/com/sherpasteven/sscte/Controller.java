package com.sherpasteven.sscte;

/**
 * Created by elias on 16/10/15.
 */
public abstract class Controller<V extends IView, M extends Model> {

    private V view;
    private M model;

    public Controller(V view, M model){
        this.view = view;
        this.model = model;
    }

    protected abstract void setListeners(V view);
}
