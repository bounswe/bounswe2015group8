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
    public void registerMemberInBackground(Member m, Consumer<Long> callback) {
        progressDialog.show();
        new RestAsyncTask<Long>(callback, HttpMethod.POST).execute(m.getRegisterRequestable());
    }
    public class RestAsyncTask<T> extends AsyncTask<Requestable<T>, Void, T> {
        Consumer<T> callback;
        HttpMethod method;
        public RestAsyncTask(Consumer<T>  callback, HttpMethod method) {
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
            callback.accept(t);
            super.onPostExecute(t);
        }
    }
}
