package com.skytalkers.app.moviematcher.models;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Bruce on 2/28/2016.
 */
public class ToastWrapper {
    private ToastWrapper() {

    }

    public static void show(Context c, String mes) {
        Toast t = Toast.makeText(c, mes, Toast.LENGTH_LONG);
        t.show();
    }
}
