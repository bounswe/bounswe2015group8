package api;

import controller.Main;
import controller.SearchController;
import model.Heritage;
import model.Post;
import model.Tag;
import service.PostService;
import service.TagService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Goktug on 14.12.2015.
 */
public class PostUtility {
    private static ArrayList<Post> postList;
    static PostService postService;
    static TagService tagService;

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

    public static TagService getTagService() {
        if (tagService == null) {
            tagService = new TagService(Main.getSessionFactory());
        }
        return tagService;
    }

    public static ArrayList<Post> getSemanticallyRelatedPosts(String wholetag){
        if(tagService == null){
            tagService = new TagService(Main.getSessionFactory());
        }
        String[] tagPieces = tagService.extractTextAndContext(wholetag);
        Tag tag = tagService.getTagByText(tagPieces[0], tagPieces[1]);
        List<Post> posts;
        if(tag != null)
            posts = getPostService().getPostsByTag(tag);
        else
            posts = new ArrayList<>();

        // Check whether there are enough results
        if(posts.size() < SearchApi.MIN_LIMIT){
            List<Tag> additionalTags = tagService.sortByCount(tagService.getSemanticallyRelatedTags(tagPieces[0], tagPieces[1]));
            for(Tag additionalTag : additionalTags){
                List<Post> additionalPosts = getPostService().getPostsByTag(additionalTag);
                for(int i = 0; i < additionalPosts.size(); i++){
                    posts.addAll(additionalPosts);
                }
                if(posts.size() >= SearchApi.MIN_LIMIT)
                    break;
            }
        }

        return (ArrayList<Post>)getPostService().removeDuplicates(posts);
    }
}
