package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
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
    private Button btnSubmit;
    private EditText etTitle, etContent;
    private boolean isNew = true;
    private Post post;
    private MemberLocalStore memberLocalStore;
    private long heritageId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memberLocalStore = new MemberLocalStore(getActivity());
        View v = inflater.inflate(R.layout.fragment_post_edit,container,false);
        etTitle = (EditText) v.findViewById(R.id.etPostEditTitle);
        etContent = (EditText) v.findViewById(R.id.etPostEditContent);
        btnSubmit = (Button) v.findViewById(R.id.btnPostEditSubmit);
        btnSubmit.setOnClickListener(this);
        initViews(getArguments());
        return v;
    }
    private void initViews(Bundle args) {
        isNew = args.getBoolean("isNew",true);
        if(!isNew) {
            post = args.getParcelable("post");
            etTitle.setText(post.getTitle());
            etContent.setText(post.getContent());

        } else {
            post = new Post();
        }
        post.setOwner(memberLocalStore.getLoggedInMember());
        heritageId = args.getLong("heritageId");
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnPostEditSubmit:
                post.setTitle(etTitle.getText().toString());
                post.setContent(etContent.getText().toString());
                ServerRequests sr = new ServerRequests(getActivity());
                if(isNew) {

                    sr.createPost(post, heritageId, new Consumer<Long>() {
                        @Override
                        public void accept(Long id) {
                            post.setId(id);
                            NamedFragment nf = new PostViewFragment();
                            Bundle b = new Bundle();
                            b.putParcelable("post", post);
                            nf.setArguments(b);
                            MainActivity.beginFragment(getActivity(), nf);
                        }
                    });
                } else {
                    sr.createPost(post, heritageId, new Consumer<Long>() {
                        @Override
                        public void accept(Long id) {
                            post.setId(id);
                            NamedFragment nf = new PostViewFragment();
                            Bundle b = new Bundle();
                            b.putParcelable("post", post);
                            nf.setArguments(b);
                            MainActivity.beginFragment(getActivity(), nf);
                        }
                    });
                }
                break;
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
