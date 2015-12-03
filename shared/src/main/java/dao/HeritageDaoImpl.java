package dao;

import model.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
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
        Hibernate.initialize(heritage.getTags());
        s.close();
        return heritage;
    }

    public List<Heritage> getAllHeritages() {
        Session s = getSessionFactory().openSession();
        List<Heritage> heritages = s.createQuery("from Heritage").list();
        for(Heritage heritage : heritages){
            Hibernate.initialize(heritage.getTags());
        }
        s.close();
        return heritages;
    }

    @SuppressWarnings("unchecked")
    public List<Heritage> getHeritagesByPost(Post post){
        Session s = getSessionFactory().openSession();
        List<HeritagePost> heritageposts = s
                .createQuery("from HeritagePost where post=?")
                .setParameter(0, post).list();
        List<Heritage> heritages = new ArrayList<Heritage>();
        for (int i = 0; i < heritageposts.size(); i++) {
            heritages.add(heritageposts.get(i).getHeritage());
        }
        return heritages;
    }

    @SuppressWarnings("unchecked")
    public List<Heritage> getHeritagesByTag(Tag tag) {
        Session s = getSessionFactory().openSession();
        List<TagHeritage> tagheritages = s
                .createQuery("from TagHeritage where tag=?")
                .setParameter(0, tag).list();
        List<Heritage> heritages = new ArrayList<Heritage>();
        for (int i = 0; i < tagheritages.size(); i++) {
            heritages.add(tagheritages.get(i).getHeritage());
        }
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

