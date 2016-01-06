package dao;

import model.*;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public interface PostDao {
    Post getPostById(long id);

    List<Post> getPostsByOwner(Member owner);

    List<Post> getPostsByHeritage(Heritage heritage);

    List<Post> getPostsByTag(Tag tag);

    List<Post> getPostsContainTitle(String title);

    List<Post> getPostsContainContent(String content);

    List<Post> getPostsCreatedAfter(Timestamp date);

    List<Post> getPostsCreatedAfter(Timestamp date, Heritage heritage);

    List<Post> getPostsCreatedBefore(Timestamp date);

    List<Post> getPostsEditedAfter(Timestamp date);

    List<Post> getPostsEditedBefore(Timestamp date);

    Post savePost(Post post, Heritage heritage);

    HeritagePost linkPostWithHeritage(Post post, Heritage heritage);

    Post updatePost(Post post);

    SessionFactory getSessionFactory();

    void setSessionFactory(SessionFactory sessionFactory);

    List<Post> unproxyPostList(List<Post> posts);
}
