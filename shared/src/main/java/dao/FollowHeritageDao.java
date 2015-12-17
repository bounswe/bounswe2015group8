package dao;

import model.FollowHeritage;
import model.Heritage;
import model.Member;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by coldwhistle on 12/6/15.
 */
public interface FollowHeritageDao {
    List<Long> getHeritageFollowersByHeritage(Heritage heritage);

    List<Long> getFollowedHeritagesByMemberId (long id);

    boolean doesMemberFollowHeritage(long memberId, long heritageId);

    FollowHeritage saveFollowHeritage (FollowHeritage followHeritage);

    void unfollow(long followerId, long heritageId);

    SessionFactory getSessionFactory();

    void setSessionFactory(SessionFactory sessionFactory);

}
