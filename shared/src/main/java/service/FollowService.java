package service;

import api.MemberUtility;
import dao.FollowDao;
import dao.FollowDaoImpl;
import model.Follow;
import model.Member;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;

/**
 * Created by Goktug on 03.12.2015.
 */
public class FollowService {
    private FollowDao followDao;

    public FollowService(SessionFactory sessionFactory) {
        followDao = new FollowDaoImpl();
        followDao.setSessionFactory(sessionFactory);
    }

    public Collection<Member> getFollowersById(long id) {
        return followDao.getFollowersById(id);
    }

    public Collection<Member> getFollowingById(long id) {
        return followDao.getFollowingById(id);
    }

    public List<Follow> getAllFollows() {
        return followDao.getAllFollows();
    }

    public void deleteFollow(Member follower, Member followee) {
        followDao.unfollow(follower,followee);
    }

    public Follow saveFollow(long followerId, long followeeId) {
        Follow follow = new Follow(followerId, followeeId);
        return followDao.saveFollow(follow);
    }
}

