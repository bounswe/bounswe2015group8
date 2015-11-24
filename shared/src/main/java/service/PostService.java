package service;

import dao.PostDao;
import dao.PostDaoImpl;
import model.Heritage;
import model.Member;
import model.Post;
import model.Tag;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public class PostService {
    private PostDao postDao;

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

    public Post savePost(Member member, int type, Timestamp timestamp, String title, String content, Heritage heritage) {
        Post post = new Post(member, type, timestamp, title, content);
        return postDao.savePost(post, heritage);
    }
}
