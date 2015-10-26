package com.cmpe.bounswe2015group8.culturalheritage;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends ActionBarActivity implements View.OnClickListener {

    Button bRegister;
    EditText etUsername, etMail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etMail = (EditText) findViewById(R.id.etMail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);


        bRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bRegister:
                String username = etUsername.getText().toString();
                String mail = etMail.getText().toString();
                String password = etPassword.getText().toString();

                User registeredData = new User(username, mail, password);
                break;
        }
    }
}
