package com.skytalkers.app.moviematcher.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Bruce on 2/29/2016.
 */
public class HTTPImageRetriever implements Runnable {

    /**
     * Request String
     */
    private String req;
    /**
     * Movie image
     */
    private Bitmap image;

    /**
     * Constructor to set request
     * @param r Request String
     */
    public HTTPImageRetriever(String r) { req = r; }

    @Override
    public void run() {
        try {
            Log.d("**MOVIEMATCHER**", "Requesting HTTP");
            final URL url = new URL(req);
            final InputStream is = url.openStream();
            image = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            Log.d("**MOVIEMATCHER**", e.toString());
            return;
        }
    }

    /**
     * Get image
     * @return image
     */
    public Bitmap getImage() { return image; }

}
