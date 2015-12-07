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
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.front.adapter.HeritageViewAdapter;
import com.cmpe.bounswe2015group8.westory.model.Heritage;
import com.cmpe.bounswe2015group8.westory.model.Media;
import com.cmpe.bounswe2015group8.westory.model.Post;
import com.cmpe.bounswe2015group8.westory.model.Tag;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Fragment for viewing heritage objects.
 * Needs a {@link Heritage} object to be passed in as argument.
 * Contains links to {@link HeritageEditFragment} for editing and {@link PostEditFragment}
 * for posting about this object.
 * @see Heritage
 * @author xyllan
 * Date: 15.11.2015.
 */
public class HeritageViewFragment extends NamedFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String NAME = "HERITAGE_VIEW";
    private TextView tvPlace, tvCreationDate, tvDescription;
    private Heritage heritage;
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
        View header = inflater.inflate(R.layout.fragment_heritage_view_header,elvData,false);
        tvPlace = (TextView) header.findViewById(R.id.tvHeritageViewPlaceValue);
        tvCreationDate = (TextView) header.findViewById(R.id.tvHeritageViewCreationDateValue);
        tvDescription = (TextView) header.findViewById(R.id.tvHeritageViewDescriptionValue);
        Button btnEdit = (Button) header.findViewById(R.id.btnHeritageViewEdit);
        Button btnAdd = (Button) header.findViewById(R.id.btnHeritageViewAdd);
        if(memberLocalStore.getUserLoggedIn()) {
            btnEdit.setOnClickListener(this);
            btnAdd.setOnClickListener(this);
        } else {
            btnEdit.setVisibility(View.GONE);
            btnAdd.setVisibility(View.GONE);
        }
        initViews(this.getArguments());
        elvData.addHeaderView(header);
        manualRefresh();
        return v;
    }
    private void initViews(Bundle args) {
        heritage = args.getParcelable("heritage");
        tvPlace.setText(heritage.getPlace());
        tvCreationDate.setText(heritage.getPostDate());
        tvDescription.setText(heritage.getDescription());
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnHeritageViewEdit:
                NamedFragment nf = new HeritageEditFragment();
                Bundle b = new Bundle();
                b.putBoolean("isNew", false);
                b.putParcelable("heritage",heritage);
                nf.setArguments(b);
                MainActivity.beginFragment(getActivity(),nf);
                break;
            case R.id.btnHeritageViewAdd:
                add();
                break;
        }
    }
    private void add() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add...");
        final String[] mediaTypes = getResources().getStringArray(R.array.heritage_view_singular_list);
        builder.setItems(mediaTypes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                addPost();
                                break;
                            case 1:
                                addTag();
                                break;
                            case 2:
                                uploadMedia();
                                break;
                        }
                    }
                });
        builder.show();
    }
    private void addPost() {
        NamedFragment pef = new PostEditFragment();
        Bundle b2 = new Bundle();
        b2.putLong("heritageId",heritage.getId());
        pef.setArguments(b2);
        MainActivity.beginFragment(getActivity(),pef);
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
                        //TODO add tag
                        heritage.addTags(t);
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
                        Media m = new Media(heritage.getId(), link, requestCode, false);
                        //TODO upload media to server once API call is ready
                        Toast.makeText(getActivity(), link, Toast.LENGTH_LONG).show();
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
        return heritage.getName();
    }

    @Override
    public void onRefresh() {
        ServerRequests sr = new ServerRequests(getActivity());
//        sr.getPostsByHeritageId(heritage.getId(), new Consumer<Post[]>() {
//            @Override
//            public void accept(Post[] posts) {
//                elvData.setAdapter(new HeritageViewAdapter(getActivity(),Arrays.asList(posts),null,null));
//                heritage.setPosts(Arrays.asList(posts));
//            }
//        });
        sr.getHeritageById(heritage.getId(), new Consumer<Heritage>() {
            @Override
            public void accept(Heritage h) {
                updateAdapter(h);
                heritage.setPosts(h.getPosts());
                heritage.setTags(h.getTags());
                heritage.setMedia(h.getMedia());
            }
        });
    }
    private void updateAdapter() { updateAdapter(heritage); }
    private void updateAdapter(Heritage h) {
        elvData.setAdapter(new HeritageViewAdapter(getActivity(), new ArrayList<>(h.getPosts()), new ArrayList<>(h.getTags()), new ArrayList<>(h.getMedia())));
    }
    private void manualRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }
}
