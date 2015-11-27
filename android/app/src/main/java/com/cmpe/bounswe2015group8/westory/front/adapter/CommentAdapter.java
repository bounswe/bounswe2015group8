package com.cmpe.bounswe2015group8.westory.front.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.model.Comment;

import java.text.SimpleDateFormat;

/**
 * Created by marslanbenzer on 27.11.2015.
 */
public class CommentAdapter extends ArrayAdapter<Comment> {
    private Activity context;
    public CommentAdapter(Activity context, int resource, Comment[] objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.comment_small,parent,false);
        TextView tvOwner = (TextView) v.findViewById(R.id.tvCommentSmallOwner);
        TextView tvCreationDate = (TextView) v.findViewById(R.id.tvCommentSmallCreationDate);
        TextView tvCreationDateValue = (TextView) v.findViewById(R.id.tvCommentSmallCreationDateValue);
        TextView tvContent = (TextView) v.findViewById(R.id.tvCommentSmallContent);
        final Comment c = getItem(position);
        tvOwner.setText(context.getResources().getString(R.string.generic_by) + " " + c.getOwnerId());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String s  =c.getPostDate();
        System.out.println(s);
        tvCreationDateValue.setText(s);
        tvContent.setText(c.getContent());

        return v;
    }

}
