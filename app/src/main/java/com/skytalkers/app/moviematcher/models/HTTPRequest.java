package com.skytalkers.app.moviematcher.models;

import android.graphics.Bitmap;

import java.io.InputStream;

/**
 * Created by Bruce on 2/22/2016.
 */
public class HTTPRequest {

    String req;
    String res;
    InputStream is;

    public HTTPRequest(String req) {
        this.req = req;
    }

    public void sendRequest() throws InterruptedException {
        HTTPRetriever retriever = new HTTPRetriever(req);
        Thread thread = new Thread(retriever);
        thread.start();
        thread.join();
        res = retriever.getResponse();
        is = retriever.getStream();
    }

    public String getResponse() { return res; }

    public InputStream getStream() throws InterruptedException { return is; }

    public static String getKey() {
        return "yedukp76ffytfuy24zsqk7f5&";
    }

    public static boolean getErr() {
        HTTPRetriever retriever = new HTTPRetriever("");
        return retriever.getErr();
    }

}
