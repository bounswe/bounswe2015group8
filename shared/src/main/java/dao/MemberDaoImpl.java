package dao;

import controller.Main;
import model.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;

/**
 * Created by xyllan on 30.10.2015.
 */
public class MemberDaoImpl implements MemberDao{
    private SessionFactory sessionFactory;
    public Member getMemberById(long id) {
        Session s = getSessionFactory().openSession();
        Member member = (Member)s
                .createQuery("from Member where id=?")
                .setParameter(0, id).uniqueResult();
        s.close();
        return member;
    }
    public Member getMemberByUsername(String username) {
        Session s = getSessionFactory().openSession();
        Member member = (Member)s
                .createQuery("from Member where username=?")
                .setParameter(0, username).uniqueResult();
        s.close();
        return member;
    }
    public Member saveMember(Member m) {
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.save(m);
        s.getTransaction().commit();
        s.close();
        return m;
    }
    public Member updateMember(Member m){
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.update(m);
        s.getTransaction().commit();
        s.close();
        return m;
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Member> unproxyMemberList(List<Member> members){
        for(int i = 0; i < members.size(); i++){
            Member member = members.get(i);
            members.set(i, unproxyMember(member));
        }
        return members;
    }

    public Member unproxyMember(Member member){
        Hibernate.initialize(member);
        for(Comment comment : member.getComments()){
            Hibernate.initialize(comment);
            Hibernate.initialize(comment.getOwner());
        }
        Hibernate.initialize(member.getCommentVotes());
        for(CommentVote commentVote : member.getCommentVotes()){
            Hibernate.initialize(commentVote);
            Hibernate.initialize(commentVote.getOwner());
        }
        Hibernate.initialize(member.getFollowedMembers());
        for(Member followedMember : member.getFollowedMembers()){
            Hibernate.initialize(followedMember);
        }
        Hibernate.initialize(member.getFollowers());
        for(Member followerMember : member.getFollowers()) {
            Hibernate.initialize(followerMember);
        }
        Hibernate.initialize(member.getFollowedHeritages());
        for(Heritage followedHeritage : member.getFollowedHeritages()) {
            Hibernate.initialize(followedHeritage);
        }
        Hibernate.initialize(member.getPostVotes());
        for(PostVote postVote : member.getPostVotes()) {
            Hibernate.initialize(postVote);
        }
        Hibernate.initialize(member.getPosts());
        for(Post post : member.getPosts()) {
            Hibernate.initialize(post);
            Hibernate.initialize(post.getVotes());
            for(PostVote postVote : post.getVotes()){
                Hibernate.initialize(postVote);
            }
            Hibernate.initialize(post.getComments());
            for(Comment comment : post.getComments()){
                Hibernate.initialize(comment);
            }
            Hibernate.initialize(post.getTags());
            for(Tag tag : post.getTags()){
                Hibernate.initialize(tag);
            }
            Hibernate.initialize(post.getVotes());
            for(PostVote postVote : post.getVotes()){
                Hibernate.initialize(postVote);
            }
        }
        member = Main.initializeAndUnproxy(member);
        return member;
    }
}
