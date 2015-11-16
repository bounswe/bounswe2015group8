package api;

import model.Member;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Goktug on 31.10.2015.
 */
@RestController
public class SignUpApi implements ErrorCodes {


    /**
     * Creates member via API. Gets details with RequestMethod Post.
     * @param apiMember ApiMemberModel as JSON
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
        Member m = MemberUtility.getMemberService().createMember(username,password,email,"");
        return m.getId();
    }

}
