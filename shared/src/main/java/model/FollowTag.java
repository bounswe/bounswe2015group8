package model;

import javax.persistence.*;

/**
 * Created by gokcan on 06.12.2015.
 */
@Entity
@Table(name = "follow_tag", schema = "", catalog = "cmpe451_db")
@IdClass(FollowTagPK.class)
public class FollowTag {
    private long followerId;
    private long tagId;

    @Id
    @Column(name = "FOLLOWER_ID")
    public long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(long followerId) {
        this.followerId = followerId;
    }

    @Id
    @Column(name = "TAG_ID")
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

        FollowTag followTag = (FollowTag) o;

        if (followerId != followTag.followerId) return false;
        if (tagId != followTag.tagId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (followerId ^ (followerId >>> 32));
        result = 31 * result + (int) (tagId ^ (tagId >>> 32));
        return result;
    }
}
