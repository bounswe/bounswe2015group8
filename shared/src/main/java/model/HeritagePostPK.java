package model;

import java.io.Serializable;

/**
 * Created by xyllan on 22.10.2015.
 */
public class HeritagePostPK implements Serializable {
    private long heritageId;
    private long postId;

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

        HeritagePostPK that = (HeritagePostPK) o;

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
}
