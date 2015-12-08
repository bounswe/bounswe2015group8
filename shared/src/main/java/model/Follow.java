package model;

import api.MemberUtility;

import java.util.ArrayList;

/**
 * Created by xyllan on 23.10.2015.
 */
public class Follow {
    private long followerId;
    private long followeeId;
    private Member follower;
    private Member followee;

    public Follow(){

    }

    public Follow(long followerId, long followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
        ArrayList<Member> members = MemberUtility.getUserList();
        for (Member m : members) {
            if (m.getId() == followeeId) {
                followee = m;
            } else if (m.getId() == followerId) {
                follower = m;
            }
        }

        follower.follow(followee);
    }

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

        Follow follow = (Follow) o;

        if (followerId != follow.followerId) return false;
        if (followeeId != follow.followeeId) return false;

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
