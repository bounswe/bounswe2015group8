package com.cmpe.bounswe2015group8.westory.front.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.AsyncImageLoad;
import com.cmpe.bounswe2015group8.westory.model.Media;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by xyllan on 04.12.2015.
 */

public class MediaRecyclerAdapter extends RecyclerView.Adapter<MediaRecyclerAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<Media> items;
    private Map<Integer, Bitmap> bitmaps;
    private RecyclerView recyclerView;
    private Context context;

    public MediaRecyclerAdapter(List<Media> items, RecyclerView recyclerView, Context context) {
        this.items = items;
        this.recyclerView = recyclerView;
        this.context = context;
        bitmaps = new TreeMap<>();
    }
    public void setItems(List<Media> items) {
        this.items = items;
    }
    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.media_frame, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Media item = items.get(position);
        switch(item.getMediaType()) {
            case 0:
                Bitmap b = bitmaps.get(position);
                if(b == null) new AsyncImageLoad(holder,bitmaps,position).execute(item.getMediaLink());
                else holder.image.setImageBitmap(b);
                break;
            case 1:
                holder.image.setImageResource(R.drawable.ic_music_note_black_48dp);
                break;
            case 2:
                holder.image.setImageResource(R.drawable.ic_play_circle_outline_black_48dp);
                break;
        }
        holder.itemView.setTag(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    @Override
    public void onClick(View v) {
        int itemPosition = recyclerView.getChildAdapterPosition(v);
        Media item = items.get(itemPosition);
        switch (item.getMediaType()) {
            case Media.IMAGE:
                if(bitmaps.containsKey(itemPosition)) {
                    Dialog dialog = new Dialog(context,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                    dialog.setContentView(R.layout.popup_image_viewer);
                    dialog.show();
                    ImageView iv = (ImageView) dialog.findViewById(R.id.ivPopupImageViewer);
                    iv.setImageBitmap(bitmaps.get(itemPosition));
                }
                break;
            case Media.AUDIO:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getMediaLink()));
                intent.setDataAndType(Uri.parse(item.getMediaLink()), "audio/*");
                context.startActivity(intent);
                break;
            case Media.VIDEO:
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getMediaLink()));
                intent2.setDataAndType(Uri.parse(item.getMediaLink()), "video/*");
                context.startActivity(intent2);
                break;
            }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.ivMediaFrame);
        }
    }
}