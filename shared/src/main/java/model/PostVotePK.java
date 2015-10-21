package model;

import java.io.Serializable;

/**
 * Created by xyllan on 22.10.2015.
 */
public class PostVotePK implements Serializable {
    private long memberId;
    private long postId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostVotePK that = (PostVotePK) o;

        if (memberId != that.memberId) return false;
        if (postId != that.postId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (memberId ^ (memberId >>> 32));
        result = 31 * result + (int) (postId ^ (postId >>> 32));
        return result;
    }
}
