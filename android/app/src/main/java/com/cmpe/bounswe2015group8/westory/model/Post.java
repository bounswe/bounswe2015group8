package com.cmpe.bounswe2015group8.westory.model;

import android.os.Bundle;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by xyllan on 07.11.2015.
 */
public class Post {
    public static String BUNDLE_BASE = "post_cur_";
    private long id;
    private int type;
    private Member owner;
    private long ownerId;
    private String postDate;
    private String lastEditedDate;
    private String title;
    private String content;
    private Collection<Comment> comments;
    private Collection<Heritage> heritages;
    private Collection<PostVote> votes;
    private Collection<Tag> tags;
    public Post() {
        comments = new HashSet<Comment>();
        heritages = new HashSet<Heritage>();
        votes = new HashSet<PostVote>();
        tags = new HashSet<Tag>();
    }

    public Post(Member owner, int type, String postDate, String title, String content) {
        this.owner = owner;
        this.type = type;
        this.postDate = postDate;
        this.title = title;
        this.content = content;
        comments = new HashSet<Comment>();
        heritages = new HashSet<Heritage>();
        votes = new HashSet<PostVote>();
        tags = new HashSet<Tag>();
    }
    public Post(Bundle b) {
        id = b.getLong(BUNDLE_BASE + "id", -1);
        //TODO save owner also
        //owner = b.getString(BUNDLE_BASE + "owner", "");
        postDate = b.getString(BUNDLE_BASE + "postDate", "");
        lastEditedDate = b.getString(BUNDLE_BASE + "lastEditedDate","");
        long time = b.getLong(BUNDLE_BASE + "lastEditedDate",-1);
        title = b.getString(BUNDLE_BASE + "title","");
        content = b.getString(BUNDLE_BASE + "content","");
        //TODO fix comments
        this.comments = new HashSet<>();
        this.heritages = new HashSet<>();
        this.votes = new HashSet<>();
        this.tags = new HashSet<>();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (type != post.type) return false;
        if (postDate != null ? !postDate.equals(post.postDate) : post.postDate != null) return false;
        if (lastEditedDate != null ? !lastEditedDate.equals(post.lastEditedDate) : post.lastEditedDate != null)
            return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + type;
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (lastEditedDate != null ? lastEditedDate.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Collection<Heritage> getHeritages() {
        return heritages;
    }

    public void setHeritages(Collection<Heritage> heritages) {
        this.heritages = heritages;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public long getOwnerId() { return ownerId; }

    public void setOwnerId(long ownerId) { this.ownerId = ownerId; }

    public Collection<PostVote> getVotes() {
        return votes;
    }

    public void setVotes(Collection<PostVote> votes) {
        this.votes = votes;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public void addTags(Tag... tags) {
        for(Tag t : tags) {
            this.tags.add(t);
            t.getPosts().add(this);
        }
    }
    public Bundle getBundle() {
        Bundle b = new Bundle();
        b.putLong(BUNDLE_BASE + "id",id);
        //TODO save owner also
        //owner = b.getString(BUNDLE_BASE + "owner", "");
        b.putString(BUNDLE_BASE + "postDate",postDate);
        b.putString(BUNDLE_BASE + "lastEditedDate",lastEditedDate);
        b.putString(BUNDLE_BASE + "title",title);
        b.putString(BUNDLE_BASE + "content",content);
        //TODO fix comments
        return b;
    }
    public Requestable<Long> getCreateRequestable(long heritageId) {
        Map<String,String> dataToSend = new HashMap<>();
        dataToSend.put("heritageId", Long.toString(heritageId));
        dataToSend.put("type",Integer.toString(0));
        dataToSend.put("ownerId", Long.toString(owner.getId()));
        dataToSend.put("title", title);
        dataToSend.put("content", content);
        return new Requestable("/api/createPost",dataToSend,Long.class);
    }
}