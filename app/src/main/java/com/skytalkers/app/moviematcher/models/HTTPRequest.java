package com.skytalkers.app.moviematcher.models;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by Bruce on 2/22/2016.
 */
public class HTTPRequest {

    private String req;
    private String res;
    private InputStream is;
    private String image;

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
        Bitmap bmp = retriever.getImage();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        bmp.recycle();
        byte[] bytes = stream.toByteArray();
        image = Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    public String getResponse() { return res; }

    public InputStream getStream() throws InterruptedException { return is; }

    public String getImage() throws Exception { return image; }

    public static String getKey() {
        return "yedukp76ffytfuy24zsqk7f5&";
    }

    public static boolean getErr() {
        HTTPRetriever retriever = new HTTPRetriever("");
        return retriever.getErr();
    }

}
