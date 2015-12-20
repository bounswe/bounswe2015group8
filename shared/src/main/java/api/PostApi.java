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

    /**
     * Get the post by given ID
     * @param id of the post
     * @return json representation of specific post
     */
    @RequestMapping(value = "/api/getPostById",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPostById(@RequestBody int id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Post.class, new PostAdapter()).create();
        Post post = PostUtility.getPostById(id);
        if (post != null) {
            return gson.toJson(post);
        }
        return Integer.toString(POST_DOES_NOT_EXIST);
    }

    /**
     * Creates a new post by taking information from user
     * @param json representation of user's post
     * @return created post's id
     */
    @RequestMapping(value="/api/createPost",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public long createPost(@RequestBody String json) {
        PostApiModel apiModel = gson.fromJson(json, PostApiModel.class);

        Date now = new Date();

        Member member = MemberUtility.getMemberService().getMemberById(apiModel.getOwnerId());
        Heritage heritage = HeritageUtility.getHeritageService().getHeritageById(apiModel.getHeritageId());

        Post post = PostUtility.getPostService().savePost(member, 0, new Timestamp(now.getTime()), apiModel.getTitle(), apiModel.getContent(), heritage);

        return post.getId();
    }

    /**
     * Votes post by getting information from user
     * @param json comment representation
     * @return new vote count of post
     */
    @RequestMapping(value = "/api/votePost",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public long votePost(@RequestBody String json){
        HashMap<String, String> jsonComment = gson.fromJson(json, new TypeToken<HashMap<String, String>>(){}.getType());
        Member member = MemberUtility.getMemberService().getMemberById(Long.parseLong(jsonComment.get("ownerId")));
        Post post = PostUtility.getPostService().getPostById(Long.parseLong(jsonComment.get("postId")));
        boolean voteType = Boolean.parseBoolean(jsonComment.get("voteType"));

        HeritageUtility.getVoteService().savePostVote(member, post, voteType);
        return HeritageUtility.getVoteService().getPostOverallVote(post);
    }

    /**
     * Gets a post as parameter, adds new heritages to the current post in the database
     * @param post with new heritage list
     * @return updated post in the database
     */
    @RequestMapping(value = "/api/addHeritagesToPost",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addHeritagestoPost(@RequestBody Post post) {
        Post currentPost = PostUtility.getPostById(post.getId());
        if (currentPost == null) {
            return "Post does not exist.";
        }
        ArrayList<Long> heritageIds = currentPost.getHeritageIds();
        for (Heritage heritage : post.getHeritages()) {
            if (!heritageIds.contains(heritage.getId())) {
                currentPost.getHeritages().add(heritage);
            }
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Post.class, new PostAdapter()).create();
        return gson.toJson(currentPost);
    }

    /**
     * Get vote count of given post
     * @param postId id of post
     * @return vote count of the post
     */
    @RequestMapping(value = "/api/getOverallPostVoteById/{postId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public long getOverallPostVoteById(@PathVariable long postId){
        Post post = PostUtility.getPostService().getPostById(postId);
        return HeritageUtility.getVoteService().getPostOverallVote(post);
    }

    /**
     * Returns all posts in the database
     * @return json representation of all posts
     */
    @RequestMapping(value = "/api/getAllPosts",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllPosts() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Post.class, new PostAdapter()).create();
        ArrayList<Post> posts = PostUtility.getPostList();
        return gson.toJson(posts);
    }
}
