package model;

/**
 * Created by xyllan on 22.10.2015.
 */
public class HeritagePost {
    private long heritageId;
    private long postId;
    private Heritage heritage;
    private Post post;

    public long getHeritageId() {
        return heritageId;
    }

    public void setHeritageId(long heritageId) {
        this.heritageId = heritageId;
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

        HeritagePost that = (HeritagePost) o;

        if (heritageId != that.heritageId) return false;
        if (postId != that.postId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (heritageId ^ (heritageId >>> 32));
        result = 31 * result + (int) (postId ^ (postId >>> 32));
        return result;
    }

    public Heritage getHeritage() {
        return heritage;
    }

    public void setHeritage(Heritage heritage) {
        this.heritage = heritage;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
