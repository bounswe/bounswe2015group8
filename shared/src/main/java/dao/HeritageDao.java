package dao;

import model.Heritage;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public interface HeritageDao {
    Heritage getHeritageById(long id);

    List<Heritage> getAllHeritages();

    Heritage saveHeritage(Heritage heritage);

    SessionFactory getSessionFactory();

    void setSessionFactory(SessionFactory sessionFactory);
}