package api;

/**
 * Created by Goktug on 07.12.2015.
 */
public class FollowApiModel {
    private long followerId;
    private long followeeId;

    public FollowApiModel(long followerId, long followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
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
}
