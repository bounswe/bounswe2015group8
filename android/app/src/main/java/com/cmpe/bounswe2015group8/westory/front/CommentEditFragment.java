package com.cmpe.bounswe2015group8.westory.front;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.model.Comment;

import org.json.JSONObject;

/**
 * Created by marslanbenzer on 27.11.2015.
 */
public class CommentEditFragment extends NamedFragment implements View.OnClickListener{
    public static final String NAME = "COMMENT_EDIT";
    public static final String TITLE = "Edit Comment";
    private Button btnSubmit;
    private EditText etContent;
    private boolean isNew = true;
    private Comment comment;
    private MemberLocalStore memberLocalStore;
    private long postId;
    private Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memberLocalStore = new MemberLocalStore(getActivity());
        View v = inflater.inflate(R.layout.fragment_comment_edit,container,false);
        etContent = (EditText) v.findViewById(R.id.etCommentEditContent);
        btnSubmit = (Button) v.findViewById(R.id.btnCommentEditSubmit);
        btnSubmit.setOnClickListener(this);
        initViews(getArguments());
        return v;
    }

    private void initViews(Bundle args) {
        isNew = args.getBoolean("isNew",true);
        if(isNew) {
            comment=new Comment();
        } else {
            comment = args.getParcelable("comment");
            etContent.setText(comment.getContent());
        }
        comment.setOwner(memberLocalStore.getLoggedInMember());
        postId = args.getLong("postId");
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnCommentEditSubmit:
                comment.setContent(etContent.getText().toString());
                ServerRequests sr = new ServerRequests(getActivity());
                 sr.createComment(comment, postId, new Consumer<String>() {
                    @Override
                    public void accept(String json) {
                        Long id=null;
                        try {

                            JSONObject obj = new JSONObject(json);
                            id=obj.getLong("id");
                            Log.d("My App", obj.toString());

                        } catch (Throwable t) {
                            Log.e("My App", "Could not parse malformed JSON: \"" + json + "\"");
                        }
                        comment.setId(id);
                        NamedFragment nf = new PostsFragment();
                        MainActivity.beginFragment(getActivity(),nf);
                    }
                });
                break;
        }
    }
    @Override
    String getName() {
        return NAME;
    }
    @Override
    String getTitle() {
        return TITLE;
    }

}




