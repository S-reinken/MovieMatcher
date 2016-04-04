package com.skytalkers.app.moviematcher.models;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Bruce on 2/28/2016.
 */
public class ToastWrapper {
    /**
     * Empty constructor for a ToastWrapper object
     */
    private ToastWrapper() {

    }

    /**
     * Displays a toast
     * @param c Context of the toast
     * @param mes Message contained in the toast
     */
    public static void show(Context c, String mes) {
        final Toast t = Toast.makeText(c, mes, Toast.LENGTH_LONG);
        t.show();
    }
}
