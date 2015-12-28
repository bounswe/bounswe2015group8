package dao;

import model.Gamification;
import model.Member;
import org.hibernate.SessionFactory;

/**
 * Created by gokcan on 27.12.2015.
 */
public interface GamificationDao {
    boolean doesExist(Member member);

    Gamification get(Member member);

    Gamification save(Gamification gamification);

    Gamification update(Gamification gamification);

    SessionFactory getSessionFactory();

    void setSessionFactory(SessionFactory sessionFactory);
}
