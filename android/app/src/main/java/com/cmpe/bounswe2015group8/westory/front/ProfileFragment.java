package com.cmpe.bounswe2015group8.westory.front;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.adapter.PostAdapter;
import com.cmpe.bounswe2015group8.westory.model.Member;
import com.cmpe.bounswe2015group8.westory.model.Post;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;
import java.util.ArrayList;
/**
 * Created by marslanbenzer on 19.11.2015.
 */
public class ProfileFragment extends NamedFragment implements AdapterView.OnItemClickListener {
    public static final String NAME = "PROFILE";
    private TextView tvName;
    private ListView listView;
    private ImageView iv;
    Bitmap image ;
    ProgressDialog pd;
    MemberLocalStore memberLocalStore;
    private String profPicUrl="http://ninjaturtles-animalsdigestion.weebly.com/uploads/2/6/9/3/26932485/612610_orig.jpg";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        String url =getString(R.string.photo_url_str);
        iv = (ImageView) v.findViewById(R.id.profilePicture);
        new TheTask().execute();


        memberLocalStore = new MemberLocalStore(getActivity());
        tvName = (TextView) v.findViewById(R.id.tvHomeName);
        displayMemberDetails();
        listView = (ListView) v.findViewById(R.id.listPosts);
        Member member = memberLocalStore.getLoggedInMember();
        long id=member.getId();
        ServerRequests sr = new ServerRequests(getActivity());
        sr.getAllPosts(new Consumer<Post[]>() {
            @Override
            public void accept(Post[] posts) {
                setAdapter(posts);
            }
        });
        return v;
    }

    class TheTask extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {

            try
            {
                image = downloadBitmap(profPicUrl);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(image!=null)
            {
                iv.setImageBitmap(image);
            }

        }
    }

        private void setAdapter(Post[] posts) {
        ArrayList<Post> usersPosts = new ArrayList<Post>();
        Member member = memberLocalStore.getLoggedInMember();
        long id=member.getId();
        for (Post p:posts){
            if(p.getOwnerId()==id)
                usersPosts.add(p);
        }
        Post[] postArr = new Post[usersPosts.size()];
        postArr = usersPosts.toArray(postArr);
        listView.setAdapter(new PostAdapter(getActivity(),R.layout.post_small, postArr));
    }

    public void displayMemberDetails(){
        Member member = memberLocalStore.getLoggedInMember();
        tvName.setText(member.getUsername());

    }

    @Override
    String getName() {
        return NAME;
    }

    @Override
    String getTitle() {
        return NAME;
    }

    private Bitmap downloadBitmap(String url) {
        // initilize the default HTTP client object
        final DefaultHttpClient client = new DefaultHttpClient();

        //forming a HttoGet request
        final HttpGet getRequest = new HttpGet(url);
        try {

            HttpResponse response = client.execute(getRequest);

            //check 200 OK for success
            final int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                Log.w("ImageDownloader", "Error " + statusCode +
                        " while retrieving bitmap from " + url);
                return null;

            }

            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = null;
                try {
                    // getting contents from the stream
                    inputStream = entity.getContent();

                    // decoding stream data back into image Bitmap that android understands
                    image = BitmapFactory.decodeStream(inputStream);


                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    entity.consumeContent();
                }
            }
        } catch (Exception e) {
            // You Could provide a more explicit error message for IOException
            getRequest.abort();
            Log.e("ImageDownloader", "Something went wrong while" +
                    " retrieving bitmap from " + url + e.toString());
        }

        return image;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}