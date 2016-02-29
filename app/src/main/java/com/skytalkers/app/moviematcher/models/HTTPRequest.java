package com.skytalkers.app.moviematcher.models;


import android.graphics.Bitmap;

import java.io.InputStream;

/**
 * Created by Bruce on 2/22/2016.
 */
public class HTTPRequest {

    private String req;
    private String res;
    private InputStream is;
    private Bitmap image;

    public HTTPRequest(String req) {
        this.req = req;
    }

    public void sendRequest() throws Exception {
        HTTPRetriever retriever = new HTTPRetriever(req);
        Thread thread = new Thread(retriever);
        thread.start();
        thread.join();
        res = retriever.getResponse();
    }

    public void sendImageRequest() throws Exception {
        HTTPImageRetriever retriever = new HTTPImageRetriever(req);
        Thread thread = new Thread(retriever);
        thread.start();
        thread.join();
        image = retriever.getImage();
    }

    public String getResponse() { return res; }

    public InputStream getStream() throws InterruptedException { return is; }

    public Bitmap getImage() throws Exception { return image; }

    public static String getKey() {
        return "yedukp76ffytfuy24zsqk7f5&";
    }

    public static boolean getErr() {
        HTTPRetriever retriever = new HTTPRetriever("");
        return retriever.getErr();
    }

}
