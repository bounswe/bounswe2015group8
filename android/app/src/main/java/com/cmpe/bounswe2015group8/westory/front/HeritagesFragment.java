package com.cmpe.bounswe2015group8.westory.front;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.model.Heritage;

/**
 * Created by xyllan on 17.11.2015.
 */
public class HeritagesFragment extends NamedFragment implements AdapterView.OnItemClickListener {
    public static final String NAME = "All Heritages";
    private ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_heritages,container,false);
        listView = (ListView) v.findViewById(R.id.lvHeritages);
        listView.setOnItemClickListener(this);
        ServerRequests sr = new ServerRequests(getActivity());
        sr.getAllHeritages(new Consumer<Heritage[]>() {
            @Override
            public void accept(Heritage[] heritages) {
                setAdapter(heritages);
            }
        });
        return v;
    }
    private void setAdapter(Heritage[] heritages) {
        listView.setAdapter(new HeritageAdapter(getActivity(),R.layout.heritage_small, heritages));
    }
    @Override
    String getName() {
        return NAME;
    }

    @Override
    String getTitle() {
        return NAME;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    class HeritageAdapter extends ArrayAdapter<Heritage> {

        public HeritageAdapter(Context context, int resource, Heritage[] objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) getActivity()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.heritage_small,parent,false);
            TextView tvName = (TextView) v.findViewById(R.id.tvHeritageSmallName);
            TextView tvPlace = (TextView) v.findViewById(R.id.tvHeritageSmallPlace);
            TextView tvCreationDate = (TextView) v.findViewById(R.id.tvHeritageSmallCreationDateValue);
            TextView tvDescription = (TextView) v.findViewById(R.id.tvHeritageSmallDescription);
            TextView tvSeePosts = (TextView) v.findViewById(R.id.tvHeritageSmallSeePosts);
            final Heritage h = getItem(position);
            tvName.setText(h.getName());
            tvPlace.setText(h.getPlace());
            tvCreationDate.setText(h.getPostDate().toString());
            tvDescription.setText(h.getDescription());
            tvSeePosts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NamedFragment nf = new HeritageViewFragment();
                    nf.setArguments(h.getBundle());
                    MainActivity.beginFragment(getActivity(),nf);
                }
            });
            return v;
        }
    }
}
