package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.adapter.PostAdapter;
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


}

