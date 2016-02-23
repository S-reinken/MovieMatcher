package com.skytalkers.app.moviematcher.models;

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

    public static boolean getErr() {
        HTTPRetriever retriever = new HTTPRetriever("");
        return retriever.getErr();
    }

}
