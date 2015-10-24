package model;

import java.io.Serializable;

/**
 * Created by xyllan on 22.10.2015.
 */
public class CommentVotePK implements Serializable {
    private long ownerId;
    private long commentId;
    private Member owner;
    private Comment comment;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentVotePK that = (CommentVotePK) o;

        if (ownerId != that.ownerId) return false;
        if (commentId != that.commentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ownerId ^ (ownerId >>> 32));
        result = 31 * result + (int) (commentId ^ (commentId >>> 32));
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
