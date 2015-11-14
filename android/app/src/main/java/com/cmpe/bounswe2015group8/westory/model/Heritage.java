package com.cmpe.bounswe2015group8.westory.model;
import android.os.Bundle;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by xyllan on 07.11.2015.
 */
public class Heritage {
    public static String BUNDLE_BASE = "heritage_cur_";
    private long id;
    private String name;
    private String place;
    private Timestamp postDate;
    private String description;
    private Collection<Post> posts;
    private Collection<Tag> tags;
    public Heritage() {
        this.posts = new HashSet<Post>();
        this.tags = new HashSet<Tag>();
    }

    public Heritage(String name, String place, String description, Timestamp postDate) {
        this.name = name;
        this.place = place;
        this.description = description;
        this.postDate = postDate;
        this.posts = new HashSet<Post>();
        this.tags = new HashSet<Tag>();
    }
    public Heritage(Bundle b) {
        id = b.getLong(BUNDLE_BASE + "id", -1);
        name = b.getString(BUNDLE_BASE + "name", "");
        place = b.getString(BUNDLE_BASE + "place","");
        postDate = new Timestamp(b.getLong(BUNDLE_BASE + "postDate",-1));
        description = b.getString(BUNDLE_BASE + "description","");
        //TODO fix posts
        this.posts = new HashSet<Post>();
        this.tags = new HashSet<Tag>();
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

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
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

    public void addTags(Tag... tags) {
        for(Tag t : tags) {
            this.tags.add(t);
            t.getHeritages().add(this);
        }
    }
    public Bundle getBundle() {
        Bundle b = new Bundle();
        b.putLong(BUNDLE_BASE + "id", id);
        b.putString(BUNDLE_BASE + "name",name);
        b.putString(BUNDLE_BASE + "place",place);
        b.putLong(BUNDLE_BASE + "postDate",postDate.getTime());
        b.putString(BUNDLE_BASE + "description",description);
        //TODO fix posts
        return b;
    }
}
