package com.cmpe.bounswe2015group8.westory.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by xyllan on 07.11.2015.
 */
public class Comment {
    private long id;
    private String content;
    private String postDate;
    private String lastEditedDate;
    private Member owner;
    private Post post;
    private long ownerId;
    private long postId;
    private int netCount;
    private Collection<CommentVote> votes;
    public Comment() {
        votes = new HashSet<CommentVote>();
    }
    public Comment(Member owner, Post post, String content, String postDate
    ) {
        this.owner = owner;
        this.post = post;
        this.content = content;
        this.postDate = postDate;
        this.votes = new HashSet<CommentVote>();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getLastEditedDate() {
        return lastEditedDate;
    }

    public void setLastEditedDate(String lastEditedDate) {
        this.lastEditedDate = lastEditedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (postDate != null ? !postDate.equals(comment.postDate) : comment.postDate != null) return false;
        if (lastEditedDate != null ? !lastEditedDate.equals(comment.lastEditedDate) : comment.lastEditedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (lastEditedDate != null ? lastEditedDate.hashCode() : 0);
        return result;
    }

    public Requestable<String> getCreateRequestable(long postId) {
        Map<String,String> dataToSend = new HashMap<>();

        dataToSend.put("postId", Long.toString(postId));
        dataToSend.put("ownerId", Long.toString(owner.getId()));
        dataToSend.put("content", content);
        return new Requestable<String>("/api/postComment",dataToSend,String.class);
    }
    public Requestable<String> getVoteRequestable(boolean voteType,Long ownerId) {
        Map<String,String> dataToSend = new HashMap<>();

        dataToSend.put("commentId", Long.toString(getId()));
        dataToSend.put("ownerId", Long.toString(ownerId));
        dataToSend.put("voteType", Boolean.toString(voteType));
        return new Requestable<String>("/api/voteComment",dataToSend,String.class);
    }


    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public Post getPost() {
        return post;
    }

    public long getOwnerId() { return ownerId; }

    public void setOwnerId(long ownerId) { this.ownerId = ownerId; }

    public long getPostId() { return postId; }

    public void setPostId(long postId) { this.postId = postId; }

    public int getNetCount() {
        int netVoteCount=0;
        Collection<CommentVote> votes = getVotes();
        for(CommentVote cv: votes){
            if(cv.getVoteType()){
                netVoteCount++;
            } else{
                netVoteCount--;
            }

        }
        return netVoteCount;
    }

    public void setNetCount(int netCount) { this.netCount = netCount; }

    public void setPost(Post post) {
        this.post = post;
    }

    public Collection<CommentVote> getVotes() {
        return votes;
    }

    public void setVotes(Collection<CommentVote> votes) {
        this.votes = votes;
    }
}
