package com.cmpe.bounswe2015group8.westory;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class RegisterFragment extends NamedFragment implements View.OnClickListener {
    public static final String NAME = "REGISTER";
    Button btnRegister;
    EditText etUsername, etMail, etPassword, etPasswordConfirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_register,container,false);

        etUsername = (EditText) v.findViewById(R.id.etUsername);
        etMail = (EditText) v.findViewById(R.id.etMail);
        etPassword = (EditText) v.findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) v.findViewById(R.id.etPasswordConfirm);
        btnRegister = (Button) v.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                String username = etUsername.getText().toString();
                String mail = etMail.getText().toString();
                String password = etPassword.getText().toString();
                String passwordConfirm = etPasswordConfirm.getText().toString();

                if (password.contentEquals(passwordConfirm)) {
                    User user = new User(username, mail, password);
                    registerUser(user );
                } else {
                    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
                    dialogBuilder.setMessage("Passwords do not match.");
                    dialogBuilder.setPositiveButton("OK", null);
                    dialogBuilder.show();
                    return;
                }
                break;
        }
    }

    private void registerUser (User user){
        ServerRequests serverRequests = new ServerRequests(getActivity());
        final Activity a = this.getActivity();
        serverRequests.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                MainActivity.beginFragment(a, new LoginFragment());
            }
        });
    }
    @Override
    public String getName() {
        return NAME;
    }
 }
