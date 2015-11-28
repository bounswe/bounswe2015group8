package com.cmpe.bounswe2015group8.westory.front;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.model.Member;
/**
 * Fragment for registering onto the site.
 * Performs a client side password check. Stores user details if successful.
 * @see Member
 * @author xyllan
 * Date: 01.11.2015.
 */
public class RegisterFragment extends NamedFragment implements View.OnClickListener {
    public static final String NAME = "Register";
    private Button btnRegister;
    private EditText etUsername, etMail, etPassword, etPasswordConfirm;
    private MemberLocalStore memberLocalStore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_register,container,false);
        etUsername = (EditText) v.findViewById(R.id.etRegisterUsername);
        etMail = (EditText) v.findViewById(R.id.etRegisterEmail);
        etPassword = (EditText) v.findViewById(R.id.etRegisterPassword);
        etPasswordConfirm = (EditText) v.findViewById(R.id.etRegisterPasswordConfirm);
        btnRegister = (Button) v.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
        memberLocalStore = new MemberLocalStore(getActivity());
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                String username = etUsername.getText().toString();
                String email = etMail.getText().toString();
                String password = etPassword.getText().toString();
                String passwordConfirm = etPasswordConfirm.getText().toString();

                if (password.contentEquals(passwordConfirm)) {
                    Member member = new Member(username, password, email,"");
                    registerMember(member);
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

    private void registerMember(final Member member){
        ServerRequests serverRequests = new ServerRequests(getActivity());
        final MainActivity a = (MainActivity) getActivity();
        serverRequests.register(member, new Consumer<Long>() {
            @Override
            public void accept(Long l) {
                member.setId(l);
                memberLocalStore.storeUserData(member);
                a.resetNavbar();
                Toast.makeText(getActivity(), "Congratulations " + member.getUsername() + ", you are logged in!", Toast.LENGTH_LONG).show();
                MainActivity.beginFragment(a, new HomeFragment());
            }
        });
    }
    @Override
    public String getName() {
        return NAME;
    }
    @Override
    public String getTitle() { return NAME; }
 }
