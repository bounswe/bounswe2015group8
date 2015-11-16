package dao;

import model.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
}
