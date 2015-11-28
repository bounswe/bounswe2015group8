package com.cmpe.bounswe2015group8.westory.front;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cmpe.bounswe2015group8.westory.R;
import com.cmpe.bounswe2015group8.westory.back.Consumer;
import com.cmpe.bounswe2015group8.westory.back.MemberLocalStore;
import com.cmpe.bounswe2015group8.westory.back.ServerRequests;
import com.cmpe.bounswe2015group8.westory.model.Member;
/**
 * Fragment for user login. Checks for a successful login and
 * stores user details if successful.
 * @see Member
 * @author Buğrahan Memiş
 * @author xyllan
 * Date: 01.11.2015.
 */
public class LoginFragment extends NamedFragment implements View.OnClickListener {
    public static final String NAME = "Login";
    private Button btnLogin;
    private EditText etUsername, etPassword;
    private TextView tvLoginToRegister, tvLoginForgotPassword;
    private MemberLocalStore memberLocalStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_login,container,false);

        memberLocalStore = new MemberLocalStore(getActivity());

        etUsername = (EditText) v.findViewById(R.id.etLoginUsername);
        etPassword = (EditText) v.findViewById(R.id.etLoginPassword);
        btnLogin = (Button) v.findViewById(R.id.btnLogin);
        tvLoginToRegister = (TextView) v.findViewById(R.id.tvLoginToRegister);
        tvLoginForgotPassword = (TextView) v.findViewById(R.id.tvLoginForgotPassword);
        btnLogin.setOnClickListener(this);
        tvLoginToRegister.setOnClickListener(this);
        tvLoginForgotPassword.setOnClickListener(this);
        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                Member member = new Member(username, password,"","");

                authenticate(member);

                break;
            case R.id.tvLoginToRegister:
                MainActivity.beginFragment(getActivity(), new RegisterFragment());
                break;
            case R.id.tvLoginForgotPassword:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ServerRequests.SERVER_ADDRESS+"/forget_password")));
                break;
        }
    }
    private void authenticate(final Member member){
        final MainActivity a = (MainActivity) getActivity();
        ServerRequests serverRequests = new ServerRequests(a);
        serverRequests.login(member, new Consumer<Member>() {
            @Override
            public void accept(Member m) {
                m.setUsername(member.getUsername());
                memberLocalStore.storeUserData(m);
                a.resetNavbar();
                Toast.makeText(getActivity(), "Congratulations " + m.getUsername() + ", you are logged in!", Toast.LENGTH_LONG).show();
                MainActivity.beginFragment(a, new HomeFragment());
            }
        });
    }

    private void showErrorMessage() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setMessage("Incorrect user details.");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }
    @Override
    public String getName() {
        return NAME;
    }
    @Override
    public String getTitle() { return NAME; }
}
