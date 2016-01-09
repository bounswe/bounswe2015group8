package com.cmpe.bounswe2015group8.westory.model;


/**
 * Base object for storing a member's vote on a comment.
 * @see Member
 * @see Comment
 * @author xyllan
 * Date: 07.11.2015
 */
public class CommentVote {
    private long ownerId;
    private long commentId;
    private boolean voteType;
    private Member owner;
    private Comment comment;
    public CommentVote() {

    }
    public CommentVote(Member owner, Comment comment, boolean type) {
        this.owner = owner;
        this.comment = comment;
        this.voteType = type;
    }
    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public boolean getVoteType() {
        return voteType;
    }

    public void setVoteType(boolean voteType) {
        this.voteType = voteType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentVote that = (CommentVote) o;

        if (ownerId != that.ownerId) return false;
        if (commentId != that.commentId) return false;
        if (voteType != that.voteType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ownerId ^ (ownerId >>> 32));
        result = 31 * result + (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + (voteType ? 1 : 0);
        return result;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
