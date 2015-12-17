package com.cmpe.bounswe2015group8.westory.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by xyllan on 07.11.2015.
 */
public class Post implements Parcelable{
    private long id;
    private int type;
    private Member owner;
    private long ownerId;
    private String username;
    private String heritage_id;
    private String postDate;
    private String lastEditedDate;
    private String title;
    private String content;
    private String place;
    private Collection<Comment> comments;
    private Collection<Heritage> heritages;
    private Collection<PostVote> votes;
    private Collection<Tag> tags;
    private Collection<Media> media;
    private int netCount;
    public Post() {
        comments = new HashSet<Comment>();
        heritages = new HashSet<Heritage>();
        votes = new HashSet<PostVote>();
        tags = new HashSet<Tag>();
        media = new HashSet<>();
    }

    public Post(Member owner, int type, String postDate, String title, String content, String place) {
        this.owner = owner;
        this.type = type;
        this.postDate = postDate;
        this.title = title;
        this.content = content;
        this.place = place;
        comments = new HashSet<Comment>();
        heritages = new HashSet<Heritage>();
        votes = new HashSet<PostVote>();
        tags = new HashSet<Tag>();
        media = new HashSet<>();
    }
    public Post(Parcel in) {
        id = in.readLong();
        postDate = in.readString();
        lastEditedDate = in.readString();
        title = in.readString();
        content = in.readString();
        place = in.readString();
        username = in.readString();
        //TODO fix comments
        this.comments = new HashSet<>();
        this.heritages = new HashSet<>();
        this.votes = new HashSet<>();
        this.tags = new HashSet<>();
        this.media = new HashSet<>();
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

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

    public void setHeritage_id(String heritage_id) {
        this.heritage_id = heritage_id;
    }

    public String getHeritage_id() { return heritage_id; }

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

    public void setNetCount(int netCount){this.netCount=netCount;}

    public int getNetCount (){return netCount;}

    public Collection<Media> getMedia() {
        return media;
    }

    public void setMedia(Collection<Media> media) {
        this.media = media;
    }

    public void addTags(Tag... tags) {
        for(Tag t : tags) {
            this.tags.add(t);
            t.getPosts().add(this);
        }
    }
    public Requestable<Long> getCreateRequestable(long heritageId) {
        Map<String,String> dataToSend = new HashMap<>();
        dataToSend.put("heritageId", Long.toString(heritageId));
        dataToSend.put("type",Integer.toString(0));
        dataToSend.put("ownerId", Long.toString(owner.getId()));
        dataToSend.put("title", title);
        dataToSend.put("content", content);
        dataToSend.put("place",place);
        return new Requestable<Long>("/api/createPost",dataToSend,Long.class);
    }

    public Requestable<String> getVoteRequestable(boolean voteType,Long ownerId) {
        Map<String,String> dataToSend = new HashMap<>();

        dataToSend.put("postId", Long.toString(getId()));
        dataToSend.put("ownerId", Long.toString(ownerId));
        dataToSend.put("voteType", Boolean.toString(voteType));
        return new Requestable<String>("/api/votePost",dataToSend,String.class);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public int getVoteCount(){
        int netVoteCount=0;
        Collection<PostVote> votes = getVotes();
        for(PostVote cv: votes){
            if(cv.getVoteType()){
                netVoteCount++;
            } else{
                netVoteCount--;
            }
        }
        return netVoteCount;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(postDate);
        dest.writeString(lastEditedDate);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(place);
        dest.writeString(username);
        //TODO save owner and other fields
    }
    public static final Parcelable.Creator<Post> CREATOR
            = new Parcelable.Creator<Post>() {
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

}
