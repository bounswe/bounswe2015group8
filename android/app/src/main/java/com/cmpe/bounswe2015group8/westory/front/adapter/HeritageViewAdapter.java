package com.cmpe.bounswe2015group8.westory.front.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.MainActivity;
import com.cmpe.bounswe2015group8.westory.front.NamedFragment;
import com.cmpe.bounswe2015group8.westory.front.PostViewFragment;
import com.cmpe.bounswe2015group8.westory.model.Media;
import com.cmpe.bounswe2015group8.westory.model.Member;
import com.cmpe.bounswe2015group8.westory.model.Post;
import com.cmpe.bounswe2015group8.westory.model.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyllan on 26.11.2015.
 */
public class HeritageViewAdapter extends BaseExpandableListAdapter {
    public static final int POSTS_VIEW_INDEX = 0;
    public static final int TAGS_VIEW_INDEX = 1;
    public static final int MEDIA_VIEW_INDEX = 2;
    private LayoutInflater inflater;
    private MediaRecyclerAdapter mediaRecyclerAdapter;
    private FragmentActivity activity;
    private String[] groupNames;
    private List<Post> posts;
    private List<Tag> tags;
    private List<Media> media;
    public HeritageViewAdapter(FragmentActivity activity, List<Post> posts, List<Tag> tags, List<Media> media) {
        super();
        this.activity = activity;
        this.posts = posts;
        this.tags = tags;
        this.media = media;
        if(posts == null) this.posts = new ArrayList<>();
        if(tags == null) this.tags = new ArrayList<>();
        if(media == null) this.media = new ArrayList<>();
        this.media.add(new Media(1, "http://antonioleiva.com/wp-content/uploads/2014/06/Screenshot_2014-06-29-21-04-43.png", 0, false));
        this.media.add(new Media(2, "http://res.cloudinary.com/bounswe2015group8/video/upload/v1449171778/c4ldnee5uk7aqtyxqupa.mp3", 1, false));
        this.media.add(new Media(3, "http://res.cloudinary.com/bounswe2015group8/video/upload/v1449171941/lqeaetbipmh8zgriwmqt.mp4", 2, false));
        this.tags.add(new Tag("asd","ccc"));
        this.tags.add(new Tag("kekekekeke","ıeufısdsdufeıf"));
        inflater = activity.getLayoutInflater();
        groupNames = activity.getResources().getStringArray(R.array.heritage_view_list);
        mediaRecyclerAdapter = new MediaRecyclerAdapter(this.media, null, activity);
    }
    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        switch(groupPosition) {
            case POSTS_VIEW_INDEX:
               return posts.size();
            case TAGS_VIEW_INDEX:
                return tags.size();
            case MEDIA_VIEW_INDEX:
                return 1;
            default:
                return -1;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        switch(groupPosition) {
            case POSTS_VIEW_INDEX:
                return posts;
            case TAGS_VIEW_INDEX:
                return tags;
            case MEDIA_VIEW_INDEX:
                return media;
            default:
                return -1;
        }
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        switch(groupPosition) {
            case POSTS_VIEW_INDEX:
                return posts.get(childPosition);
            case TAGS_VIEW_INDEX:
                return tags.get(childPosition);
            case MEDIA_VIEW_INDEX:
                return media;
            default:
                return null;
        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        switch(groupPosition) {
            case POSTS_VIEW_INDEX:
                return childPosition;
            case TAGS_VIEW_INDEX:
                return posts.size()+childPosition;
            case MEDIA_VIEW_INDEX:
                return posts.size()+tags.size()+childPosition;
            default:
                return -1;
        }
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView == null ? inflater.inflate(R.layout.fragment_heritage_view_group_item,parent,false) : convertView;
        TextView tv = (TextView) v.findViewById(R.id.tvHeritageViewGroupItemName);
        tv.setText(groupNames[groupPosition]);
        tv.setCompoundDrawablesWithIntrinsicBounds(isExpanded ? R.drawable.ic_expand_more_white_24dp : R.drawable.ic_chevron_right_white_24dp,0,0,0);
        return v;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = null;
        switch(groupPosition) {
            case POSTS_VIEW_INDEX:
                v = (convertView == null || convertView.getId() != R.id.rlPostSmall) ? inflater.inflate(R.layout.post_small,parent,false) : convertView;
                final Post p = posts.get(childPosition);
                TextView tvTitle = (TextView) v.findViewById(R.id.tvPostSmallTitle);
                tvTitle.setText(p.getTitle());
                TextView tvOwner = (TextView) v.findViewById(R.id.tvPostSmallOwner);
                tvOwner.setText(activity.getResources().getString(R.string.generic_by_username, Long.toString(p.getOwnerId())));
                //tvOwner.setText(activity.getResources().getString(R.string.generic_by_username, p.getOwner().getUsername()));
                TextView tvCreationDate = (TextView) v.findViewById(R.id.tvPostSmallCreationDateValue);
                tvCreationDate.setText(p.getPostDate());
                TextView tvContent = (TextView) v.findViewById(R.id.tvPostSmallContent);
                tvContent.setText(p.getContent());
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NamedFragment nf = new PostViewFragment();
                        Bundle b = new Bundle();
                        b.putParcelable("post", p);
                        nf.setArguments(b);
                        MainActivity.beginFragment(activity, nf);
                    }
                });
                final TextView tvVote= (TextView) v.findViewById(R.id.tvPostVoteCount);
                int netVoteCount=p.getVoteCount();
                tvVote.setText("" + netVoteCount);
                ImageButton btnDownVote = (ImageButton) v.findViewById(R.id.btnPostDownVote);
                ImageButton btnUpVote = (ImageButton) v.findViewById(R.id.btnPostUpVote);
                btnUpVote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ServerRequests sr = new ServerRequests(activity);
                        MemberLocalStore memberLocalStore = new MemberLocalStore(activity);
                        Member m = memberLocalStore.getLoggedInMember();
                        sr.votePost(p, true, m.getId(), new Consumer<String>() {
                            @Override
                            public void accept(String vote) {
                                tvVote.setText("" + vote);
                            }
                        });
                    }
                });

                btnDownVote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ServerRequests sr = new ServerRequests(activity);
                        MemberLocalStore memberLocalStore = new MemberLocalStore(activity);
                        Member m = memberLocalStore.getLoggedInMember();
                        sr.votePost(p, false, m.getId(), new Consumer<String>() {
                            @Override
                            public void accept(String vote) {
                                tvVote.setText("" + vote);
                            }
                        });
                    }
                });
                break;
            case TAGS_VIEW_INDEX:
                v = (convertView == null || convertView.getId() != R.id.vsTagSmall) ? inflater.inflate(R.layout.tag_small,parent,false) : convertView;
                final Tag t = tags.get(childPosition);
                final ViewSwitcher viewSwitcher = (ViewSwitcher) v.findViewById(R.id.vsTagSmall);
                final EditText etText = (EditText) v.findViewById(R.id.etTagSmallEditText);
                final EditText etContext = (EditText) v.findViewById(R.id.etTagSmallEditContext);
                etText.setText(t.getTagText());
                etContext.setText(t.getTagContext());
                final ImageView ivTagSmallAccept = (ImageView) v.findViewById(R.id.ivTagSmallEditAccept);
                final ImageView ivTagSmallCancel = (ImageView) v.findViewById(R.id.ivTagSmallEditCancel);
                final TextView tvText = (TextView) v.findViewById(R.id.tvTagSmallViewText);
                tvText.setText(t.getTagText());
                final TextView tvContext = (TextView) v.findViewById(R.id.tvTagSmallViewContext);
                tvContext.setText(activity.getResources().getString(R.string.generic_parenthesized, t.getTagContext()));
                final ImageView ivTagSmallEdit = (ImageView) v.findViewById(R.id.ivTagSmallViewEdit);
                if(!new MemberLocalStore(activity).getUserLoggedIn()) ivTagSmallEdit.setVisibility(View.GONE);
                ivTagSmallEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewSwitcher.showNext();
                    }
                });
                ivTagSmallAccept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Tag newTag = new Tag(etText.getText().toString(),
                                etContext.getText().toString());
                        newTag.setId(t.getId());
                        t.setTagText(newTag.getTagText());
                        t.setTagContext(newTag.getTagContext());
                        //TODO edit tag call to system. if successful, do the following
                        tvText.setText(t.getTagText());
                        tvContext.setText(activity.getResources().getString(R.string.generic_parenthesized, t.getTagContext()));
                        viewSwitcher.showPrevious();
                    }
                });
                ivTagSmallCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        etText.setText(t.getTagText());
                        etContext.setText(t.getTagContext());
                        viewSwitcher.showPrevious();
                    }
                });
                break;
            case MEDIA_VIEW_INDEX:
                v = (convertView == null || convertView.getId() != R.id.media_list) ? inflater.inflate(R.layout.media_list,parent,false) : convertView;
                RecyclerView rv = (RecyclerView) v;
                rv.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                mediaRecyclerAdapter.setRecyclerView(rv);
                rv.setAdapter(mediaRecyclerAdapter);
                break;
            default:
                return null;
        }
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
