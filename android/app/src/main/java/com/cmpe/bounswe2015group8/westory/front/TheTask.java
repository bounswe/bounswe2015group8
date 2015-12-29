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
    int maxHeight;

    public TheTask(String link, ImageView imgV,int max) {
        super();
        imageUrl = link;
        imgView = imgV;
        maxHeight=max;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {

        try {
            image=decodeSampledBitmapFromResource(imageUrl, maxHeight, maxHeight);
            //image = downloadBitmap(imageUrl);
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

    /*private Bitmap downloadBitmap(String urlStr) {
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
    }*/
    public static Bitmap decodeSampledBitmapFromResource(String urlStr,int reqWidth, int reqHeight) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url
                .openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(input, null, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        System.out.println("aaloo: " + options.inSampleSize);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        connection = (HttpURLConnection) url
                .openConnection();
        connection.setDoInput(true);
        connection.connect();
        input = connection.getInputStream();
        return BitmapFactory.decodeStream(input, null, options);
    }
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
}