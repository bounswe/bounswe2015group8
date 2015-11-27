package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.adapter.CommentAdapter;
import com.cmpe.bounswe2015group8.westory.model.Comment;
import com.cmpe.bounswe2015group8.westory.model.Post;

import java.util.Arrays;

/**
 * Fragment for viewing posts. It needs a post to be passed in as an argument.
 * Contains link for editing this post.
 * edited / created.
 * @see Post
 * @author xyllan
 * Date: 15.11.2015.
 */
public class PostViewFragment extends NamedFragment implements View.OnClickListener{
    public static final String NAME = "POST_VIEW";
    Button btnEdit, btnComment;
    TextView tvOwner, tvCreationDate, tvLastEditDate, tvContent;
    ListView lvComments;
    Post post;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_post_view,container,false);
        tvOwner = (TextView) v.findViewById(R.id.tvPostViewOwnerValue);
        tvCreationDate = (TextView) v.findViewById(R.id.tvPostViewCreationDateValue);
        tvLastEditDate = (TextView) v.findViewById(R.id.tvPostViewLastEditDateValue);
        tvContent = (TextView) v.findViewById(R.id.tvPostViewContentValue);
        lvComments=(ListView) v.findViewById(R.id.listComments);
        btnEdit = (Button) v.findViewById(R.id.btnPostViewEdit);
        btnEdit.setOnClickListener(this);
        btnComment = (Button) v.findViewById(R.id.btnAddComment);
        btnComment.setOnClickListener(this);
        initViews(this.getArguments());
        ServerRequests sr = new ServerRequests(getActivity());
        sr.getCommentsByPostId(post.getId(), new Consumer<Comment[]>() {
            @Override
            public void accept(Comment[] comments) {
                lvComments.setAdapter(new CommentAdapter(getActivity(), R.layout.comment_small, comments));
                post.setComments(Arrays.asList(comments));
            }
        });
        return v;
    }
    private void initViews(Bundle args) {
        post = args.getParcelable("post");
        //TODO fix this once owner is properly stored
        tvOwner.setText(""+post.getOwnerId());
        tvCreationDate.setText(post.getPostDate());
        if(post.getLastEditedDate()!=null) {
            tvLastEditDate.setText(post.getLastEditedDate());
        } else {
            tvLastEditDate.setText("---");
        }
        tvContent.setText(post.getContent());
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnPostViewEdit:
                NamedFragment nf = new PostEditFragment();
                Bundle b = new Bundle();
                b.putParcelable("post", post);
                b.putBoolean("isNew", false);
                nf.setArguments(b);
                MainActivity.beginFragment(getActivity(), nf);
                break;

            case R.id.btnAddComment:
                NamedFragment nf1 = new CommentEditFragment();
                Bundle b1 = new Bundle();
                b1.putLong("postId", post.getId());
                nf1.setArguments(b1);
                MainActivity.beginFragment(getActivity(), nf1);
                break;
        }
    }
    @Override
    String getName() {
        return NAME;
    }
    @Override
    String getTitle() {
        if(post.getTitle() != null) return post.getTitle();
        else return getResources().getString(R.string.post_view_title_default);
    }
}
