package dao;

import model.Heritage;
import model.Post;
import model.Tag;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public interface HeritageDao {
    Heritage getHeritageById(long id);

    List<Heritage> getAllHeritages();

    List<Heritage> getHeritagesByPost(Post post);

    List<Heritage> getHeritagesByTag(Tag tag);

    Heritage saveHeritage(Heritage heritage);

    SessionFactory getSessionFactory();

    void setSessionFactory(SessionFactory sessionFactory);

}
