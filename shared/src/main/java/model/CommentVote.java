package model;

/**
 * Created by xyllan on 22.10.2015.
 */
public class CommentVote {
    private Member member;
    private Comment comment;
    private byte voteType;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public byte getVoteType() {
        return voteType;
    }

    public void setVoteType(byte voteType) {
        this.voteType = voteType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentVote that = (CommentVote) o;

        if (member != null ? !member.equals(that.member) : that.member != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (voteType != that.voteType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result =  (member != null ? member.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (int) voteType;
        return result;
    }
}
