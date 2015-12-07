package api;

import adapter.MemberAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.Main;
import model.Follow;
import model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.FollowService;

/**
 * Created by Goktug on 07.12.2015.
 */
@RestController
public class FollowApi {
    @Autowired
    private ApplicationContext appContext;
    FollowService followService = new FollowService(Main.getSessionFactory());

    @RequestMapping(value = "/api/follow",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String followApi(@RequestBody String followString) {
        long followerId = Long.parseLong(followString.substring(0, followString.lastIndexOf('-')));
        long followeeId = Long.parseLong(followString.substring(followString.lastIndexOf('-') + 1));
        Follow follow = followService.saveFollow(followerId, followeeId);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Member.class, new MemberAdapter()).create();
        return gson.toJson(follow.getFollowee());
    }
}
