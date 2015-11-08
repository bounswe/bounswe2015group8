package dao;

import model.Member;
import model.Post;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public interface PostDao {
    public Post getPostById(long id);
    public List<Post> getPostsByOwner(Member owner);
    public Post savePost(Post post);
    public SessionFactory getSessionFactory();
    public void setSessionFactory(SessionFactory sessionFactory);
}
