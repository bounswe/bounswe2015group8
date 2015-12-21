package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.front.adapter.PostAdapter;
import com.cmpe.bounswe2015group8.westory.model.Member;
import com.cmpe.bounswe2015group8.westory.model.Post;

/**
 * Created by marslanbenzer on 20.12.2015.
 */
public class PostsFeed  extends NamedFragment {
    private ListView listView;
    private Post[] posts;
    Member m;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_heritages,container,false);
        listView = (ListView) v.findViewById(R.id.lvHeritages);
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fabHeritages);
        fab.hide();
        m=null;
        if(getArguments().getParcelable("member")!=null){
            m=getArguments().getParcelable("member");
            setAdapter(m.getPosts().toArray(new Post[m.getPosts().size()]));
        }




        return v;
    }
    private void setAdapter(Post[] posts) {
        this.posts = posts;
        listView.setAdapter(new PostAdapter(getActivity(),R.layout.post_small,posts));
    }

    public static final String NAME = "PostsFeed";
    @Override
    String getName() {
        return NAME;
    }

    @Override
    String getTitle() {
        return NAME;
    }
}
