package com.cmpe.bounswe2015group8.westory.front;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.model.Post;

/**
 * Created by xyllan on 17.11.2015.
 */
public class PostsFragment extends NamedFragment {
    public static final String NAME = "All Posts";
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_heritages,container,false);
        listView = (ListView) v.findViewById(R.id.lvHeritages);
        ServerRequests sr = new ServerRequests(getActivity());
        sr.getAllPosts(new Consumer<Post[]>() {
            @Override
            public void accept(Post[] posts) {
                setAdapter(posts);
            }
        });
        return v;
    }
    private void setAdapter(Post[] posts) {
        listView.setAdapter(new PostAdapter(getActivity(),R.layout.post_small, posts));
    }
    @Override
    String getName() {
        return NAME;
    }

    @Override
    String getTitle() {
        return NAME;
    }

    class PostAdapter extends ArrayAdapter<Post> {

        public PostAdapter(Context context, int resource, Post[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.post_small,parent,false);
            TextView tvTitle = (TextView) v.findViewById(R.id.tvPostSmallTitle);
            TextView tvOwner = (TextView) v.findViewById(R.id.tvPostSmallOwner);
            TextView tvCreationDate = (TextView) v.findViewById(R.id.tvPostSmallCreationDateValue);
            TextView tvContent = (TextView) v.findViewById(R.id.tvPostSmallContent);
            TextView tvSeeHeritages = (TextView) v.findViewById(R.id.tvPostSmallSeeHeritages);
            final Post p = getItem(position);
            tvTitle.setText(p.getTitle());
            tvOwner.setText(""+p.getOwnerId());
            tvCreationDate.setText(p.getPostDate());
            tvContent.setText(p.getContent());
            tvSeeHeritages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NamedFragment nf = new PostViewFragment();
                    nf.setArguments(p.getBundle());
                    MainActivity.beginFragment(getActivity(),nf);
                }
            });
            return v;
        }
    }
}

