package dao;

import model.Member;
import model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public class PostDaoImpl implements PostDao {

    private SessionFactory sessionFactory;

    public Post getPostById(long id){
        Session s = getSessionFactory().openSession();
        Post post = (Post)s
                .createQuery("from Post where id=?")
                .setParameter(0, id).uniqueResult();
        s.close();
        return post;
    }

    public List<Post> getPostsByOwner(Member owner){
        Session s = getSessionFactory().openSession();
        List<Post> posts = s
                .createQuery("from Post where owner=?")
                .setParameter(0, owner).list();
        s.close();
        return posts;
    }

    public Post savePost(Post post){
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.save(post);
        s.getTransaction().commit();
        s.close();
        return post;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
