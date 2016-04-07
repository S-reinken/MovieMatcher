package com.skytalkers.app.moviematcher.models;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Object to retrieve movie information
 */
class HTTPRequest {

    /**
     * Request String
     */
    private final String req;
    /**
     * Response String
     */
    private String res;
    /**
     * Image String
     */
    private String image;

    /**
     * Constructor to set request
     * @param reqs Request string
     */
    public HTTPRequest(String reqs) {
        this.req = reqs;
    }

    /**
     * Send a movie info request
     */
    public void sendRequest() {
        final HTTPRetriever retriever = new HTTPRetriever(req);
        final Thread thread = new Thread(retriever);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Log.d("HTTPRequest", "Thread failed to join");
        }
        res = retriever.getResponse();
    }

    /**
     * Send a movie image request
     */
    public void sendImageRequest() {
        final HTTPImageRetriever retriever = new HTTPImageRetriever(req);
        final Thread thread = new Thread(retriever);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            Log.d("HTTPRequest", "Thread failed to join");
        }
        final Bitmap bmp = retriever.getImage();
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        final int cmp = 100;
        bmp.compress(Bitmap.CompressFormat.PNG, cmp, stream);
        bmp.recycle();
        final byte[] bytes = stream.toByteArray();
        image = Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    /**
     * Get JSON response
     * @return String form of JSON response
     */
    public String getResponse() { return res; }

    /**
     * Get image
     * @return image
     */
    public String getImage() { return image; }

}
