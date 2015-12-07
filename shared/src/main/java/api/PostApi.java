package api;

import adapter.PostAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import model.Heritage;
import model.Member;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Goktug on 07.12.2015.
 */
@RestController
public class PostApi implements ErrorCodes {
    @Autowired
    private ApplicationContext appContext;
    Gson gson = new Gson();

    @RequestMapping(value = "/api/getPostById",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPostById(@RequestBody int id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Post.class, new PostAdapter()).create();
        ArrayList<Post> posts = HeritageUtility.getPostList();
        for (Post p : posts) {
            if (p.getId() == id) {
                return gson.toJson(p);
            }
        }
        return Integer.toString(POST_DOES_NOT_EXIST);
    }

    @RequestMapping(value="/api/createPost",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public long createPost(@RequestBody String json) {
        PostApiModel apiModel = gson.fromJson(json, PostApiModel.class);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Date now = new Date();

        Member m = MemberUtility.getMemberService().getMemberById(apiModel.getOwnerId());
        Heritage heritage = HeritageUtility.getHeritageService().getHeritageById(apiModel.getHeritageId());

        Post post = HeritageUtility.getPostService().savePost(m, 0, new Timestamp(now.getTime()), apiModel.getTitle(), apiModel.getContent(), heritage);

        return post.getId();
    }

    @RequestMapping(value = "/api/votePost",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public long votePost(@RequestBody String json){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        HashMap<String, String> jsonComment = gson.fromJson(json, new TypeToken<HashMap<String, String>>(){}.getType());
        Member member = MemberUtility.getMemberService().getMemberById(Long.parseLong(jsonComment.get("ownerId")));
        Post post = HeritageUtility.getPostService().getPostById(Long.parseLong(jsonComment.get("postId")));
        boolean voteType = Boolean.parseBoolean(jsonComment.get("voteType"));

        HeritageUtility.getVoteService().savePostVote(member, post, voteType);
        return HeritageUtility.getVoteService().getPostOverallVote(post);
    }

    @RequestMapping(value = "/api/getOverallPostVoteById/{postId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public long getOverallPostVoteById(@PathVariable long postId){
        Post post = HeritageUtility.getPostService().getPostById(postId);
        return HeritageUtility.getVoteService().getPostOverallVote(post);
    }

    @RequestMapping(value = "/api/getAllPosts",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllPosts() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Post.class, new PostAdapter()).create();
        ArrayList<Post> posts = HeritageUtility.getPostList();
        return gson.toJson(posts);
    }
}
