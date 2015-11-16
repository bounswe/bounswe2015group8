package dao;

import model.Heritage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public class HeritageDaoImpl implements HeritageDao {
    private SessionFactory sessionFactory;

    public Heritage getHeritageById(long id) {
        Session s = getSessionFactory().openSession();
        Heritage heritage = (Heritage) s
                .createQuery("from Heritage where id=?")
                .setParameter(0, id).uniqueResult();
        s.close();
        return heritage;
    }

    public List<Heritage> getAllHeritages() {
        Session s = getSessionFactory().openSession();
        List<Heritage> heritages = s.createQuery("from Heritage").list();
        s.close();
        return heritages;
    }

    public Heritage saveHeritage(Heritage heritage) {
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.save(heritage);
        s.getTransaction().commit();
        s.close();
        return heritage;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

