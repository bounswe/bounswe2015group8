package controller;

import dao.MemberDaoImpl;
import model.Member;
import org.hibernate.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import service.MemberDetailsService;

import java.util.ArrayList;

/**
 * Created by Goktug on 31.10.2015.
 */
@RestController
public class ApiMemberController {
    private final int USER_DOES_NOT_EXIST_ERROR_CODE = -1;
    private final int WRONG_PASSWORD_ERROR_CODE = -2;
    private final int USERNAME_NULL = -11;
    private final int PASSWORD_NULL = -12;
    private final int EMAIL_NULL = -13;

    private ArrayList<Member> memberList;
    final Session session = Main.getSession();
    MemberDetailsService memberService;

    /**
     * Creates and/or updates member list.
     *
     * @return the list of member objects in the database.
     */
    private ArrayList<Member> getUserList() {
        if (memberList == null) {
            memberList = new ArrayList<Member>();
        }
        memberList = (ArrayList<Member>) session.createCriteria(Member.class).list();
        return memberList;
    }

    /**
     * Login attempt via API. Gets username and password from the URL and checks
     * if there is such member in the database.
     *
     * @param username username of the member
     * @param password passowrd of the member
     * @return member id if the login attempt is successful; returns error codes otherwise.
     */
    @RequestMapping(value = "/api/login/{username}/{password}",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public long doLogin(@PathVariable("username") String username, @PathVariable("password") String password) {
        ArrayList<Member> members = getUserList();
        for (Member member : members) {
            if (member.getUsername().equals(username)) {
                if (member.getPassword().equals(password)) {
                    return member.getId();
                } else {
                    return WRONG_PASSWORD_ERROR_CODE;
                }
            }
        }
        return USER_DOES_NOT_EXIST_ERROR_CODE;
    }

    /**
     * Creates member via API. Gets details with RequestMethod Post.
     * @param apiMember ApiMemberModel as JSON
     * @return the member id
     */
    @RequestMapping(value = "/api/signup",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public long doSignUp(@RequestBody ApiMemberModel apiMember) {
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
        memberService = new MemberDetailsService();
        MemberDaoImpl mdao = new MemberDaoImpl();
        mdao.setSessionFactory(Main.getSessionFactory());
        memberService.setMemberDao(mdao);
        Member m = memberService.createMember(username,password,email,"");
        return m.getId();
    }

}
