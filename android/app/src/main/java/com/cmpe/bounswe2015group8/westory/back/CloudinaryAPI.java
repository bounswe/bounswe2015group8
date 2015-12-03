package com.cmpe.bounswe2015group8.westory.back;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.cloudinary.Cloudinary;
import com.cloudinary.android.Utils;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Class handling calls to the Cloudinary API.
 * It is important to note that Cloudinary treats audio
 * files as video files with no display.
 * @author xyllan
 * Date: 02/12/15.
 */
public class CloudinaryAPI {
    public static final int AUDIO_CODE = 1;
    public static final int IMAGE_CODE = 0;
    public static final int VIDEO_CODE = 2;
    public static Intent getMediaIntent(int type) {
        Intent intent_upload = new Intent(Intent.ACTION_GET_CONTENT);
        switch (type) {
            case AUDIO_CODE:
                intent_upload.setType("audio/*");
                break;
            case IMAGE_CODE:
                intent_upload.setType("image/*");
                break;
            case VIDEO_CODE:
                intent_upload.setType("video/*");
                break;
        }
        return intent_upload;
    }
    public static boolean canHandleRequest(int requestCode) {
        return requestCode == AUDIO_CODE || requestCode == IMAGE_CODE || requestCode == VIDEO_CODE;
    }
    public static class CloudinaryUploadTask extends AsyncTask<Uri,Void,String> {
        private Context context;
        private Consumer<String> consumer;
        public CloudinaryUploadTask(Context context, Consumer<String> consumer) {
            super();
            this.context = context;
            this.consumer = consumer;

        }
        @Override
        protected String doInBackground(Uri... params) {
            Cloudinary cld = new Cloudinary(Utils.cloudinaryUrlFromContext(context));
            try {
                Map result = cld.uploader().upload(context.getContentResolver().openInputStream(
                        params[0]), ObjectUtils.asMap("resource_type", "auto"));
                return result.get("secure_url").toString();
            } catch(IOException e) {
                Log.e("WeStory", e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            consumer.accept(s);
        }
    }
}
