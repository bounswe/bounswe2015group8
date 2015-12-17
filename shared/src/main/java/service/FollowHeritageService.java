package service;

import dao.FollowHeritageDao;
import dao.FollowHeritageDaoImpl;
import dao.MemberDao;
import model.FollowHeritage;
import model.Heritage;
import model.Member;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coldwhistle on 12/6/15.
 */
public class FollowHeritageService {
    private FollowHeritageDao followHeritageDao;

    public FollowHeritageService(SessionFactory sessionFactory) {
        followHeritageDao = new FollowHeritageDaoImpl();
        followHeritageDao.setSessionFactory(sessionFactory);
    }

    public List<Member> getHeritageFollowersByHeritage(Heritage heritage) {
        List<Long> memberIds = followHeritageDao.getHeritageFollowersByHeritage(heritage);
        MemberDetailsService memberDetailsService = new MemberDetailsService();
        List<Member> members = new ArrayList<>();
        for(int i = 0; i < memberIds.size(); i++){
            members.add(memberDetailsService.getMemberById(memberIds.get(i)));
        }
        return members;
    }

    public List<Heritage> getFollowedHeritagesByMemberId(long id) {
        List<Long> heritageIds = followHeritageDao.getFollowedHeritagesByMemberId(id);
        HeritageService heritageService = new HeritageService(followHeritageDao.getSessionFactory());
        List<Heritage> heritages = new ArrayList<>();
        for(int i = 0; i < heritageIds.size(); i++) {
            heritages.add(heritageService.getHeritageById(heritageIds.get(i)));
        }
        return heritages;
    }

    public boolean doesMemberFollowHeritage(long memberId, long heritageId){
        return followHeritageDao.doesMemberFollowHeritage(memberId, heritageId);
    }

    public FollowHeritage saveFollowHeritage(long followerId, long heritageId) {
        if(doesMemberFollowHeritage(followerId, heritageId))
            return null;
        FollowHeritage followHeritage = new FollowHeritage(followerId, heritageId);
        return followHeritageDao.saveFollowHeritage(followHeritage);
    }

    public void deleteFollowHeritage(long followerId, long heritageId) {
        if(!doesMemberFollowHeritage(followerId, heritageId))
            return;
        followHeritageDao.unfollow(followerId, heritageId);
    }
}
