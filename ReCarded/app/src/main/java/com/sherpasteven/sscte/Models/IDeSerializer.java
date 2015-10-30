package com.sherpasteven.sscte.Models;

import android.content.Context;

/**
 * generic interface for deserializing objects
 * @param <T> type of objects to deserialize
 */
public interface IDeSerializer<T> {
    /**
     * deserialize object
     * @param id id of object to deserialize
     * @param context app context
     * @return deserialized object
     */
    T Deserialize(Object id, Context context);
}
