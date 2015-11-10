package dao;

import model.Heritage;
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
    public List<Post> getPostsByHeritage(Heritage heritage);
    public Post savePost(Post post, Heritage heritage);
    public SessionFactory getSessionFactory();
    public void setSessionFactory(SessionFactory sessionFactory);
}
