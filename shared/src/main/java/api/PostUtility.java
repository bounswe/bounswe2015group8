package api;

import controller.Main;
import model.Heritage;
import model.Post;
import service.PostService;

import java.util.ArrayList;

/**
 * Created by Goktug on 14.12.2015.
 */
public class PostUtility {
    private static ArrayList<Post> postList;
    static PostService postService;

    /**
     * Gets post list
     * @return all posts in the database
     */
    public static ArrayList<Post> getPostList() {
        if (postList == null) {
            postList = new ArrayList<Post>();
        }
        postList.clear();
        for (Heritage h : HeritageUtility.getHeritageList()) {
            postList.addAll(h.getPosts());
        }

        return postList;
    }

    /**
     * Find post with the given id and return it
     * @param id of the post
     * @return post with given id
     */
    public static Post getPostById(long id) {
        postList = getPostList();
        for (Post post : postList) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    /**
     * Returns post service
     * @return post service
     */
    public static PostService getPostService() {
        if (postService == null) {
            postService = new PostService(Main.getSessionFactory());
        }
        return postService;
    }
}
