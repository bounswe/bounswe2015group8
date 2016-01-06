package com.cmpe.bounswe2015group8.westory.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by xyllan on 07.11.2015.
 */
public class Tag implements Parcelable {
    private long id;
    private String tagText;
    private String tagContext;
    private Collection<Heritage> heritages;
    private Collection<Post> posts;
    private Collection<Member> followers;

    public Tag() {
        this.heritages = new HashSet<Heritage>();
        this.posts = new HashSet<Post>();
        this.followers = new HashSet<Member>();
    }

    public Tag(String tagText, String tagContext) {
        this.tagText = tagText;
        this.tagContext = tagContext;
        this.heritages = new HashSet<Heritage>();
        this.posts = new HashSet<Post>();
        this.followers = new HashSet<Member>();
    }
    public Tag(Parcel in) {
        tagText = in.readString();
        tagContext = in.readString();
        this.heritages = new HashSet<Heritage>();
        this.posts = new HashSet<Post>();
        this.followers = new HashSet<Member>();
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTagText() {
        return tagText;
    }

    public void setTagText(String tagText) {
        this.tagText = tagText;
    }

    public String getTagContext() {
        return tagContext;
    }

    public void setTagContext(String tagContext) {
        this.tagContext = tagContext;
    }

    public String getWholeTag() {
        return tagText + "(" + tagContext + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (id != tag.id) return false;
        if (tagText != null ? !tagText.equals(tag.tagText) : tag.tagText != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (tagText != null ? tagText.hashCode() : 0);
        return result;
    }

    public Collection<Heritage> getHeritages() {
        return heritages;
    }

    public void setHeritages(Collection<Heritage> heritages) {
        this.heritages = heritages;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public Collection<Member> getFollowers() { return followers; }

    public void setFollowers(Collection<Member> followers) { this.followers = followers; }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tagText);
        dest.writeString(tagContext);
    }
    public static final Parcelable.Creator<Tag> CREATOR
            = new Parcelable.Creator<Tag>() {
        public Tag createFromParcel(Parcel in) {
            return new Tag(in);
        }

        public Tag[] newArray(int size) {
            return new Tag[size];
        }
    };
}

