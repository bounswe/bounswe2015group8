package model;

import java.io.Serializable;

/**
 * Created by xyllan on 22.10.2015.
 */
public class CommentVotePK implements Serializable {
    private long memberId;
    private long commentId;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
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

        if (memberId != that.memberId) return false;
        if (commentId != that.commentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (memberId ^ (memberId >>> 32));
        result = 31 * result + (int) (commentId ^ (commentId >>> 32));
        return result;
    }
}
