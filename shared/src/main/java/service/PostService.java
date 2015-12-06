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
}
