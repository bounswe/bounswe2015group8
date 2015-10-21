package model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

enum PostType {}
/**
 * Created by xyllan on 21.10.2015.
 */
public class Post {
    private long id;
    private Member owner;
    private PostType type;
    private Timestamp postDate;
    private Timestamp lastEditedDate;
    private String title;
    private String content;
    private Set<Heritage> heritages;

    public Post() {
        heritages = new HashSet<Heritage>();
    }

    public Post(Member owner, PostType type, Timestamp postDate, Timestamp lastEditedDate,
                String title, String content, Set<Heritage> heritages) {
        this.owner = owner;
        this.type = type;
        this.postDate = postDate;
        this.lastEditedDate = lastEditedDate;
        this.title = title;
        this.content = content;
        this.heritages = heritages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type.ordinal();
    }

    public void setType(int type) { this.type = PostType.values()[type]; }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Heritage> getHeritages() { return heritages; }

    public void setHeritages(Set<Heritage> heritages) { this.heritages = heritages; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (type != post.type) return false;
        if (postDate != null ? !postDate.equals(post.postDate) : post.postDate != null) return false;
        if (lastEditedDate != null ? !lastEditedDate.equals(post.lastEditedDate) : post.lastEditedDate != null)
            return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (lastEditedDate != null ? lastEditedDate.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}