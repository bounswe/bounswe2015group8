package model;

/**
 * Created by xyllan on 23.10.2015.
 */
public class PostVote {
    private long memberId;
    private long postId;
    private boolean voteType;
    private Member owner;
    private Post post;
    public PostVote() {

    }
    public PostVote(Member owner, Post post, boolean type) {
        this.owner = owner;
        this.post = post;
        this.voteType = type;
    }
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

    public boolean getVoteType() {
        return voteType;
    }

    public void setVoteType(boolean voteType) {
        this.voteType = voteType;
    }

    /*
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
        result = 31 * result + (voteType ? 1 : 0);
        return result;
    }
    */

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
