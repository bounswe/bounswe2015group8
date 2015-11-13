package model;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by xyllan on 23.10.2015.
 */
public class Member {
    private long id;
    private String username;
    private String password;
    private String email;
    private transient String profilePicture;
    private transient Collection<Comment> comments;
    private transient Collection<CommentVote> commentVotes;
    private transient Collection<Member> followedMembers;
    private transient Collection<Member> followers;
    private transient Collection<Post> posts;
    private transient Collection<PostVote> postVotes;
    public Member() {
        comments = new HashSet<Comment>();
        commentVotes = new HashSet<CommentVote>();
        followedMembers = new HashSet<Member>();
        followers = new HashSet<Member>();
        posts = new HashSet<Post>();
        postVotes = new HashSet<PostVote>();
    }

    public Member(String username, String password, String email, String profilePicture) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profilePicture = profilePicture;
        comments = new HashSet<Comment>();
        commentVotes = new HashSet<CommentVote>();
        followedMembers = new HashSet<Member>();
        followers = new HashSet<Member>();
        posts = new HashSet<Post>();
        postVotes = new HashSet<PostVote>();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (id != member.id) return false;
        if (username != null ? !username.equals(member.username) : member.username != null) return false;
        if (password != null ? !password.equals(member.password) : member.password != null) return false;
        if (email != null ? !email.equals(member.email) : member.email != null) return false;
        if (profilePicture != null ? !profilePicture.equals(member.profilePicture) : member.profilePicture != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (profilePicture != null ? profilePicture.hashCode() : 0);
        return result;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Collection<CommentVote> getCommentVotes() {
        return commentVotes;
    }

    public void setCommentVotes(Collection<CommentVote> commentVotes) {
        this.commentVotes = commentVotes;
    }

    public Collection<Member> getFollowedMembers() {
        return followedMembers;
    }

    public void setFollowedMembers(Collection<Member> followedMembers) {
        this.followedMembers = followedMembers;
    }

    public Collection<Member> getFollowers() {
        return followers;
    }

    public void setFollowers(Collection<Member> followers) {
        this.followers = followers;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public Collection<PostVote> getPostVotes() {
        return postVotes;
    }

    public void setPostVotes(Collection<PostVote> postVotes) {
        this.postVotes = postVotes;
    }
    public void postPost(Post p, Heritage... heritages) {
        p.setOwner(this);
        posts.add(p);
        for(Heritage h : heritages) {
            p.getHeritages().add(h);
            h.getPosts().add(p);
        }
    }
    public void postComment(Post p, Comment c) {
        c.setOwner(this);
        comments.add(c);
        c.setPost(p);
        p.getComments().add(c);
    }
    public void voteComment(Comment c, boolean type) {
        CommentVote cv = new CommentVote(this,c,type);
        commentVotes.add(cv);
        c.getVotes().add(cv);
    }
    public void votePost(Post p, boolean type) {
        PostVote pv = new PostVote(this,p,type);
        postVotes.add(pv);
        p.getVotes().add(pv);
    }
    public void follow(Member other) {
        followedMembers.add(other);
        other.followers.add(this);
    }
}
