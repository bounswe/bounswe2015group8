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
    Post getPostById(long id);

    List<Post> getPostsByOwner(Member owner);

    List<Post> getPostsByHeritage(Heritage heritage);

    Post savePost(Post post, Heritage heritage);

    Post updatePost(Post post, Heritage heritage);

    SessionFactory getSessionFactory();

    void setSessionFactory(SessionFactory sessionFactory);
}
