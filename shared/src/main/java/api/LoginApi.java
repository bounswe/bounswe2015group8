package api;

import adapter.MemberAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Member;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Goktug on 12.11.2015.
 */
@RestController
public class LoginApi implements ErrorCodes{
    @RequestMapping(value = "/api/login",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String doLogin(@RequestBody Member member) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Member.class, new MemberAdapter()).create();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ArrayList<Member> members = MemberUtility.getUserList();
        for (Member m : members) {
            if (m.getUsername().equals(member.getUsername())) {
                if (passwordEncoder.matches(member.getPassword(), m.getPassword())) {
                    return gson.toJson(m);
                } else {
                    return WRONG_PASSWORD_ERROR_CODE;
                }
            }
        }
        return USER_DOES_NOT_EXIST_ERROR_CODE;
    }

    @RequestMapping(value = "/api/getMemberById",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMemberById(@RequestBody long id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        MemberAdapter.IS_EXTENSIVE = true;
        Gson gson = gsonBuilder.registerTypeAdapter(Member.class, new MemberAdapter()).create();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ArrayList<Member> members = MemberUtility.getUserList();
        for (Member m : members) {
            if (m.getId() == id) {
                String output = gson.toJson(m);
                MemberAdapter.IS_EXTENSIVE = false;
                return output;
            }
        }
        return USER_DOES_NOT_EXIST_ERROR_CODE;
    }
}
