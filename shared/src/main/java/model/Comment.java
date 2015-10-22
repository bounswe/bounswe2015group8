package model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xyllan on 22.10.2015.
 */
public class Comment {
    private long id;
    private Member owner;
    private Post post;
    private String content;
    private Timestamp postDate;
    private Timestamp lastEditedDate;
    private Set<CommentVote> votes;
    public Comment() {
        votes = new HashSet<CommentVote>();
    }
    public Comment(Member owner, Post post, String content, Timestamp postDate
                   ) {
        this.owner = owner;
        this.post = post;
        this.content = content;
        this.postDate = postDate;
        this.votes = new HashSet<CommentVote>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }

    public Timestamp getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(Timestamp lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    public Set<CommentVote> getVotes() { return votes; }

    public void setVotes(Set<CommentVote> votes) { this.votes = votes; }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (owner != comment.owner) return false;
        if (post != comment.post) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (postDate != null ? !postDate.equals(comment.postDate) : comment.postDate != null) return false;
        if (lastEditedDate != null ? !lastEditedDate.equals(comment.lastEditedDate) : comment.lastEditedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (lastEditedDate != null ? lastEditedDate.hashCode() : 0);
        return result;
    }
}
