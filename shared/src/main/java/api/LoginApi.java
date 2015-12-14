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
    /**
     * Gets member information from the user and logs in to database
     * @param memberParam member information containing username and password
     * @return the member as json
     */
    @RequestMapping(value = "/api/login",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String doLogin(@RequestBody Member memberParam) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Member.class, new MemberAdapter()).create();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ArrayList<Member> members = MemberUtility.getUserList();
        for (Member member : members) {
            if (member.getUsername().equals(memberParam.getUsername())) {
                if (passwordEncoder.matches(memberParam.getPassword(), member.getPassword())) {
                    return gson.toJson(member);
                } else {
                    return WRONG_PASSWORD_ERROR_CODE;
                }
            }
        }
        return USER_DOES_NOT_EXIST_ERROR_CODE;
    }
}
