package model;

import java.io.Serializable;

/**
 * Created by xyllan on 22.10.2015.
 */
public class FollowPK implements Serializable {
    private long followerId;
    private long followeeId;
    private Member follower;
    private Member followee;

    public long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(long followerId) {
        this.followerId = followerId;
    }

    public long getFolloweeId() {
        return followeeId;
    }

    public void setFolloweeId(long followeeId) {
        this.followeeId = followeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowPK followPK = (FollowPK) o;

        if (followerId != followPK.followerId) return false;
        if (followeeId != followPK.followeeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (followerId ^ (followerId >>> 32));
        result = 31 * result + (int) (followeeId ^ (followeeId >>> 32));
        return result;
    }

    public Member getFollower() {
        return follower;
    }

    public void setFollower(Member follower) {
        this.follower = follower;
    }

    public Member getFollowee() {
        return followee;
    }

    public void setFollowee(Member followee) {
        this.followee = followee;
    }
}
