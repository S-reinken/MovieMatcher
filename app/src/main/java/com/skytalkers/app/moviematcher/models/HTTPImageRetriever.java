package com.skytalkers.app.moviematcher.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Bruce on 2/29/2016.
 */
public class HTTPImageRetriever implements Runnable {

    private String req;
    private Bitmap image;

    public HTTPImageRetriever(String r) { req = r; }

    @Override
    public void run() {
        try {
            Log.d("**MOVIEMATCHER**", "Requesting HTTP");
            final URL url = new URL(req);
            final InputStream is = url.openStream();
            image = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            Log.d("**MOVIEMATCHER**", e.toString());
            return;
        }
    }

    public Bitmap getImage() { return image; }

}
