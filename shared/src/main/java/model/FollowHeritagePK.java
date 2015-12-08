package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by gokcan on 05.12.2015.
 */
public class FollowHeritagePK implements Serializable {
    private long followerId;
    private long heritageId;

    @Column(name = "FOLLOWER_ID")
    @Id
    public long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(long followerId) {
        this.followerId = followerId;
    }

    @Column(name = "HERITAGE_ID")
    @Id
    public long getHeritageId() {
        return heritageId;
    }

    public void setHeritageId(long heritageId) {
        this.heritageId = heritageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowHeritagePK that = (FollowHeritagePK) o;

        if (followerId != that.followerId) return false;
        if (heritageId != that.heritageId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (followerId ^ (followerId >>> 32));
        result = 31 * result + (int) (heritageId ^ (heritageId >>> 32));
        return result;
    }
}
