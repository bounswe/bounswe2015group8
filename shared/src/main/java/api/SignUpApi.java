package api;

import model.Member;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Goktug on 31.10.2015.
 */
@RestController
public class SignUpApi implements ErrorCodes {


    /**
     * Creates member via API. Gets details with RequestMethod Post.
     * @param apiMember Member as JSON
     * @return the member id
     */
    @RequestMapping(value = "/api/signup",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public long doSignUp(@RequestBody Member apiMember) {
        String username = apiMember.getUsername();
        String password = apiMember.getPassword();
        String email = apiMember.getEmail();
        if (username == null) {
            return USERNAME_NULL;
        }
        if (password == null) {
            return PASSWORD_NULL;
        }
        if (email == null) {
            return EMAIL_NULL;
        }
        if (usernameExists(username)) {
            return USERNAME_ALREADY_EXISTS;
        }
        if (emailExists(email)) {
            return EMAIL_ALREADY_EXISTS;
        }
        Member member = MemberUtility.getMemberService().createMember(username,password,email,"");
        return member.getId();
    }

    public boolean usernameExists(String username) {
        ArrayList<Member> members = MemberUtility.getUserList();
        for (Member member : members) {
            if (member.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean emailExists(String email) {
        ArrayList<Member> members = MemberUtility.getUserList();
        for (Member member : members) {
            if (member.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

}
