package dao;

import model.FollowHeritage;
import model.Heritage;
import model.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrtysn on 12/6/15.
 */

public class FollowHeritageDaoImpl implements FollowHeritageDao{

    private SessionFactory sessionFactory;

    public List<Long> getHeritageFollowersByHeritage(Heritage heritage) {
        Session s = getSessionFactory().openSession();
        List<FollowHeritage> followHeritages = s
                .createQuery("from FollowHeritage  where heritageId=?")
                .setParameter(0, heritage.getId()).list();
        List<Long> memberIds = new ArrayList<>();
        for(int i = 0; i < followHeritages.size(); i++) {
            memberIds.add(followHeritages.get(i).getFollowerId());
        }
        return memberIds;
    }

    public List<Long> getFollowedHeritagesByMemberId (long id) {
        Session s = getSessionFactory().openSession();
        List<FollowHeritage> followHeritages = s
                .createQuery("from FollowHeritage where followerId=?")
                .setParameter(0, id).list();
        List<Long> heritageIds = new ArrayList<>();
        for(int i = 0; i < followHeritages.size(); i++){
            heritageIds.add(followHeritages.get(i).getHeritageId());
        }
        return heritageIds;
    }

    public boolean doesMemberFollowHeritage(long memberId, long heritageId){
        Session s = getSessionFactory().openSession();
        int count = s
                .createQuery("from FollowHeritage where heritageId=? and followerId=?")
                .setParameter(0, heritageId)
                .setParameter(1, memberId).list().size();
        s.close();
        if(count == 0)
            return false;
        else
            return true;
    }

    public FollowHeritage saveFollowHeritage (FollowHeritage followHeritage) {
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.save(followHeritage);
        s.getTransaction().commit();
        s.close();
        return followHeritage;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
