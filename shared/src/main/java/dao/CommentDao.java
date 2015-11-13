package dao;

import model.Comment;
import model.Member;
import model.Post;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Goktug on 13.11.2015.
 */
public interface CommentDao {
    public Comment getCommentById(long id);

    public List<Comment> getCommentsByOwner(Member owner);

    public List<Comment> getCommentsByPost(Post post);

    public List<Comment> getAllComments();

    public Comment saveComment(Comment comment, Post post);

    public SessionFactory getSessionFactory();

    public void setSessionFactory(SessionFactory sessionFactory);
}
