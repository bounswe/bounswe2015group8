package com.cmpe.bounswe2015group8.westory.front;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by marslanbenzer on 20.12.2015.
 * This class is used to download and display an image
 */
public class TheTask extends AsyncTask<Void,Void,Void> {
    Bitmap image;
    String imageUrl = "";
    ImageView imgView;

    public TheTask(String link, ImageView imgV) {
        super();
        imageUrl = link;
        imgView = imgV;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            image = downloadBitmap(imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if (image != null) {
            imgView.setImageBitmap(image);
        }

    }

    private Bitmap downloadBitmap(String urlStr) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}