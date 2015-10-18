package com.sherpasteven.sscte.Models;

/**
 * Created by elias on 17/10/15.
 */
public interface IDeSerializer<T> {
    T Deserialize(int id);
}
