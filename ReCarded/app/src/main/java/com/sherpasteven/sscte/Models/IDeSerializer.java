package com.sherpasteven.sscte.Models;

import android.content.Context;

/**
 * Created by elias on 17/10/15.
 */
public interface IDeSerializer<T> {
    T Deserialize(Object id, Context context);
}
