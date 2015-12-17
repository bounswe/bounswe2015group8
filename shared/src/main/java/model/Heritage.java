package model;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by xyllan on 22.10.2015.
 */
public class Heritage {
    private long id;
    private String name;
    private String place;
    private Timestamp postDate;
    private String description;
    private Collection<Post> posts;
    private Collection<Tag> tags;
    private Collection<Member> followers;
    public Heritage() {
        this.posts = new HashSet<Post>();
        this.tags = new HashSet<Tag>();
        this.followers = new HashSet<Member>();
    }

    public Heritage(String name, String place, String description, Timestamp postDate) {
        this.name = name;
        this.place = place;
        this.description = description;
        this.postDate = postDate;
        this.posts = new HashSet<Post>();
        this.tags = new HashSet<Tag>();
        this.followers = new HashSet<Member>();
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

    public Collection<Member> getFollowers(){ return followers; }

    public void setFollowers(Collection<Member> followers){ this.followers = followers; }
}
