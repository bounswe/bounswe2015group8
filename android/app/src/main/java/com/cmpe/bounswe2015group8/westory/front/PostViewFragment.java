package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.model.Post;

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
    Button btnEdit;
    TextView tvOwner, tvCreationDate, tvLastEditDate, tvContent;
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
        btnEdit = (Button) v.findViewById(R.id.btnPostViewEdit);
        btnEdit.setOnClickListener(this);
        initViews(this.getArguments());
        return v;
    }
    private void initViews(Bundle args) {
        post = new Post(args);
        tvOwner.setText("owner");
        //TODO fix this once owner is properly stored
        //tvOwner.setText(post.getOwner().getUsername());
        tvCreationDate.setText(post.getPostDate().toString());
        if(post.getLastEditedDate()!=null) {
            tvLastEditDate.setText(post.getLastEditedDate().toString());
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
                Bundle b = post.getBundle();
                b.putBoolean("isNew", false);
                nf.setArguments(b);
                MainActivity.beginFragment(getActivity(),nf);
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
