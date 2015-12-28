package dao;

import api.MemberUtility;
import model.Follow;
import model.Heritage;
import model.Member;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Created by Goktug on 03.12.2015.
 */
public class FollowDaoImpl implements FollowDao {

    private SessionFactory sessionFactory;

    public Collection<Member> getFollowersById(long id) {
        Session s = getSessionFactory().openSession();
        Member member = (Member)s
                .createQuery("from Member where id=?")
                .setParameter(0, id).uniqueResult();
        for(Member follower : member.getFollowers()){
            Hibernate.initialize(follower);
        }
        s.close();
        return member.getFollowers();
    }

    @SuppressWarnings("unchecked")
    public List<Member> getFollowingById(long id) {
        Session s = getSessionFactory().openSession();
        Member member = (Member)s
                .createQuery("from Member where id=?")
                .setParameter(0, id).uniqueResult();
        List<Follow> follows = s
                .createQuery("from Follow where follower=?")
                .setParameter(0, member).list();
        List<Member> followees = new ArrayList<>();
        for (int i = 0; i < follows.size(); i++) {
            followees.add(follows.get(i).getFollowee());
        }
        s.close();
        return followees;
    }

    public List<Follow> getAllFollows() {
        Session s = getSessionFactory().openSession();
        List<Follow> follows = s.createQuery("from Follow").list();
        s.close();
        return follows;
    }

    public boolean doesFollow(Member follower, Member followee){
        Session s = getSessionFactory().openSession();
        int count = s
                .createQuery("from Follow where follower=? and followee=?")
                .setParameter(0, follower)
                .setParameter(1, followee).list().size();
        s.close();
        if(count == 0)
            return false;
        else
            return true;
    }

    public void unfollow(Member follower, Member followee) {
        Session s = getSessionFactory().openSession();
        Query q = s.createQuery("from Follow where follower=:fer and followee=:fee")
                .setParameter("fer", follower)
                .setParameter("fee", followee);
        Follow f = (Follow)q.uniqueResult();
        s.getTransaction().begin();
        s.delete(f);
        s.getTransaction().commit();
        s.close();
    }

    public Follow saveFollow(Follow follow) {
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.save(follow);
        s.getTransaction().commit();
        s.close();
        return follow;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
