package model;

/**
 * Created by gokcan on 27.12.2015.
 */
public class Gamification {
    private long id;
    private Long postNum;
    private Integer postLevel;
    private Long heritageNum;
    private Integer heritageLevel;
    private Long followerNum;
    private Integer followerLevel;
    private Long followeeNum;
    private Integer followeeLevel;
    private Long commentNum;
    private Integer commentLevel;
    private Long upvoteNum;
    private Integer upvoteLevel;
    private Long downvoteNum;
    private Integer downvoteLevel;
    private Integer overallLevel;
    private Member user;

    public Gamification(){
        postNum = (long)0;
        postLevel = 0;
        heritageNum = (long)0;
        heritageLevel = 0;
        followerNum = (long)0;
        followerLevel = 0;
        followeeNum = (long)0;
        followeeLevel = 0;
        commentNum = (long)0;
        commentLevel = 0;
        upvoteNum = (long)0;
        upvoteLevel = 0;
        downvoteNum = (long)0;
        downvoteLevel = 0;
        overallLevel = 0;
    }

    public Gamification(Member user){
        this.user = user;
        postNum = (long)0;
        postLevel = 0;
        heritageNum = (long)0;
        heritageLevel = 0;
        followerNum = (long)0;
        followerLevel = 0;
        followeeNum = (long)0;
        followeeLevel = 0;
        commentNum = (long)0;
        commentLevel = 0;
        upvoteNum = (long)0;
        upvoteLevel = 0;
        downvoteNum = (long)0;
        downvoteLevel = 0;
        overallLevel = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Member getUser() { return user; }

    public void setUser(Member user) { this.user = user; }

    public Long getPostNum() {
        return postNum;
    }

    public void setPostNum(Long postNum) {
        this.postNum = postNum;
    }

    public Integer getPostLevel() {
        return postLevel;
    }

    public void setPostLevel(Integer postLevel) {
        this.postLevel = postLevel;
    }

    public Long getHeritageNum() {
        return heritageNum;
    }

    public void setHeritageNum(Long heritageNum) {
        this.heritageNum = heritageNum;
    }

    public Integer getHeritageLevel() {
        return heritageLevel;
    }

    public void setHeritageLevel(Integer heritageLevel) {
        this.heritageLevel = heritageLevel;
    }

    public Long getFollowerNum() {
        return followerNum;
    }

    public void setFollowerNum(Long followerNum) {
        this.followerNum = followerNum;
    }

    public Integer getFollowerLevel() {
        return followerLevel;
    }

    public void setFollowerLevel(Integer followerLevel) {
        this.followerLevel = followerLevel;
    }

    public Long getFolloweeNum() {
        return followeeNum;
    }

    public void setFolloweeNum(Long followeeNum) {
        this.followeeNum = followeeNum;
    }

    public Integer getFolloweeLevel() {
        return followeeLevel;
    }

    public void setFolloweeLevel(Integer followeeLevel) {
        this.followeeLevel = followeeLevel;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    public Long getUpvoteNum() {
        return upvoteNum;
    }

    public void setUpvoteNum(Long upvoteNum) {
        this.upvoteNum = upvoteNum;
    }

    public Integer getUpvoteLevel() {
        return upvoteLevel;
    }

    public void setUpvoteLevel(Integer upvoteLevel) {
        this.upvoteLevel = upvoteLevel;
    }

    public Long getDownvoteNum() {
        return downvoteNum;
    }

    public void setDownvoteNum(Long downvoteNum) {
        this.downvoteNum = downvoteNum;
    }

    public Integer getDownvoteLevel() {
        return downvoteLevel;
    }

    public void setDownvoteLevel(Integer downvoteLevel) {
        this.downvoteLevel = downvoteLevel;
    }

    public Integer getOverallLevel() {
        return overallLevel;
    }

    public void setOverallLevel(Integer overallLevel) {
        this.overallLevel = overallLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gamification that = (Gamification) o;

        if (id != that.id) return false;
        if (postNum != null ? !postNum.equals(that.postNum) : that.postNum != null) return false;
        if (postLevel != null ? !postLevel.equals(that.postLevel) : that.postLevel != null) return false;
        if (heritageNum != null ? !heritageNum.equals(that.heritageNum) : that.heritageNum != null) return false;
        if (heritageLevel != null ? !heritageLevel.equals(that.heritageLevel) : that.heritageLevel != null)
            return false;
        if (followerNum != null ? !followerNum.equals(that.followerNum) : that.followerNum != null) return false;
        if (followerLevel != null ? !followerLevel.equals(that.followerLevel) : that.followerLevel != null)
            return false;
        if (followeeNum != null ? !followeeNum.equals(that.followeeNum) : that.followeeNum != null) return false;
        if (followeeLevel != null ? !followeeLevel.equals(that.followeeLevel) : that.followeeLevel != null)
            return false;
        if (commentNum != null ? !commentNum.equals(that.commentNum) : that.commentNum != null) return false;
        if (commentLevel != null ? !commentLevel.equals(that.commentLevel) : that.commentLevel != null) return false;
        if (upvoteNum != null ? !upvoteNum.equals(that.upvoteNum) : that.upvoteNum != null) return false;
        if (upvoteLevel != null ? !upvoteLevel.equals(that.upvoteLevel) : that.upvoteLevel != null) return false;
        if (downvoteNum != null ? !downvoteNum.equals(that.downvoteNum) : that.downvoteNum != null) return false;
        if (downvoteLevel != null ? !downvoteLevel.equals(that.downvoteLevel) : that.downvoteLevel != null)
            return false;
        if (overallLevel != null ? !overallLevel.equals(that.overallLevel) : that.overallLevel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (postNum != null ? postNum.hashCode() : 0);
        result = 31 * result + (postLevel != null ? postLevel.hashCode() : 0);
        result = 31 * result + (heritageNum != null ? heritageNum.hashCode() : 0);
        result = 31 * result + (heritageLevel != null ? heritageLevel.hashCode() : 0);
        result = 31 * result + (followerNum != null ? followerNum.hashCode() : 0);
        result = 31 * result + (followerLevel != null ? followerLevel.hashCode() : 0);
        result = 31 * result + (followeeNum != null ? followeeNum.hashCode() : 0);
        result = 31 * result + (followeeLevel != null ? followeeLevel.hashCode() : 0);
        result = 31 * result + (commentNum != null ? commentNum.hashCode() : 0);
        result = 31 * result + (commentLevel != null ? commentLevel.hashCode() : 0);
        result = 31 * result + (upvoteNum != null ? upvoteNum.hashCode() : 0);
        result = 31 * result + (upvoteLevel != null ? upvoteLevel.hashCode() : 0);
        result = 31 * result + (downvoteNum != null ? downvoteNum.hashCode() : 0);
        result = 31 * result + (downvoteLevel != null ? downvoteLevel.hashCode() : 0);
        result = 31 * result + (overallLevel != null ? overallLevel.hashCode() : 0);
        return result;
    }
}
