package model;

import javax.persistence.*;

/**
 * Created by gokcan on 05.12.2015.
 */
@Entity
@Table(name = "follow_heritage", schema = "", catalog = "cmpe451_db")
@IdClass(FollowHeritagePK.class)
public class FollowHeritage {
    private long followerId;
    private long heritageId;

    public FollowHeritage(long followerId, long heritageId) {
        this.followerId = followerId;
        this.heritageId = heritageId;
    }

    @Id
    @Column(name = "FOLLOWER_ID")
    public long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(long followerId) {
        this.followerId = followerId;
    }

    @Id
    @Column(name = "HERITAGE_ID")
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

        FollowHeritage that = (FollowHeritage) o;

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
