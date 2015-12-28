package service;

import dao.CommentDao;
import dao.CommentDaoImpl;
import model.Comment;
import model.Member;
import model.Post;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Goktug on 13.11.2015.
 */
public class CommentService {
    private CommentDao commentDao;

    public CommentService(SessionFactory sessionFactory) {
        commentDao = new CommentDaoImpl();
        commentDao.setSessionFactory(sessionFactory);
    }

    public Comment getCommentById(long id){
        return commentDao.getCommentById(id);
    }

    public List<Comment> getAllComments(){
        return commentDao.getAllComments();
    }

    public List<Comment> getCommentsByMember(Member member){
        return commentDao.getCommentsByOwner(member);
    }

    public List<Comment> getCommentsByPost(Post post){ return commentDao.getCommentsByPost(post); }

    public Comment saveComment(Member member, Post post, String content, Timestamp timestamp){
        Comment comment = new Comment(member, post, content, timestamp);
        return commentDao.saveComment(comment, post);
    }

    public long getCommentNumber(Member member){
        return getCommentsByMember(member).size();
    }

    public SessionFactory getSessionFactory(){
        return commentDao.getSessionFactory();
    }
}
