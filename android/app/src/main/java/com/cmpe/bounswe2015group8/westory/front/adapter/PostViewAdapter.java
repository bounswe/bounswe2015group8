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
import com.cmpe.bounswe2015group8.westory.front.HeritageViewFragment;
import com.cmpe.bounswe2015group8.westory.front.MainActivity;
import com.cmpe.bounswe2015group8.westory.front.NamedFragment;
import com.cmpe.bounswe2015group8.westory.model.Comment;
import com.cmpe.bounswe2015group8.westory.model.Heritage;
import com.cmpe.bounswe2015group8.westory.model.Media;
import com.cmpe.bounswe2015group8.westory.model.Member;
import com.cmpe.bounswe2015group8.westory.model.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xyllan on 28.11.2015.
 */
public class PostViewAdapter extends BaseExpandableListAdapter {
    public static final int COMMENTS_VIEW_INDEX = 0;
    public static final int HERITAGES_VIEW_INDEX = 1;
    public static final int TAGS_VIEW_INDEX = 2;
    public static final int MEDIA_VIEW_INDEX = 3;
    private LayoutInflater inflater;
    private MediaRecyclerAdapter mediaRecyclerAdapter;
    private FragmentActivity activity;
    private String[] groupNames;
    private List<Comment> comments;
    private List<Heritage> heritages;
    private List<Tag> tags;
    private List<Media> media;
    public PostViewAdapter(FragmentActivity activity, List<Comment> comments, List<Heritage> heritages,
                           List<Tag> tags, List<Media> media) {
        super();
        this.activity = activity;
        this.comments = comments;
        this.heritages = heritages;
        this.tags = tags;
        this.media = media;
        if(comments == null) this.comments = new ArrayList<>();
        if(heritages == null) this.heritages = new ArrayList<>();
        if(tags == null) this.tags = new ArrayList<>();
        if(media == null) this.media = new ArrayList<>();
        inflater = activity.getLayoutInflater();
        groupNames = activity.getResources().getStringArray(R.array.post_view_list);
        mediaRecyclerAdapter = new MediaRecyclerAdapter(this.media, null, activity);
    }
    @Override
    public int getGroupCount() {
        return 4;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        switch(groupPosition) {
            case COMMENTS_VIEW_INDEX:
                return comments.size();
            case HERITAGES_VIEW_INDEX:
                return heritages.size();
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
            case COMMENTS_VIEW_INDEX:
                return comments;
            case HERITAGES_VIEW_INDEX:
                return heritages;
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
            case COMMENTS_VIEW_INDEX:
                return comments.get(childPosition);
            case HERITAGES_VIEW_INDEX:
                return heritages.get(childPosition);
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
            case COMMENTS_VIEW_INDEX:
                return childPosition;
            case HERITAGES_VIEW_INDEX:
                return comments.size()+childPosition;
            case TAGS_VIEW_INDEX:
                return comments.size()+heritages.size()+childPosition;
            case MEDIA_VIEW_INDEX:
                return comments.size()+heritages.size()+tags.size()+childPosition;
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
            case COMMENTS_VIEW_INDEX:
                v = (convertView == null || convertView.getId() != R.id.rlCommentSmall) ? inflater.inflate(R.layout.comment_small,parent,false) : convertView;
                final Comment c = comments.get(childPosition);
                TextView tvOwner = (TextView) v.findViewById(R.id.tvCommentSmallOwner);
                tvOwner.setText(activity.getResources().getString(R.string.generic_by_username, Long.toString(c.getOwnerId())));
                TextView tvCreationDate = (TextView) v.findViewById(R.id.tvCommentSmallCreationDate);
                tvCreationDate.setText(activity.getResources().getString(R.string.generic_created_at, c.getPostDate()));
                TextView tvContent = (TextView) v.findViewById(R.id.tvCommentSmallContent);
                tvContent.setText(c.getContent());
                final TextView tvVoteCount = (TextView) v.findViewById(R.id.tvCommentVoteCount);
                tvVoteCount.setText(Integer.toString(c.getNetCount()));
                ImageButton btnDownVote = (ImageButton) v.findViewById(R.id.btnCommentDownVote);
                ImageButton btnUpVote = (ImageButton) v.findViewById(R.id.btnCommentUpVote);

                btnUpVote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ServerRequests sr = new ServerRequests(activity);
                        MemberLocalStore memberLocalStore = new MemberLocalStore(activity);
                        Member m = memberLocalStore.getLoggedInMember();
                        sr.voteComment(c, true, m.getId(), new Consumer<String>() {
                            @Override
                            public void accept(String vote) {
                                tvVoteCount.setText(vote);
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
                        sr.voteComment(c, false, m.getId(), new Consumer<String>() {
                            @Override
                            public void accept(String vote) {
                                tvVoteCount.setText(vote);
                            }
                        });
                    }
                });
                tvOwner.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO launch owner's profile page
                    }
                });
                break;
            case HERITAGES_VIEW_INDEX:
                v = (convertView == null || convertView.getId() != R.id.rlHeritageSmall) ? inflater.inflate(R.layout.heritage_small,parent,false) : convertView;
                TextView tvName = (TextView) v.findViewById(R.id.tvHeritageSmallName);
                TextView tvPlace = (TextView) v.findViewById(R.id.tvHeritageSmallPlace);
                TextView tvCreationDate2 = (TextView) v.findViewById(R.id.tvHeritageSmallCreationDateValue);
                TextView tvDescription = (TextView) v.findViewById(R.id.tvHeritageSmallDescription);
                final Heritage h = heritages.get(childPosition);
                tvName.setText(h.getName());
                tvPlace.setText(h.getPlace());
                tvCreationDate2.setText(h.getPostDate());
                tvDescription.setText(h.getDescription());
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        NamedFragment nf = new HeritageViewFragment();
                        Bundle b = new Bundle();
                        b.putParcelable("heritage", h);
                        nf.setArguments(b);
                        MainActivity.beginFragment(activity, nf);
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
