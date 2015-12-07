package api;

import adapter.CommentAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import model.Comment;
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
import java.util.HashMap;
import java.util.List;


/**
 * Created by siray on 24/11/2015.
 */
@RestController
public class CommentApi implements ErrorCodes {
    @Autowired
    private ApplicationContext appContext;
    Gson gson = new Gson();

    @RequestMapping(value = "/api/postComment",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String postComment(@RequestBody String json) {
        HashMap<String, String> jsonComment = gson.fromJson(json, new TypeToken<HashMap<String, String>>(){}.getType());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        java.util.Date now = new java.util.Date();
        Member member = MemberUtility.getMemberService().getMemberById(Long.parseLong(jsonComment.get("ownerId")));
        Post post = HeritageUtility.getPostService().getPostById(Long.parseLong(jsonComment.get("postId")));
        String content = jsonComment.get("content");
        Comment comment= CommentUtility.getCommentService().saveComment(member, post, content, new Timestamp(now.getTime()));

        JsonObject output = new JsonObject();
        output.addProperty("id", comment.getId());
        output.addProperty("postDate", comment.getPostDate().toString());
        return gson.toJson(output);
    }

    @RequestMapping(value = "/api/getAllComments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllComments(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Comment.class, new CommentAdapter()).create();
        ArrayList<Comment> comments = CommentUtility.getCommentList();
        String json = gson.toJson(comments);
        return json;
    }

    @RequestMapping(value = "/api/getCommentsByPostId/{postId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCommentsByPostId(@PathVariable long postId){
        Post post = HeritageUtility.getPostService().getPostById(postId);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Comment.class, new CommentAdapter()).create();
        List<Comment> comments = CommentUtility.getCommentService().getCommentsByPost(post);
        String json = gson.toJson(comments);
        return json;
    }

    @RequestMapping(value = "/api/getCommentsByMemberId/{memberId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCommentsByMemberId(@PathVariable long memberId){
        Member member = MemberUtility.getMemberService().getMemberById(memberId);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Comment.class, new CommentAdapter()).create();
        List<Comment> comments = CommentUtility.getCommentService().getCommentsByMember(member);
        String json = gson.toJson(comments);
        return json;
    }

    @RequestMapping(value = "/api/voteComment",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public long voteComment(@RequestBody String json){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        HashMap<String, String> jsonComment = gson.fromJson(json, new TypeToken<HashMap<String, String>>(){}.getType());
        Member member = MemberUtility.getMemberService().getMemberById(Long.parseLong(jsonComment.get("ownerId")));
        Comment comment = CommentUtility.getCommentService().getCommentById(Long.parseLong(jsonComment.get("commentId")));
        boolean voteType = Boolean.parseBoolean(jsonComment.get("voteType"));

        CommentUtility.getVoteService().saveCommentVote(member, comment, voteType);
        return CommentUtility.getVoteService().getCommentOverallVote(comment);
    }

    @RequestMapping(value = "/api/getOverallCommentVoteById/{commentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public long getOverallCommentVoteById(@PathVariable long commentId){
        Comment comment = CommentUtility.getCommentService().getCommentById(commentId);
        return CommentUtility.getVoteService().getCommentOverallVote(comment);
    }

}
