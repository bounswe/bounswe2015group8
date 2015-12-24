package dao;

import model.FollowTag;
import model.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrtysn on 12/6/15.
 */

public class FollowTagDaoImpl implements FollowTagDao{

    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<Long> getTagFollowersByTag(Tag tag) {
        Session s = getSessionFactory().openSession();
        List<FollowTag> followTags = s
                .createQuery("from FollowTag where tagId=?")
                .setParameter(0, tag.getId()).list();
        List<Long> memberIds = new ArrayList<>();
        for(int i = 0; i < followTags.size(); i++) {
            memberIds.add(followTags.get(i).getFollowerId());
        }
        return memberIds;
    }

    @SuppressWarnings("unchecked")
    public List<Long> getFollowedTagsByMemberId (long id) {
        Session s = getSessionFactory().openSession();
        List<FollowTag> followTags = s
                .createQuery("from FollowTag where followerId=?")
                .setParameter(0, id).list();
        List<Long> tagIds = new ArrayList<>();
        for(int i = 0; i < followTags.size(); i++){
            tagIds.add(followTags.get(i).getTagId());
        }
        return tagIds;
    }

    @SuppressWarnings("unchecked")
    public boolean doesMemberFollowTag(long memberId, long tagId){
        Session s = getSessionFactory().openSession();
        int count = s
                .createQuery("from FollowTag where tagId=? and followerId=?")
                .setParameter(0, tagId)
                .setParameter(1, memberId).list().size();
        s.close();
        if(count == 0)
            return false;
        else
            return true;
    }

    public FollowTag saveFollowTag (FollowTag followTag) {
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.save(followTag);
        s.getTransaction().commit();
        s.close();
        return followTag;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
