package api;

import controller.Main;
import model.Comment;
import service.CommentService;
import service.VoteService;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by siray on 25/11/2015.
 */
public class CommentUtility {
    private static ArrayList<Comment> commentList;
    static CommentService commentService;
    static VoteService voteService;

    public static ArrayList<Comment> getCommentList() {
        if (commentList == null) {
            commentList = new ArrayList<Comment>();
        }
        commentList = (ArrayList<Comment>)getCommentService().getAllComments();
        return commentList;
    }

    public static CommentService getCommentService() {
        if (commentService == null) {
            commentService = new CommentService(Main.getSessionFactory());
        }
        return commentService;
    }

    public static VoteService getVoteService() {
        if (voteService == null) {
            voteService = new VoteService(Main.getSessionFactory());
        }
        return voteService;
    }

}
