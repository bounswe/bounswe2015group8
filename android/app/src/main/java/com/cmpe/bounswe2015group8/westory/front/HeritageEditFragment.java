package com.cmpe.bounswe2015group8.westory.front;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cmpe.bounswe2015group8.westory.R;

/**
 * Created by xyllan on 09.11.2015.
 */
public class HeritageEditFragment extends NamedFragment implements View.OnClickListener {
    public static final String NAME = "HERITAGE_EDIT";
    Button btnSubmit;
    EditText etName, etPlace, etDescription;
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
        if(savedInstanceState!=null && !savedInstanceState.getBoolean("isNew",true)) {

        } else {

        }
        return v;
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
}