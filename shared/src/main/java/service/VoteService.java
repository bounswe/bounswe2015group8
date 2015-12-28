package service;

import dao.VoteDao;
import dao.VoteDaoImpl;
import model.*;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by gokcan on 16.11.2015.
 */
public class VoteService {
    private VoteDao voteDao;

    public VoteService(SessionFactory sessionFactory){
        voteDao = new VoteDaoImpl();
        voteDao.setSessionFactory(sessionFactory);
    }

    public CommentVote getCommentVoteById(long id){
        return voteDao.getCommentVoteById(id);
    }

    public PostVote getPostVoteById(long id){
        return voteDao.getPostVoteById(id);
    }

    public List<CommentVote> getVotesByComment(Comment comment){
        return voteDao.getVotesByComment(comment);
    }

    public List<PostVote> getVotesByPost(Post post){
        return voteDao.getVotesByPost(post);
    }

    public List<CommentVote> getCommentVotesByMember(Member member){
        return voteDao.getCommentVotesByOwner(member);
    }

    public List<PostVote> getPostVotesByMember(Member member){
        return voteDao.getPostVotesByOwner(member);
    }

    public long getCommentUpvoteCount(Comment comment){
        return voteDao.getCommentUpvoteCount(comment);
    }

    public long getPostUpvoteCount(Post post){
        return voteDao.getPostUpvoteCount(post);
    }

    public long getCommentDownvoteCount(Comment comment){
        return voteDao.getCommentDownvoteCount(comment);
    }

    public long getPostDownvoteCount(Post post){
        return voteDao.getPostDownvoteCount(post);
    }

    public long getCommentOverallVote(Comment comment){
        return getCommentUpvoteCount(comment) - getCommentDownvoteCount(comment);
    }

    public long getPostOverallVote(Post post){
        return getPostUpvoteCount(post) - getPostDownvoteCount(post);
    }

    public CommentVote saveCommentVote(Member member, Comment comment, boolean voteType){
        CommentVote oldCommentVote = voteDao.getVoteByCommentAndOwner(comment, member);
        if(oldCommentVote != null){
            deleteCommentVote(oldCommentVote);
        }
        CommentVote commentVote = new CommentVote(member, comment, voteType);
        return voteDao.saveCommentVote(commentVote);
    }

    public PostVote savePostVote(Member member, Post post, boolean voteType){
        PostVote oldPostVote = voteDao.getVoteByPostAndOwner(post, member);
        if(oldPostVote != null){
            deletePostVote(oldPostVote);
        }
        PostVote postVote = new PostVote(member, post, voteType);
        return voteDao.savePostVote(postVote);
    }

    public void deleteCommentVote(CommentVote commentVote){
        voteDao.deleteCommentVote(commentVote);
    }

    public void deletePostVote(PostVote postVote){
        voteDao.deletePostVote(postVote);
    }

    public long getUpvoteNum(Member member){
        long num = 0;
        for(CommentVote commentVote : getCommentVotesByMember(member)){
            if(commentVote.getVoteType()){
                num++;
            }
        }
        for(PostVote postVote : getPostVotesByMember(member)){
            if(postVote.getVoteType()){
                num++;
            }
        }
        return num;
    }

    public long getDownvoteNum(Member member){
        long num = 0;
        for(CommentVote commentVote : getCommentVotesByMember(member)){
            if(!commentVote.getVoteType()){
                num++;
            }
        }
        for(PostVote postVote : getPostVotesByMember(member)){
            if(!postVote.getVoteType()){
                num++;
            }
        }
        return num;
    }
}
