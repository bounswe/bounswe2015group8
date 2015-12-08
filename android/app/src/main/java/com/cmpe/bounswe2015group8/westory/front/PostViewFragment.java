package com.cmpe.bounswe2015group8.westory.front;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.CloudinaryAPI;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.adapter.PostViewAdapter;
import com.cmpe.bounswe2015group8.westory.model.Comment;
import com.cmpe.bounswe2015group8.westory.model.Media;
import com.cmpe.bounswe2015group8.westory.model.Post;
import com.cmpe.bounswe2015group8.westory.model.Tag;

import java.util.ArrayList;
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
    private Button btnEdit, btnAdd;
    private TextView tvOwner, tvCreationDate, tvLastEditDate, tvContent, tvVote;
    private Post post;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ExpandableListView elvData;
    private LayoutInflater inflater;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.inflater = inflater;
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
        tvVote = (TextView) header.findViewById(R.id.tvPostVoteCount);
        btnEdit = (Button) header.findViewById(R.id.btnPostViewEdit);
		btnAdd = (Button) header.findViewById(R.id.btnPostViewAdd);
        initViews(this.getArguments());
        if(memberLocalStore.getUserLoggedIn() && post.getOwnerId() == memberLocalStore.getLoggedInMember().getId()) {
            btnEdit.setOnClickListener(this);
        } else {
            btnEdit.setVisibility(View.GONE);
        }
        if(memberLocalStore.getUserLoggedIn()) {
            btnAdd.setOnClickListener(this);
        } else {
            btnAdd.setVisibility(View.GONE);
        }
        elvData.addHeaderView(header);
        manualRefresh();
        return v;
    }
    private void initViews(Bundle args) {
        post = args.getParcelable("post");
        tvOwner.setText(post.getUsername());
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
            case R.id.btnPostViewAdd:
                add();
                break;
        }
    }
    private void add() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add...");
        final String[] mediaTypes = getResources().getStringArray(R.array.post_view_singular_list);
        builder.setItems(mediaTypes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                addComment();
                                break;
                            case 1:
                                addHeritage();
                                break;
                            case 2:
                                addTag();
                                break;
                            case 3:
                                uploadMedia();
                                break;
                        }
                    }
                });
        builder.show();
    }
    private void addComment() {
        NamedFragment nf = new CommentEditFragment();
        Bundle b = new Bundle();
        b.putLong("postId", post.getId());
        nf.setArguments(b);
        MainActivity.beginFragment(getActivity(), nf);
    }
    private void addHeritage() {
        //TODO add heritage to post
    }
    private void addTag() {
        View v = inflater.inflate(R.layout.popup_tag_add,null,false);
        final EditText tagText = (EditText) v.findViewById(R.id.etPopupTagAddText);
        final EditText tagContext = (EditText) v.findViewById(R.id.etPopupTagAddContext);
        new AlertDialog.Builder(getActivity())
                .setTitle("Add Tag")
                .setView(v)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Tag t = new Tag(tagText.getText().toString(),
                                tagContext.getText().toString());
                        post.addTags(t);
                        ServerRequests sr = new ServerRequests(getActivity());
                        sr.addTags(post, new Consumer<Tag[]>() {
                            @Override
                            public void accept(Tag[] tags) {
                                post.setTags(Arrays.asList(tags));
                                Toast.makeText(getActivity(),"Successfully added tag.",Toast.LENGTH_LONG).show();
                            }
                        });
                        updateAdapter();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                })
                .show();
    }
    private void uploadMedia() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select media type");
        final String[] mediaTypes = getResources().getStringArray(R.array.media_types);
        builder.setItems(mediaTypes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        uploadMedia(which);
                    }
                });
        builder.show();
    }
    private void uploadMedia(int type) {
        startActivityForResult(CloudinaryAPI.getMediaIntent(type), type);
    }
    @Override
    public void onActivityResult(final int requestCode, int resultCode, Intent data) {
        if(CloudinaryAPI.canHandleRequest(requestCode)) {
            if(resultCode == FragmentActivity.RESULT_OK) {
                Consumer<String> c = new Consumer<String>() {
                    @Override
                    public void accept(String link) {
                        final Media m = new Media(post.getId(), link, requestCode, true);
                        ServerRequests sr = new ServerRequests(getActivity());
                        sr.addMedia(m, new Consumer<Long>() {
                            @Override
                            public void accept(Long id) {
                                m.setId(id);
                                post.getMedia().add(m);
                                updateAdapter();
                            }
                        });
                    }
                };
                new CloudinaryAPI.CloudinaryUploadTask(getActivity(),c).execute(data.getData());
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
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
        sr.getPostById(post.getId(), new Consumer<Post>() {
            @Override
            public void accept(Post p) {
                updateAdapter(p);
                post.setComments(p.getComments());
                post.setHeritages(p.getHeritages());
                post.setTags(p.getTags());
                post.setMedia(p.getMedia());
            }
        });
//        sr.getCommentsByPostId(post.getId(), new Consumer<Comment[]>() {
//            @Override
//            public void accept(Comment[] comments) {
//                elvData.setAdapter(new PostViewAdapter(getActivity(),Arrays.asList(comments),null,null,null));
//                post.setComments(Arrays.asList(comments));
//            }
//        });
    }
    private void updateAdapter() { updateAdapter(post); }
    private void updateAdapter(Post p) {
        elvData.setAdapter(new PostViewAdapter(getActivity(), new ArrayList<>(p.getComments()),
                new ArrayList<>(p.getHeritages()), new ArrayList<>(p.getTags()), new ArrayList<>(p.getMedia())));
    }
    private void manualRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }
}
