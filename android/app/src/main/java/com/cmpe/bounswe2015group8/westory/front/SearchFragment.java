package com.cmpe.bounswe2015group8.westory.front;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.cmpe.bounswe2015group8.westory.R;

/**
 * Created by xyllan on 05.01.2016.
 */
public class SearchFragment extends NamedFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    public static final String NAME = "Search";
    private EditText etSearch1, etSearch2;
    private Spinner spSearch;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_search,container,false);
        etSearch1 = (EditText) v.findViewById(R.id.etSearch1);
        etSearch2 = (EditText) v.findViewById(R.id.etSearch2);
        Button btnSearch = (Button) v.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
        spSearch = (Spinner) v.findViewById(R.id.spSearchFor);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.search_types, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSearch.setAdapter(arrayAdapter);
        spSearch.setSelection(0, false);
        spSearch.setOnItemSelectedListener(this);
        etSearch2.setVisibility(View.GONE);
        return v;
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
    public void onClick(View v) {
        switch(spSearch.getSelectedItemPosition()) {
            case 0:
                NamedFragment nf = new HeritageSearchFragment();
                Bundle b = new Bundle();
                b.putString("searchTerm",etSearch1.getText().toString());
                nf.setArguments(b);
                MainActivity.beginFragment(getActivity(),nf);
                break;
            case 1:
                NamedFragment nf2 = new PostSearchFragment();
                Bundle b2 = new Bundle();
                b2.putString("searchTerm",etSearch1.getText().toString());
                nf2.setArguments(b2);
                MainActivity.beginFragment(getActivity(),nf2);
                break;
            case 2:
                //TODO semantic search heritage
                break;
            case 3:
                //TODO semantic search posts
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position > 1) {
            etSearch2.setVisibility(View.VISIBLE);
            etSearch1.setHint(R.string.hint_tag_text);
            etSearch1.setText("");
            etSearch2.setText("");
        }
        else {
            etSearch2.setVisibility(View.GONE);
            etSearch1.setHint(R.string.hint_search);
            etSearch1.setText("");
            etSearch2.setText("");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
