package com.skytalkers.app.moviematcher.models;

import android.util.Log;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Bruce on 2/22/2016.
 */
public class HTTPRetriever implements Runnable {
    private String req;
    private String res;
    private InputStream is;
    private static boolean err = false;

    public HTTPRetriever(String r) {
        req = r;
    }

    @Override
    public void run() {
        try {
            Log.d("**MOVIEMATCHER**", "Requesting HTTP");
            URL url = new URL(req);
            InputStream is = url.openStream();
            this.is = is;
            StringBuilder sb = new StringBuilder();
            int ch;
            while ((ch = is.read()) != -1) sb.append((char) ch);
            res = sb.toString();
        } catch (Exception e) {
            Log.d("**MOVIEMATCHER**", e.toString());
            err = true;
            return;
        }
    }

    public InputStream getStream() { return is; }

    public String getResponse() {
        return res;
    }

    public boolean getErr() {
        if (err) {
            err = false;
            return true;
        }
        return false;
    }
}
