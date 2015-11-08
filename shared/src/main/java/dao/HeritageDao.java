package dao;

import model.Heritage;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public interface HeritageDao {
    public Heritage getHeritageById(long id);
    public List<Heritage> getAllHeritages();
    public Heritage saveHeritage(Heritage heritage);
    public SessionFactory getSessionFactory();
    public void setSessionFactory(SessionFactory sessionFactory);
}
