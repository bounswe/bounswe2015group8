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
     * Searches for the heritages with names that contain the parameter
     * @param name the search string
     * @return the heritages with names containing parameter
     */
    public static ArrayList<Post> searchByPostTitle(String name) {
        postList = getPostList();
        ArrayList<Post> postsWithName= new ArrayList<>();
        name = name.toLowerCase();
        for (Post post : postList) {
            if (post.getTitle().toLowerCase().contains(name)) {
                postsWithName.add(post);
            }
        }
        return postsWithName;
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
