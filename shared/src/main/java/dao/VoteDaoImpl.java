package dao;

import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by gokcan on 16.11.2015.
 */
public class VoteDaoImpl implements VoteDao{
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<CommentVote> getVotesByComment(Comment comment){
        Session s = getSessionFactory().openSession();
        List<CommentVote> commentVotes = s
                .createQuery("from CommentVote where comment=?")
                .setParameter(0, comment).list();
        s.close();
        return commentVotes;
    }

    @SuppressWarnings("unchecked")
    public List<PostVote> getVotesByPost(Post post){
        Session s = getSessionFactory().openSession();
        List<PostVote> postVotes = s
                .createQuery("from PostVote where post=?")
                .setParameter(0, post).list();
        s.close();
        return postVotes;
    }

    public CommentVote getCommentVoteById(long id){
        Session s = getSessionFactory().openSession();
        CommentVote commentVote = (CommentVote)s
                .createQuery("from CommentVote where id=?")
                .setParameter(0, id).uniqueResult();
        s.close();
        return commentVote;
    }

    public PostVote getPostVoteById(long id){
        Session s = getSessionFactory().openSession();
        PostVote postVote = (PostVote)s
                .createQuery("from PostVote where id=?")
                .setParameter(0, id).uniqueResult();
        s.close();
        return postVote;
    }

    @SuppressWarnings("unchecked")
    public List<CommentVote> getCommentVotesByOwner(Member owner){
        Session s = getSessionFactory().openSession();
        List<CommentVote> commentVotes = s
                .createQuery("from CommentVote where owner=?")
                .setParameter(0, owner).list();
        s.close();
        return commentVotes;
    }

    @SuppressWarnings("unchecked")
    public List<PostVote> getPostVotesByOwner(Member owner){
        Session s = getSessionFactory().openSession();
        List<PostVote> postVotes = s
                .createQuery("from PostVote where owner=?")
                .setParameter(0, owner).list();
        s.close();
        return postVotes;
    }

    public CommentVote getVoteByCommentAndOwner(Comment comment, Member owner){
        Session s = getSessionFactory().openSession();
        CommentVote commentVote = (CommentVote)s
                .createQuery("from CommentVote where comment=? and owner=?")
                .setParameter(0, comment)
                .setParameter(1, owner).uniqueResult();
        s.close();
        return commentVote;
    }

    public PostVote getVoteByPostAndOwner(Post post, Member owner){
        Session s = getSessionFactory().openSession();
        PostVote postVote = (PostVote)s
                .createQuery("from PostVote where post=? and owner=?")
                .setParameter(0, post)
                .setParameter(1, owner).uniqueResult();
        s.close();
        return postVote;
    }

    public CommentVote saveCommentVote(CommentVote commentVote){
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.save(commentVote);
        s.getTransaction().commit();
        s.close();
        return commentVote;
    }

    public PostVote savePostVote(PostVote postVote){
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.save(postVote);
        s.getTransaction().commit();
        s.close();
        return postVote;
    }

    public void deleteCommentVote(CommentVote commentVote){
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.delete(commentVote);
        s.getTransaction().commit();
        s.close();
    }

    public void deletePostVote(PostVote postVote){
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.delete(postVote);
        s.getTransaction().commit();
        s.close();
    }

    public long getCommentUpvoteCount(Comment comment){
        Session s = getSessionFactory().openSession();
        long upvoteCount = (long)s
                .createQuery("from CommentVote where comment=? and voteType=true")
                .setParameter(0, comment).list().size();
        s.close();
        return upvoteCount;
    }

    public long getPostUpvoteCount(Post post){
        Session s = getSessionFactory().openSession();
        long upvoteCount = (long)s
                .createQuery("from PostVote where post=? and voteType=true")
                .setParameter(0, post).list().size();
        s.close();
        return upvoteCount;
    }

    public long getCommentDownvoteCount(Comment comment){
        Session s = getSessionFactory().openSession();
        long downvoteCount = (long)s
                .createQuery("from CommentVote where comment=? and voteType=false")
                .setParameter(0, comment).list().size();
        s.close();
        return downvoteCount;
    }

    public long getPostDownvoteCount(Post post){
        Session s = getSessionFactory().openSession();
        long downvoteCount = (long)s
                .createQuery("from PostVote where post=? and voteType=false")
                .setParameter(0, post).list().size();
        s.close();
        return downvoteCount;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
