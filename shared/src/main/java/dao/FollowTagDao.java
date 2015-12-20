package dao;

import model.FollowTag;
import model.Tag;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by coldwhistle on 12/6/15.
 */
public interface FollowTagDao {
    List<Long> getTagFollowersByTag(Tag tag);

    List<Long> getFollowedTagsByMemberId(long id);

    boolean doesMemberFollowTag(long memberId, long tagId);

    FollowTag saveFollowTag(FollowTag followTag);

    SessionFactory getSessionFactory();

    void setSessionFactory(SessionFactory sessionFactory);

}
