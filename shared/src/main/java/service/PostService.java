package service;

import dao.PostDao;
import dao.PostDaoImpl;
import model.Heritage;
import model.Member;
import model.Post;
import model.Tag;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public class PostService {
    private PostDao postDao;
    private Logger logger = Logger.getLogger(PostService.class);

    public PostService(SessionFactory sessionFactory) {
        postDao = new PostDaoImpl();
        postDao.setSessionFactory(sessionFactory);
    }

    public Post getPostById(long id) {
        return postDao.getPostById(id);
    }

    public List<Post> getPostsByMember(Member member) {
        return postDao.getPostsByOwner(member);
    }

    public List<Post> getPostsByHeritage(Heritage heritage) {
        return postDao.getPostsByHeritage(heritage);
    }

    public List<Post> getPostsByTag(Tag tag){ return postDao.getPostsByTag(tag); }

    public List<Post> getPostsContainTitle(String title) { return postDao.getPostsContainTitle(title); }

    public Post savePost(Member member, int type, Timestamp timestamp, String title, String content, Heritage heritage) {
        Post post = new Post(member, type, timestamp, title, content);
        return postDao.savePost(post, heritage);
    }

    public Post savePost(Member member, int type, Timestamp timestamp, String title, String content, String place, Heritage heritage) {
        Post post = new Post(member, type, timestamp, title, content, place);
        return postDao.savePost(post, heritage);
    }
    public Post updatePost(long postId, String newTitle, String newContent, String newPlace, Timestamp lastEditDate){

        Post post = getPostById(postId);
        post.setContent(newContent);
        post.setTitle(newTitle);
        post.setPlace(newPlace);
        post.setLastEditedDate(lastEditDate);
        logger.info("NEW PLACE " + newPlace);
        return postDao.updatePost(post);

    }

    public List<Post> removeDuplicates(List<Post> posts){
        HashSet<Long> postIds = new HashSet<>();
        for(int i = 0; i < posts.size(); i++){
            if(!postIds.contains(posts.get(i).getId())){
                postIds.add(posts.get(i).getId());
            }
            else{
                posts.remove(i);
            }
        }
        return posts;
    }

    public List<Post> sortByPopularity(List<Post> posts){
        VoteService voteService = new VoteService(postDao.getSessionFactory());
        int size = posts.size();
        List<Post> sortedPosts = new ArrayList<>();

        for(int i = 0; i < size; i++){
            long maxVotePoint = -99999;
            Post mostPopular = null;
            for(int j = 0; j < posts.size(); j++){
                Post currentPost = posts.get(j);
                long currentVotePoint = voteService.getPostOverallVote(currentPost);
                if(currentVotePoint > maxVotePoint){
                    mostPopular = currentPost;
                    maxVotePoint = currentVotePoint;
                }
            }
            sortedPosts.add(mostPopular);
            posts.remove(mostPopular);
        }
        return sortedPosts;
    }

    public List<Post> getRecentlyMostPopularPosts(){
        long now = System.currentTimeMillis();
        long nowMinusOneWeek = now - 7L * 24L * 3600L * 1000L; // From last week up until now
        Timestamp nowMinusOneWeekTimestamp = new Timestamp(nowMinusOneWeek);
        logger.info("now  " + new Timestamp(now));
        logger.info("last week " + nowMinusOneWeekTimestamp);
        List<Post> recentPosts = postDao.getPostsCreatedAfter(nowMinusOneWeekTimestamp);
        return sortByPopularity(recentPosts);
    }

    public List<Post> getRecentlyMostPopularPosts(Heritage heritage){
        long now = System.currentTimeMillis();
        long nowMinusOneWeek = now - 7L * 24L * 3600L * 1000L; // From last week up until now
        Timestamp nowMinusOneWeekTimestamp = new Timestamp(nowMinusOneWeek);
        logger.info("now  " + new Timestamp(now));
        logger.info("last week " + nowMinusOneWeekTimestamp);
        List<Post> recentPosts = postDao.getPostsCreatedAfter(nowMinusOneWeekTimestamp, heritage);
        return sortByPopularity(recentPosts);
    }
}
