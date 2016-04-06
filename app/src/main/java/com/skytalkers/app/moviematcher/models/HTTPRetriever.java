package com.skytalkers.app.moviematcher.models;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Bruce on 2/22/2016.
 */
public class HTTPRetriever implements Runnable {
    /**
     * Request String
     */
    private String req;
    /**
     * Response String
     */
    private String res;

    /**
     * Constructor to set request string
     * @param r Request string
     */
    public HTTPRetriever(String r) {
        req = r;
    }

    @Override
    public void run() {
        try {
            Log.d("**MOVIEMATCHER**", "Requesting HTTP");
            final URL url = new URL(req);
            final InputStream iss = url.openStream();
            final StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = iss.read()) != -1) {
                sb.append((char) ch);
            }
            res = sb.toString();
        } catch (IOException e) {
            Log.d("**MOVIEMATCHER**", e.toString());
            return;
        }
    }

    /**
     * Get JSON response string
     * @return JSON response string
     */
    public String getResponse() {
        return res;
    }
}
