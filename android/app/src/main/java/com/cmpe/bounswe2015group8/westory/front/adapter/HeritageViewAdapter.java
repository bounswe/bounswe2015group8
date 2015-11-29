package com.cmpe.bounswe2015group8.westory.front.adapter;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.front.MainActivity;
import com.cmpe.bounswe2015group8.westory.front.NamedFragment;
import com.cmpe.bounswe2015group8.westory.front.PostViewFragment;
import com.cmpe.bounswe2015group8.westory.model.Media;
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
        inflater = activity.getLayoutInflater();
        groupNames = activity.getResources().getStringArray(R.array.heritage_view_list);
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
                return media.size();
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
                return media.get(childPosition);
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
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View v = null;
        switch(groupPosition) {
            case POSTS_VIEW_INDEX:
                v = convertView == null ? inflater.inflate(R.layout.post_small,parent,false) : convertView;
                final Post p = posts.get(childPosition);
                TextView tvTitle = (TextView) v.findViewById(R.id.tvPostSmallTitle);
                tvTitle.setText(p.getTitle());
                TextView tvOwner = (TextView) v.findViewById(R.id.tvPostSmallOwner);
                tvOwner.setText(activity.getResources().getString(R.string.generic_by_username, Long.toString(p.getOwnerId())));
                TextView tvCreationDate = (TextView) v.findViewById(R.id.tvPostSmallCreationDateValue);
                tvCreationDate.setText(p.getPostDate());
                TextView tvContent = (TextView) v.findViewById(R.id.tvPostSmallContent);
                tvContent.setText(p.getContent());
                TextView tvSeeMore = (TextView) v.findViewById(R.id.tvPostSmallSeeMore);
                tvSeeMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NamedFragment nf = new PostViewFragment();
                        Bundle b = new Bundle();
                        b.putParcelable("post", p);
                        nf.setArguments(b);
                        MainActivity.beginFragment(activity, nf);
                    }
                });
                break;
            case TAGS_VIEW_INDEX:
                //TODO add tags here
                break;
            case MEDIA_VIEW_INDEX:
                //TODO add media here
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
