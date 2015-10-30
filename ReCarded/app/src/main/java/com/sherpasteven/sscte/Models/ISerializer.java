package com.sherpasteven.sscte.Models;

import android.content.Context;

/**
 * generic interface for serializing objects
 * @param <T> type of object to serialize
 */
public interface ISerializer<T> {
    /**
     * Serialize object
     * @param item object to serialize
     * @param context app context
     */
    void Serialize(T item, Context context);
}
