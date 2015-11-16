package dao;

import model.Comment;
import model.Member;
import model.Post;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Goktug on 13.11.2015.
 */
public class CommentDaoImpl implements CommentDao {
    private SessionFactory sessionFactory;

    public Comment getCommentById(long id) {
        Session s = getSessionFactory().openSession();
        Comment comment = (Comment) s
                .createQuery("from Comment where id=?")
                .setParameter(0, id).uniqueResult();
        s.close();
        return comment;
    }

    public List<Comment> getCommentsByOwner(Member owner) {
        Session s = getSessionFactory().openSession();
        List<Comment> comments = s
                .createQuery("from Comment where owner=?")
                .setParameter(0, owner).list();
        s.close();
        return comments;
    }

    public List<Comment> getCommentsByPost(Post post) {
        Session s = getSessionFactory().openSession();
        List<Comment> postComments = s
                .createQuery("from Comment where post=?")
                .setParameter(0, post).list();
        return postComments;
    }

    public List<Comment> getAllComments() {
        Session s = getSessionFactory().openSession();
        List<Comment> comments = s.createQuery("from Comment").list();
        s.close();
        return comments;
    }
    
    public Comment saveComment(Comment comment, Post post) {
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        comment.setPost(post);
        s.save(comment);
        s.getTransaction().commit();
        s.close();
        return comment;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
