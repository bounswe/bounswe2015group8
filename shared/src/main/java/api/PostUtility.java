package api;

import controller.Main;
import service.CommentService;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Created by siray on 25/11/2015.
 */
public class PostUtility {
    static CommentService commentService;

    public static CommentService getCommentService() {
        if (commentService == null) {
            commentService = new CommentService(Main.getSessionFactory());
        }
        return commentService;
    }

}
