package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by gokcan on 06.12.2015.
 */
public class FollowTagPK implements Serializable {
    private long followerId;
    private long tagId;

    @Column(name = "FOLLOWER_ID")
    @Id
    public long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(long followerId) {
        this.followerId = followerId;
    }

    @Column(name = "TAG_ID")
    @Id
    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowTagPK that = (FollowTagPK) o;

        if (followerId != that.followerId) return false;
        if (tagId != that.tagId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (followerId ^ (followerId >>> 32));
        result = 31 * result + (int) (tagId ^ (tagId >>> 32));
        return result;
    }
}
