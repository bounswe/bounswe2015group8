package com.cmpe.bounswe2015group8.westory.front;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.model.Post;

/**
 * Fragment for creating and editing posts.
 * Depending on the arguments passed into it, it can act both as an editing
 * page and a new post creation page. It's title changes with the post being
 * edited / created.
 * @see Post
 * @author xyllan
 * Date: 15.11.2015.
 */
public class PostEditFragment extends NamedFragment implements View.OnClickListener {
    public static final String NAME = "POST_EDIT";
    Button btnSubmit;
    EditText etTitle, etContent;
    boolean isNew = true;
    Post post;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_post_edit,container,false);
        etTitle = (EditText) v.findViewById(R.id.etPostEditTitle);
        etContent = (EditText) v.findViewById(R.id.etPostEditContent);
        btnSubmit = (Button) v.findViewById(R.id.btnPostEditSubmit);
        btnSubmit.setOnClickListener(this);
        initViews(getArguments());
        return v;
    }
    private void initViews(Bundle args) {
        isNew = args==null || args.getBoolean("isNew",true);
        if(isNew) {
            post = new Post();
        } else {
            post = new Post(args);
            etTitle.setText(post.getTitle());
            etContent.setText(post.getContent());
        }
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnHeritageSubmit:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                dialogBuilder.setMessage("This will (in time) work.");
                dialogBuilder.setPositiveButton("OK", null);
                dialogBuilder.show();
                return;
        }
    }
    @Override
    String getName() {
        return NAME;
    }
    @Override
    String getTitle() {
        if(isNew) {
            return getResources().getString(R.string.title_post_new);
        } else {
            return getResources().getString(R.string.title_generic_edit) + ": "
                    + (post.getTitle() == null ? "Post" : post.getTitle());
        }
    }
}
