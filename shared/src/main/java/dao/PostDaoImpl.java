package dao;

import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public class PostDaoImpl implements PostDao {

    private SessionFactory sessionFactory;

    public Post getPostById(long id) {
        Session s = getSessionFactory().openSession();
        Post post = (Post) s
                .createQuery("from Post where id=?")
                .setParameter(0, id).uniqueResult();
        s.close();
        return post;
    }

    public List<Post> getPostsByOwner(Member owner) {
        Session s = getSessionFactory().openSession();
        List<Post> posts = s
                .createQuery("from Post where owner=?")
                .setParameter(0, owner).list();
        return posts;
    }

    public Post savePost(Post post, Heritage heritage) {
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.save(post);

        HeritagePost hp = new HeritagePost();
        hp.setHeritage(heritage);
        hp.setPost(post);
        s.save(hp);

        s.getTransaction().commit();
        s.close();
        return post;
    }

    public List<Post> getPostsByHeritage(Heritage heritage) {
        Session s = getSessionFactory().openSession();
        List<HeritagePost> heritageposts = s
                .createQuery("from HeritagePost where heritage=?")
                .setParameter(0, heritage).list();
        List<Post> posts = new ArrayList<Post>();
        for (int i = 0; i < heritageposts.size(); i++) {
            posts.add(heritageposts.get(i).getPost());
        }
        return posts;
    }

    @SuppressWarnings("unchecked")
    public List<Post> getPostsByTag(Tag tag) {
        Session s = getSessionFactory().openSession();
        List<TagPost> tagposts = s
                .createQuery("from TagPost where tag=?")
                .setParameter(0, tag).list();
        List<Post> posts = new ArrayList<Post>();
        for (int i = 0; i < tagposts.size(); i++) {
            posts.add(tagposts.get(i).getPost());
        }
        return posts;
    }

    @SuppressWarnings("unchecked")
    public List<Post> getPostsCreatedAfter(Timestamp date){
        Session s = getSessionFactory().openSession();
        List<Post> posts = s
                .createQuery("from Post where postDate > :date")
                .setParameter("date", date).list();

        return posts;
    }

    public Post updatePost(Post post){
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.update(post);
        s.getTransaction().commit();
        return post;
    }

    @SuppressWarnings("unchecked")
    public List<Post> getPostsContainTitle(String title){
        Session s = getSessionFactory().openSession();
        List<Post> posts = s
                .createQuery("from Post where title like ?")
                .setString(0, "%"+title+"%").list();
        return posts;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
