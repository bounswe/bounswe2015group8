package com.cmpe.bounswe2015group8.westory.back;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.cmpe.bounswe2015group8.westory.model.Member;
import com.cmpe.bounswe2015group8.westory.model.Requestable;

import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bugrahan on 31/10/15.
 */
public class ServerRequests{
    private ProgressDialog progressDialog;
    public static final String SERVER_ADDRESS = "http://ec2-54-187-115-133.us-west-2.compute.amazonaws.com:8080/lokum_v2";
    public ServerRequests(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing...");
        progressDialog.setMessage("Please wait...");

    }

//    public void storeDataInBackground(Requestable t, GetCallback<String> callback) {
//        progressDialog.show();
//        new StoreDataAsyncTask(callback).execute(t);
//    }
    public void registerMemberInBackground(Member m, GetCallback<Long> callback) {
        progressDialog.show();
        new RestAsyncTask<Long>(callback, HttpMethod.POST).execute(m.getRegisterRequestable());
    }

//    public class StoreDataAsyncTask extends AsyncTask<Requestable, Void, String> {
//        GetCallback<String> callback;
//
//        public StoreDataAsyncTask(GetCallback<String> callback) {
//            this.callback = callback;
//        }
//
//        @Override
//        protected String doInBackground(Requestable... params) {
//            for(Requestable r : params) {
//
//                ArrayList<NameValuePair> dataToSend = r.getData();
//                HttpParams httpRequestParams = new BasicHttpParams();
//                HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_TIMEOUT);
//                HttpConnectionParams.setSoTimeout(httpRequestParams, CONNECTION_TIMEOUT);
//
//                HttpClient client = new DefaultHttpClient(httpRequestParams);
//                HttpPost post = new HttpPost(SERVER_ADDRESS + r.getEndpoint());
//
//                try {
//                    post.setEntity(new UrlEncodedFormEntity(dataToSend));
//                    HttpResponse httpResponse = client.execute(post);
//
//                    HttpEntity entity = httpResponse.getEntity();
//                    String result = EntityUtils.toString(entity);
//                    return result;
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            progressDialog.dismiss();
//            callback.done(s);
//            super.onPostExecute(s);
//        }
//    }

    public class RestAsyncTask<T> extends AsyncTask<Requestable<T>, Void, T> {
        GetCallback<T> callback;
        HttpMethod method;
        public RestAsyncTask(GetCallback<T>  callback, HttpMethod method) {
            this.callback = callback;
            this.method = method;
        }

        @Override
        protected T doInBackground(Requestable<T>... params) {
            RestTemplate rt = new RestTemplate(true);
            rt.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            switch (method) {
                case GET:
                    return rt.getForObject(SERVER_ADDRESS + params[0].getEndpoint(), params[0].getDataClass());
                case POST:
                    return rt.postForObject(SERVER_ADDRESS + params[0].getEndpoint(), params[0].getData(), params[0].getDataClass());
                default:
                    return null;
            }
        }

        @Override
        protected void onPostExecute(T t) {
            progressDialog.dismiss();
            callback.done(t);
            super.onPostExecute(t);
        }
    }
}
