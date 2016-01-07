package com.cmpe.bounswe2015group8.westory.back;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.cmpe.bounswe2015group8.westory.front.adapter.MediaRecyclerAdapter;

import java.io.InputStream;
import java.util.Map;

/**
 * Basic Bitmap image loader that takes an URL and converts it to bitmap.
 * URL is given in String format.
 * @see Bitmap
 * @author xyllan
 * Date: 04.12.2015
 */
public class AsyncImageLoad extends AsyncTask<String, Void, Bitmap> {
    private MediaRecyclerAdapter.ViewHolder viewHolder;
    private Map<Integer,Bitmap> bitmaps;
    private int position;
    public AsyncImageLoad(MediaRecyclerAdapter.ViewHolder viewHolder, Map<Integer,Bitmap> bitmaps, int position) {
        this.viewHolder = viewHolder;
        this.bitmaps = bitmaps;
        this.position = position;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        viewHolder.image.setImageBitmap(result);
        bitmaps.put(position,result);
    }
}