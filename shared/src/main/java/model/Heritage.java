package model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by xyllan on 21.10.2015.
 */
public class Heritage {
    private long id;
    private String name;
    private String place;
    private Timestamp postDate;
    private String description;
    private Set<Post> posts;
    public Heritage() {
        posts = new HashSet<Post>();
    }

    public Heritage(String name, String place, Timestamp postDate, String description, Set<Post> posts) {
        this.name = name;
        this.place = place;
        this.postDate = postDate;
        this.description = description;
        this.posts = posts;
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

    public Set<Post> getPosts() { return posts; }

    public void setPosts(Set<Post> posts) { this.posts = posts; }

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
}
