package com.skytalkers.app.moviematcher.models;

import java.io.InputStream;

/**
 * Created by Bruce on 2/22/2016.
 */
public class HTTPRequest {

    private HTTPRequest() {

    }

    public static String sendRequest(String req) throws InterruptedException {
        HTTPRetriever retriever = new HTTPRetriever(req);
        Thread thread = new Thread(retriever);
        thread.start();
        thread.join();
        return retriever.getResponse();
    }

    public static String getKey() {
        return "yedukp76ffytfuy24zsqk7f5&";
    }

    public static boolean getErr() {
        HTTPRetriever retriever = new HTTPRetriever("");
        return retriever.getErr();
    }

}
