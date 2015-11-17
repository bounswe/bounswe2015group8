package com.cmpe.bounswe2015group8.westory.front;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.model.Heritage;

/**
 * Fragment for creating and editing heritage objects.
 * Depending on the arguments passed into it, it can act both as an editing
 * page and a new heritage creation page. It's title changes with the object being
 * edited / created.
 * @see Heritage
 * @author xyllan
 * Date: 09.11.2015.
 */
public class HeritageEditFragment extends NamedFragment implements View.OnClickListener {
    public static final String NAME = "HERITAGE_EDIT";
    Button btnSubmit;
    EditText etName, etPlace, etDescription;
    boolean isNew = true;
    Heritage heritage;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_heritage_edit,container,false);
        etName = (EditText) v.findViewById(R.id.etHeritageEditName);
        etPlace = (EditText) v.findViewById(R.id.etHeritageEditPlace);
        etDescription = (EditText) v.findViewById(R.id.etHeritageEditDescription);
        btnSubmit = (Button) v.findViewById(R.id.btnHeritageSubmit);
        btnSubmit.setOnClickListener(this);
        initViews(getArguments());
        return v;
    }
    private void initViews(Bundle args) {
        isNew = args==null || args.getBoolean("isNew",true);
        if(isNew) {
            heritage = new Heritage();
        } else {
            heritage = new Heritage(args);
            etName.setText(heritage.getName());
            etPlace.setText(heritage.getPlace());
            etDescription.setText(heritage.getDescription());
        }
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnHeritageSubmit:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                dialogBuilder.setMessage("This will (in time) work.");
                dialogBuilder.setPositiveButton("OK", null);
                dialogBuilder.show();
                return;
        }
    }
    @Override
    String getName() {
        return NAME;
    }
    @Override
    String getTitle() {
        if(isNew) {
            return getResources().getString(R.string.title_heritage_new);
        } else {
            return getResources().getString(R.string.title_generic_edit) + ": " + heritage.getName();
        }
    }
}