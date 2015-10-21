package model;

import java.sql.Timestamp;

/**
 * Created by xyllan on 21.10.2015.
 */
public class Comment {
    private long id;
    private String content;
    private Timestamp postDate;
    private Timestamp lastEditedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (postDate != null ? !postDate.equals(comment.postDate) : comment.postDate != null) return false;
        if (lastEditedDate != null ? !lastEditedDate.equals(comment.lastEditedDate) : comment.lastEditedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (lastEditedDate != null ? lastEditedDate.hashCode() : 0);
        return result;
    }
}
