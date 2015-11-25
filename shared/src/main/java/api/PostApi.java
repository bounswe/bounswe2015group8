package api;

import com.google.gson.Gson;
import model.Comment;
import model.Member;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.sql.Timestamp;


/**
 * Created by siray on 24/11/2015.
 */
public class PostApi implements ErrorCodes {
    @Autowired
    private ApplicationContext appContext;
    Gson gson = new Gson();

    @RequestMapping(value = "/api/post_comment",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Comment  post_comment(@RequestBody Post apiPost) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        java.util.Date now = new java.util.Date();
        Member member = apiPost.getOwner();
        String content = apiPost.getContent();
        Comment comment= PostUtility.getCommentService().saveComment(member,apiPost,content,new Timestamp(now.getTime()));
        return comment;
    }




}
