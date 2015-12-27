package dao;

import model.Heritage;
import model.Post;
import model.Tag;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public interface HeritageDao {
    Heritage getHeritageById(long id);

    Heritage getHeritageByName(String name);

    List<Heritage> getAllHeritages();

    List<Heritage> getHeritagesByPost(Post post);

    List<Heritage> getHeritagesByTag(Tag tag);

    List<Heritage> getHeritagesContainName(String name);

    List<Heritage> getHeritagesContainDescription(String description);

    List<Heritage> getHeritagesCreatedAfter(Timestamp date);

    List<Heritage> getHeritagesCreatedBefore(Timestamp date);

    int countPostsInHeritage(Heritage heritage);

    Heritage saveHeritage(Heritage heritage);

    boolean doesHeritageHavaPost(Heritage heritage, Post post);

    SessionFactory getSessionFactory();

    void setSessionFactory(SessionFactory sessionFactory);

}
