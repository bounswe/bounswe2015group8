package model;

/**
 * Created by xyllan on 22.10.2015.
 */
public class PostVote {
    private long memberId;
    private long postId;
    private byte voteType;

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
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

        PostVote postVote = (PostVote) o;

        if (memberId != postVote.memberId) return false;
        if (postId != postVote.postId) return false;
        if (voteType != postVote.voteType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (memberId ^ (memberId >>> 32));
        result = 31 * result + (int) (postId ^ (postId >>> 32));
        result = 31 * result + (int) voteType;
        return result;
    }
}
