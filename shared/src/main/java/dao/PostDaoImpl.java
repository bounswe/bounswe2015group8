package dao;

import controller.Main;
import model.*;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public class PostDaoImpl implements PostDao {
    private Logger logger = Logger.getLogger(PostDaoImpl.class);
    private SessionFactory sessionFactory;

    public Post getPostById(long id) {
        Session s = getSessionFactory().openSession();
        Post post = (Post) s
                .createQuery("from Post where id=?")
                .setParameter(0, id).uniqueResult();
        post = unproxyPost(post);
        s.close();
        return post;
    }

    @SuppressWarnings("unchecked")
    public List<Post> getPostsByOwner(Member owner) {
        Session s = getSessionFactory().openSession();
        List<Post> posts = s
                .createQuery("from Post where owner=?")
                .setParameter(0, owner).list();
        posts = unproxyPostList(posts);
        s.close();
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

    public HeritagePost linkPostWithHeritage(Post post, Heritage heritage){
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();

        HeritagePost hp = new HeritagePost();
        hp.setHeritage(heritage);
        hp.setPost(post);
        s.save(hp);

        s.getTransaction().commit();
        s.close();
        return hp;
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
        posts = unproxyPostList(posts);
        s.close();
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
        posts = unproxyPostList(posts);
        s.close();
        return posts;
    }

    @SuppressWarnings("unchecked")
    public List<Post> getPostsCreatedAfter(Timestamp date){
        Session s = getSessionFactory().openSession();
        List<Post> posts = s
                .createQuery("from Post where postDate >= :date")
                .setParameter("date", date).list();
        posts = unproxyPostList(posts);
        s.close();
        return posts;
    }

    @SuppressWarnings("unchecked")
    public List<Post> getPostsCreatedAfter(Timestamp date, Heritage heritage){
        Session s = getSessionFactory().openSession();
        List<BigInteger> postIds = s
                .createSQLQuery("select POST_ID from HERITAGE_POST where HERITAGE_ID=?")
                .setParameter(0, heritage.getId()).list();
        List<Long> postIdsLong = new ArrayList<>();
        for(BigInteger postId : postIds){
            postIdsLong.add(postId.longValue());
        }
        List<Post> posts = s
                .createQuery("from Post where postDate >= :date and id in :ids")
                .setParameter("date", date)
                .setParameterList("ids", postIdsLong).list();
        posts = unproxyPostList(posts);
        s.close();
        return posts;
    }

    @SuppressWarnings("unchecked")
    public List<Post> getPostsCreatedBefore(Timestamp date){
        Session s = getSessionFactory().openSession();
        List<Post> posts = s
                .createQuery("from Post where postDate <= :date")
                .setParameter("date", date).list();
        s.close();
        return posts;
    }

    @SuppressWarnings("unchecked")
    public List<Post> getPostsEditedAfter(Timestamp date){
        Session s = getSessionFactory().openSession();
        List<Post> posts = s
                .createQuery("from Post where lastEditedDate >= :date")
                .setParameter("date", date).list();
        s.close();
        return posts;
    }

    @SuppressWarnings("unchecked")
    public List<Post> getPostsEditedBefore(Timestamp date){
        Session s = getSessionFactory().openSession();
        List<Post> posts = s
                .createQuery("from Post where lastEditedDate <= :date")
                .setParameter("date", date).list();
        s.close();
        return posts;
    }

    public Post updatePost(Post post){
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.update(post);
        s.getTransaction().commit();
        s.close();
        return post;
    }

    @SuppressWarnings("unchecked")
    public List<Post> getPostsContainTitle(String title){
        Session s = getSessionFactory().openSession();
        List<Post> posts = s
                .createQuery("from Post where title like ?")
                .setString(0, "%"+title+"%").list();
        s.close();
        return posts;
    }

    @SuppressWarnings("unchecked")
    public List<Post> getPostsContainContent(String content){
        Session s = getSessionFactory().openSession();
        List<Post> posts = s
                .createQuery("from Post where content like ?")
                .setString(0, "%"+content+"%").list();
        s.close();
        return posts;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Post> unproxyPostList(List<Post> posts){
        for(int i = 0; i < posts.size(); i++){
            Post post = posts.get(i);
            posts.set(i, unproxyPost(post));
        }
        return posts;
    }

    public Post unproxyPost(Post post){
        Hibernate.initialize(post);
        Hibernate.initialize(post.getOwner());
        Hibernate.initialize(post.getComments());
        for(Comment comment : post.getComments()){
            Hibernate.initialize(comment);
            Hibernate.initialize(comment.getOwner());
        }
        Hibernate.initialize(post.getVotes());
        for(PostVote postVote : post.getVotes()){
            Hibernate.initialize(postVote);
            Hibernate.initialize(postVote.getOwner());
        }
        Hibernate.initialize(post.getTags());
        post = Main.initializeAndUnproxy(post);
        if(post instanceof HibernateProxy){
            logger.info("yok artik ama...");
        }
        return post;
    }
}
