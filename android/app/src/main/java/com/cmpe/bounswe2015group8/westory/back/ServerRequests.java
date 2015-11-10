package com.cmpe.bounswe2015group8.westory.back;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.cmpe.bounswe2015group8.westory.model.Requestable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by bugrahan on 31/10/15.
 */
public class ServerRequests{
    public enum HTTP_Method {GET,POST,DELETE};
    private ProgressDialog progressDialog;
    private HTTP_Method method;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://ec2-54-187-115-133.us-west-2.compute.amazonaws.com:8080/lokum_v2";       // FIXME server address
    //public static final String SERVER_ADDRESS = "http://localhost:12122";
    public ServerRequests(Context context, HTTP_Method method){
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please wait...");
        this.method = method;

    }

    public void storeDataInBackground(Requestable t, GetCallback<String> callback) {
        progressDialog.show();
        new StoreDataAsyncTask(callback).execute(t);
    }
    public void fetchDataInBackground(Requestable send, Requestable recieve, GetCallback<Requestable> callback) {
        progressDialog.show();
        new FetchDataAsyncTask(recieve, callback).execute(send);
    }

    public class StoreDataAsyncTask extends AsyncTask<Requestable, Void, String> {
        GetCallback<String> callback;

        public StoreDataAsyncTask(GetCallback<String> callback) {
            this.callback = callback;
        }

        @Override
        protected String doInBackground(Requestable... params) {
            for(Requestable r : params) {
                ArrayList<NameValuePair> dataToSend = r.getData();
                HttpParams httpRequestParams = new BasicHttpParams();
                HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
                HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

                HttpClient client = new DefaultHttpClient(httpRequestParams);
                HttpPost post = new HttpPost(SERVER_ADDRESS + r.getEndpoint());

                try {
                    post.setEntity(new UrlEncodedFormEntity(dataToSend));
                    HttpResponse httpResponse = client.execute(post);

                    HttpEntity entity = httpResponse.getEntity();
                    String result = EntityUtils.toString(entity);
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            callback.done(s);
            super.onPostExecute(s);
        }
    }

    public class FetchDataAsyncTask extends AsyncTask<Requestable, Void, Requestable> {
        GetCallback<Requestable> callback;
        Requestable out;

        public FetchDataAsyncTask(Requestable out, GetCallback<Requestable>  callback) {
            this.out = out;
            this.callback = callback;
        }

        @Override
        protected Requestable doInBackground(Requestable... params) {
            ArrayList<NameValuePair> dataToSend = params[0].getData();
            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            //HttpPost post = new HttpPost(SERVER_ADDRESS + "/api/login");
            HttpPost post = new HttpPost(SERVER_ADDRESS + params[0].getEndpoint());
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);

                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject.length() == 0) {
                    return null;
                } else {
                    return out;
//                    String mail = jsonObject.getString("mail");
//                    r = new User(user.username, user.password);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Requestable r) {
            progressDialog.dismiss();
            callback.done(r);
            super.onPostExecute(r);
        }
    }
}
