package api;

import controller.Main;
import model.Heritage;
import model.HeritagePost;
import model.Post;
import service.HeritageService;
import service.PostService;
import service.VoteService;

import java.util.ArrayList;

/**
 * Created by Goktug on 13.11.2015.
 */
public class HeritageUtility {
    private static ArrayList<Heritage> heritageList;
    private static ArrayList<Post> postList;
    private static ArrayList<HeritagePost> heritagePostList;
    static HeritageService heritageService;
    static PostService postService;
    static VoteService voteService;

    /**
     * Creates and/or updates member list.
     *
     * @return the list of member objects in the database.
     */
    public static ArrayList<Heritage> getHeritageList() {
        if (heritageList == null) {
            heritageList = new ArrayList<Heritage>();
        }
        heritageList = (ArrayList<Heritage>) Main.getSession().createCriteria(Heritage.class).list();
        return heritageList;
    }

    public static ArrayList<Post> getPostList() {
        if (postList == null) {
            postList = new ArrayList<Post>();
        }
        if (heritageList == null) {
            heritageList = new ArrayList<Heritage>();
        }
        heritageList = getHeritageList();
        postList.clear();
        for (Heritage h : heritageList) {
            postList.addAll(h.getPosts());
        }

        return postList;
    }

    public static HeritageService getHeritageService() {
        if (heritageService == null) {
            heritageService = new HeritageService(Main.getSessionFactory());
        }
        return heritageService;
    }

    public static PostService getPostService() {
        if (postService == null) {
            postService = new PostService(Main.getSessionFactory());
        }
        return postService;
    }

    public static VoteService getVoteService() {
        if (voteService == null) {
            voteService = new VoteService(Main.getSessionFactory());
        }
        return voteService;
    }

}
