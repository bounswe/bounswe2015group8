package api;

import adapter.MemberAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Goktug on 07.12.2015.
 */
@RestController
public class MemberApi {
    @Autowired
    private ApplicationContext appContext;

    @RequestMapping(value = "/api/getMemberById",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getById(@RequestBody long id) {
        ArrayList<Member> memberList = MemberUtility.getUserList();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Member.class, new MemberAdapter()).create();
        for (Member member : memberList) {
            if (member.getId() == id) {
                return gson.toJson(member);
            }
        }
        return Integer.toString(-1);
    }
}
