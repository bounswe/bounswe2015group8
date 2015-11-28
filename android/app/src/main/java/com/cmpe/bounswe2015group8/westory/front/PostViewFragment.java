package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.adapter.CommentAdapter;
import com.cmpe.bounswe2015group8.westory.front.adapter.PostViewAdapter;
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
public class PostViewFragment extends NamedFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener{
    public static final String NAME = "POST_VIEW";
    private Button btnEdit, btnComment;
    private TextView tvOwner, tvCreationDate, tvLastEditDate, tvContent;
    private Post post;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ExpandableListView elvData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MemberLocalStore memberLocalStore = new MemberLocalStore(getActivity());
        View v = inflater.inflate(R.layout.fragment_heritage_view,container,false);
        swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.srlHeritageView);
        swipeRefreshLayout.setOnRefreshListener(this);
        elvData = (ExpandableListView) v.findViewById(R.id.lvHeritageViewPosts);
        View header = inflater.inflate(R.layout.fragment_post_view_header,elvData,false);
        tvOwner = (TextView) header.findViewById(R.id.tvPostViewOwnerValue);
        tvCreationDate = (TextView) header.findViewById(R.id.tvPostViewCreationDateValue);
        tvLastEditDate = (TextView) header.findViewById(R.id.tvPostViewLastEditDateValue);
        tvContent = (TextView) header.findViewById(R.id.tvPostViewContentValue);
        btnEdit = (Button) header.findViewById(R.id.btnPostViewEdit);
		btnComment = (Button) header.findViewById(R.id.btnPostViewNewComment);
        initViews(this.getArguments());
        if(memberLocalStore.getUserLoggedIn() && post.getOwnerId() == memberLocalStore.getLoggedInMember().getId()) {
            btnEdit.setOnClickListener(this);
        } else {
            btnEdit.setVisibility(View.GONE);
        }
        if(memberLocalStore.getUserLoggedIn()) {
            btnComment.setOnClickListener(this);
        } else {
            btnComment.setVisibility(View.GONE);
        }
        elvData.addHeaderView(header);
        manualRefresh();
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

            case R.id.btnPostViewNewComment:
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
    @Override
    public void onRefresh() {
        ServerRequests sr = new ServerRequests(getActivity());
        sr.getCommentsByPostId(post.getId(), new Consumer<Comment[]>() {
            @Override
            public void accept(Comment[] comments) {
                elvData.setAdapter(new PostViewAdapter(getActivity(),Arrays.asList(comments),null,null,null));
                post.setComments(Arrays.asList(comments));
            }
        });
    }
    private void manualRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }
}
