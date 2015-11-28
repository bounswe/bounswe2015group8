package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.adapter.CommentAdapter;
import com.cmpe.bounswe2015group8.westory.model.Comment;

/**
 * Created by marslanbenzer on 27.11.2015.
 */
public class CommentsFragment extends NamedFragment {
    public static final String NAME = "All Comments";
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_heritages,container,false);
        listView = (ListView) v.findViewById(R.id.lvHeritages);
        ServerRequests sr = new ServerRequests(getActivity());
        sr.getAllComments(new Consumer<Comment[]>() {
            @Override
            public void accept(Comment[] comments) {
                setAdapter(comments);
            }
        });
        return v;
    }
    private void setAdapter(Comment[] comments) {
        listView.setAdapter(new CommentAdapter(getActivity(),R.layout.comment_small, comments));
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
