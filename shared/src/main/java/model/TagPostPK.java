package model;

import java.io.Serializable;

/**
 * Created by xyllan on 23.10.2015.
 */
public class TagPostPK implements Serializable {
    private long tagId;
    private long postId;
    private Tag tag;
    private Post post;

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
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

        TagPostPK tagPostPK = (TagPostPK) o;

        if (tagId != tagPostPK.tagId) return false;
        if (postId != tagPostPK.postId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tagId ^ (tagId >>> 32));
        result = 31 * result + (int) (postId ^ (postId >>> 32));
        return result;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
