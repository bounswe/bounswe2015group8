package dao;

import model.Gamification;
import model.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by gokcan on 27.12.2015.
 */
public class GamificationDaoImpl implements GamificationDao {
    private SessionFactory sessionFactory;

    public boolean doesExist(Member member){
        Session session = getSessionFactory().openSession();
        int count = session
                .createQuery("from Gamification where user=?")
                .setParameter(0, member).list().size();
        session.close();
        if(count > 0)
            return true;
        else
            return false;
    }

    public Gamification get(Member member){
        Session session = getSessionFactory().openSession();
        Gamification gamification = (Gamification)session
                .createQuery("from Gamification where user=?")
                .setParameter(0, member).uniqueResult();
        session.close();
        return gamification;
    }

    public Gamification save(Gamification gamification){
        Session session = getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(gamification);
        session.getTransaction().commit();
        session.close();
        return gamification;
    }

    public Gamification update(Gamification gamification){
        Session session = getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(gamification);
        session.getTransaction().commit();
        session.close();
        return gamification;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
