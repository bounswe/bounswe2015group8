package model;

/**
 * Created by xyllan on 22.10.2015.
 */
public class CommentVote {
    private long memberId;
    private long commentId;
    private byte voteType;

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

    public byte getVoteType() {
        return voteType;
    }

    public void setVoteType(byte voteType) {
        this.voteType = voteType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentVote that = (CommentVote) o;

        if (memberId != that.memberId) return false;
        if (commentId != that.commentId) return false;
        if (voteType != that.voteType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (memberId ^ (memberId >>> 32));
        result = 31 * result + (int) (commentId ^ (commentId >>> 32));
        result = 31 * result + (int) voteType;
        return result;
    }
}
