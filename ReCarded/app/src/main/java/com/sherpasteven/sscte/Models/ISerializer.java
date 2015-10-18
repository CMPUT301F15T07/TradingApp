package com.sherpasteven.sscte.Models;

/**
 * Created by elias on 17/10/15.
 */
public interface ISerializer<T> {
    void Serialize(T item);
}
