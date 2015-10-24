package model;

import java.io.Serializable;

/**
 * Created by xyllan on 23.10.2015.
 */
public class PostVotePK implements Serializable {
    private long ownerId;
    private long postId;
    private Member owner;
    private Post post;

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
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

        if (ownerId != that.ownerId) return false;
        if (postId != that.postId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ownerId ^ (ownerId >>> 32));
        result = 31 * result + (int) (postId ^ (postId >>> 32));
        return result;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
