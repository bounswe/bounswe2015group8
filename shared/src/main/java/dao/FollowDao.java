package dao;

import model.Follow;
import model.Member;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

/**
 * Created by Goktug on 03.12.2015.
 */
public interface FollowDao {
    public Collection<Member> getFollowersById(long id);

    public List<Member> getFollowingById(long id);

    public List<Follow> getAllFollows();

    public boolean doesFollow(Member follower, Member followee);

    public Follow saveFollow(Follow follow);

    public void unfollow(Member follower, Member followee);

    SessionFactory getSessionFactory();

    void setSessionFactory(SessionFactory sessionFactory);
}
