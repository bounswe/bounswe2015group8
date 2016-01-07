package com.cmpe.bounswe2015group8.westory.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Base object for our cultural heritage objects.
 * @author xyllan
 * Date: 07.11.2015
 */
public class Heritage implements Parcelable{
    private long id;
    private String name;
    private String place;
    private String postDate;
    private String description;
    private Collection<Post> posts;
    private Collection<Tag> tags;
    private Collection<Member> followers;
    private Collection<Media> media;
    public Heritage() {
        this.posts = new HashSet<Post>();
        this.tags = new HashSet<Tag>();
        this.followers = new HashSet<Member>();
        this.media = new HashSet<>();
    }

    public Heritage(String name, String place, String description, String postDate) {
        this.name = name;
        this.place = place;
        this.description = description;
        this.postDate = postDate;
        this.posts = new HashSet<Post>();
        this.tags = new HashSet<Tag>();
        this.followers = new HashSet<Member>();
        this.media = new HashSet<>();
    }
    public Heritage(Parcel in) {
        id = in.readLong();
        name = in.readString();
        place = in.readString();
        postDate = in.readString();
        description = in.readString();
        //TODO fix posts
        this.posts = new HashSet<Post>();
        this.tags = new HashSet<Tag>();
        this.followers = new HashSet<Member>();
        this.media = new HashSet<>();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Heritage heritage = (Heritage) o;

        if (id != heritage.id) return false;
        if (name != null ? !name.equals(heritage.name) : heritage.name != null) return false;
        if (place != null ? !place.equals(heritage.place) : heritage.place != null) return false;
        if (postDate != null ? !postDate.equals(heritage.postDate) : heritage.postDate != null) return false;
        if (description != null ? !description.equals(heritage.description) : heritage.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + (postDate != null ? postDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public Collection<Member> getFollowers(){ return followers; }

    public void setFollowers(Collection<Member> followers){ this.followers = followers; }

    public Collection<Media> getMedia() {
        return media;
    }

    public void setMedia(Collection<Media> media) {
        this.media = media;
    }

    public void addTags(Tag... tags) {
        for(Tag t : tags) {
            this.tags.add(t);
            t.getHeritages().add(this);
        }
    }
    public Requestable<Long> getCreateRequestable() {
        Map<String,String> dataToSend = new HashMap<>();
        dataToSend.put("name", name);
        dataToSend.put("place", place);
        dataToSend.put("description", description);
        return new Requestable<>("/api/createHeritage",dataToSend,Long.class);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(place);
        dest.writeString(postDate);
        dest.writeString(description);
        //TODO fix posts
    }
    public static final Parcelable.Creator<Heritage> CREATOR
            = new Parcelable.Creator<Heritage>() {
        public Heritage createFromParcel(Parcel in) {
            return new Heritage(in);
        }

        public Heritage[] newArray(int size) {
            return new Heritage[size];
        }
    };
}
