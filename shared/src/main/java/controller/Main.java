package controller;

import model.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by xyllan on 23.10.2015.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;
    private static final Metadata metadata;

    static {
        try {
            serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            ourSessionFactory = metadata.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
    public static SessionFactory getSessionFactory() {
        return ourSessionFactory;
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        try {
            session.getTransaction().begin();
            //Member m = new Member("root","1234","link","lonk");
            //Heritage h = new Heritage("türbe","somewhere","something",Timestamp.valueOf(LocalDateTime.now()));
            Member m = (Member)session.get(Member.class,8l);
            Heritage h = (Heritage)session.get(Heritage.class,4l);
            Tag t = new Tag("smt", null);
            Post p = new Post(m,1, Timestamp.valueOf(LocalDateTime.now()),"title","content");
            p.addTags(t);
            h.addTags(t);
            m.postPost(p,h);
            Comment c = new Comment(m,p,"content",Timestamp.valueOf(LocalDateTime.now()));
            m.postComment(p,c);
            m.voteComment(c,true);
            m.votePost(p,false);
            m.follow((Member)session.get(Member.class,6l));
            session.save(h);
            Serializable id = session.save(m);

            session.getTransaction().commit();
            System.out.println("Member posts: "+m.getPosts());
            System.out.println("Heritage posts: "+h.getPosts());
            Member m2 = ((Member)session.get(Member.class,id));
            System.out.println(m2.getId());
            System.out.println(m2.getPosts());

        } finally {
            session.close();
        }
    }
}
