package dao;

import model.*;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by gokcan on 16.11.2015.
 */
public interface VoteDao {
    public List<CommentVote> getVotesByComment(Comment comment);
    public List<PostVote> getVotesByPost(Post post);
    public CommentVote getCommentVoteById(long id);
    public PostVote getPostVoteById(long id);
    public List<CommentVote> getCommentVotesByOwner(Member owner);
    public List<PostVote> getPostVotesByOwner(Member owner);
    public CommentVote getVoteByCommentAndOwner(Comment comment, Member owner);
    public PostVote getVoteByPostAndOwner(Post post, Member owner);

    public CommentVote saveCommentVote(CommentVote commentVote);
    public PostVote savePostVote(PostVote postVote);
    public void deleteCommentVote(CommentVote commentVote);
    public void deletePostVote(PostVote postVote);

    public SessionFactory getSessionFactory();
    public void setSessionFactory(SessionFactory sessionFactory);
}
