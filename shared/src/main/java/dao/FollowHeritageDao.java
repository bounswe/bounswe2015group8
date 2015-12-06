package dao;

import model.FollowHeritage;
import model.Heritage;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by coldwhistle on 12/6/15.
 */
public interface FollowHeritageDao {
    public List<Long> getHeritageFollowersByHeritage(Heritage heritage);

    public List<Long> getFollowedHeritagesByMemberId (long id);

    public FollowHeritage saveFollowHeritage (FollowHeritage followHeritage);

    public SessionFactory getSessionFactory();

    public void setSessionFactory(SessionFactory sessionFactory);

}
