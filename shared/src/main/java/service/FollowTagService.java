package service;

import dao.FollowTagDao;
import dao.FollowTagDaoImpl;
import model.*;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by coldwhistle on 12/6/15.
 */
public class FollowTagService {
    private FollowTagDao followTagDao;

    public FollowTagService(SessionFactory sessionFactory) {
        followTagDao = new FollowTagDaoImpl();
        followTagDao.setSessionFactory(sessionFactory);
    }

    public List<Member> getTagFollowersByTag(Tag tag) {
        List<Long> memberIds = followTagDao.getTagFollowersByTag(tag);
        MemberDetailsService memberDetailsService = new MemberDetailsService();
        List<Member> members = new ArrayList<>();
        for(int i = 0; i < memberIds.size(); i++){
            members.add(memberDetailsService.getMemberById(memberIds.get(i)));
        }
        return members;
    }

    public List<Tag> getFollowedTagsByMemberId(long id) {
        List<Long> tagIds = followTagDao.getFollowedTagsByMemberId(id);
        TagService tagService = new TagService(followTagDao.getSessionFactory());
        List<Tag> tags = new ArrayList<>();
        for(int i = 0; i < tagIds.size(); i++) {
            tags.add(tagService.getTagById(tagIds.get(i)));
        }
        return tags;
    }

    public boolean doesMemberFollowTag(long memberId, long tagId){
        return followTagDao.doesMemberFollowTag(memberId, tagId);
    }

    public FollowTag saveFollowTag(long followerId, long tagId) {
        if(doesMemberFollowTag(followerId, tagId))
            return null;
        FollowTag followTag = new FollowTag(followerId, tagId);
        return followTagDao.saveFollowTag(followTag);
    }





}
