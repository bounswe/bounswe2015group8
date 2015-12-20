package api;

import adapter.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controller.Main;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.FollowHeritageService;
import service.FollowService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Goktug on 07.12.2015.
 */
@RestController
public class FollowApi {
    Gson gson = new Gson();
    FollowService followService = new FollowService(Main.getSessionFactory());
    FollowHeritageService followHeritageService = new FollowHeritageService(Main.getSessionFactory());

    @RequestMapping(value = "/api/follow",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public long follow(@RequestBody String json) {
        HashMap<String, String> jsonFollow = gson.fromJson(json, new TypeToken<HashMap<String, String>>(){}.getType());
        long followerId = Long.parseLong(jsonFollow.get("followerId"));
        long followeeId = Long.parseLong(jsonFollow.get("followeeId"));
        followService.saveFollow(followerId, followeeId);
        return followeeId;
    }

    @RequestMapping(value = "/api/unfollow",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public long unfollow(@RequestBody String json) {
        HashMap<String, String> jsonFollow = gson.fromJson(json, new TypeToken<HashMap<String, String>>(){}.getType());
        Member follower = MemberUtility.getMemberById(Long.parseLong(jsonFollow.get("followerId")));
        Member followee = MemberUtility.getMemberById(Long.parseLong(jsonFollow.get("followeeId")));
        followService.deleteFollow(follower, followee);
        return followee.getId();
    }

    @RequestMapping(value = "/api/followHeritage",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public long followHeritage(@RequestBody String json) {
        HashMap<String, String> jsonFollow = gson.fromJson(json, new TypeToken<HashMap<String, String>>(){}.getType());
        long followerId = Long.parseLong(jsonFollow.get("followerId"));
        long heritageId = Long.parseLong(jsonFollow.get("heritageId"));
        followHeritageService.saveFollowHeritage(followerId, heritageId);
        return heritageId;
    }

    @RequestMapping(value = "/api/unfollowHeritage",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public long unfollowHeritage(@RequestBody String json) {
        HashMap<String, String> jsonFollow = gson.fromJson(json, new TypeToken<HashMap<String, String>>(){}.getType());
        long followerId = Long.parseLong(jsonFollow.get("followerId"));
        long heritageId = Long.parseLong(jsonFollow.get("heritageId"));
        followHeritageService.deleteFollowHeritage(followerId, heritageId);
        return heritageId;
    }

    @RequestMapping(value = "/api/getFollowedPosts",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPostsOfFollowedUsers(@RequestBody long userId) {
        Member member = MemberUtility.getMemberById(userId);
        ArrayList<Post> posts = new ArrayList<>();
        if (member != null) {
            for (Member followed : member.getFollowedMembers()) {
                posts.addAll(followed.getPosts());
            }
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Post.class, new PostAdapter()).create();
        return gson.toJson(posts);
    }

    @RequestMapping(value = "/api/getFollowedComments",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCommentsOfFollowedUsers(@RequestBody long userId) {
        Member member = MemberUtility.getMemberById(userId);
        ArrayList<Comment> comments = new ArrayList<>();
        if (member != null) {
            for (Member followed : member.getFollowedMembers()) {
                comments.addAll(followed.getComments());
            }
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Comment.class, new CommentAdapter()).create();
        return gson.toJson(comments);
    }
}
