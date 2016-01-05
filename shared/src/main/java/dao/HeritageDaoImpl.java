package dao;

import controller.Main;
import model.*;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gokcan on 08.11.2015.
 */
public class HeritageDaoImpl implements HeritageDao {
    private SessionFactory sessionFactory;
    private Logger logger = Logger.getLogger(HeritageDaoImpl.class);

    public Heritage getHeritageById(long id) {
        Session s = getSessionFactory().openSession();
        Heritage heritage = (Heritage) s
                .createQuery("from Heritage where id=?")
                .setParameter(0, id).uniqueResult();
        Hibernate.initialize(heritage.getPosts());
        for(Post post : heritage.getPosts()){
            Hibernate.initialize(post);
            Hibernate.initialize(post.getOwner());
            Hibernate.initialize(post.getComments());
        }
        Hibernate.initialize(heritage.getFollowers());
        Hibernate.initialize(heritage.getTags());
        s.close();
        return heritage;
    }

    @SuppressWarnings("unchecked")
    public Heritage getHeritageByName(String name){
        Session s = getSessionFactory().openSession();
        List<Heritage> heritages = s
                .createQuery("from Heritage where name=?")
                .setParameter(0, name).list();
        heritages = unproxyHeritageList(heritages);
        s.close();
        if(heritages.size() == 0){
            return null;
        }
        else{
            return heritages.get(0);
        }
    }

    public List<Heritage> getAllHeritages() {
        Session s = getSessionFactory().openSession();
        List<Heritage> heritages = s.createQuery("from Heritage").list();
        for(Heritage heritage : heritages){
            Hibernate.initialize(heritage.getTags());
        }
        s.close();
        return heritages;
    }

    @SuppressWarnings("unchecked")
    public List<Heritage> getHeritagesByPost(Post post){
        Session s = getSessionFactory().openSession();
        List<HeritagePost> heritageposts = s
                .createQuery("from HeritagePost where post=?")
                .setParameter(0, post).list();
        List<Heritage> heritages = new ArrayList<Heritage>();
        for (int i = 0; i < heritageposts.size(); i++) {
            heritages.add(heritageposts.get(i).getHeritage());
        }
        heritages = unproxyHeritageList(heritages);
        s.close();
        return heritages;
    }

    @SuppressWarnings("unchecked")
    public List<Heritage> getHeritagesCreatedAfter(Timestamp date){
        Session s = getSessionFactory().openSession();
        List<Heritage> heritages = s
                .createQuery("from Heritage where postDate > :date")
                .setParameter("date", date).list();
        heritages = unproxyHeritageList(heritages);
        s.close();
        return heritages;
    }

    public boolean doesHeritageHavaPost(Heritage heritage, Post post){
        Session s = getSessionFactory().openSession();
        int count = s
                .createQuery("from HeritagePost where heritage=? and post=?")
                .setParameter(0, heritage)
                .setParameter(1, post).list().size();
        s.close();
        if(count == 0)
            return false;
        else
            return true;
    }

    public int countPostsInHeritage(Heritage heritage){
        Session s = getSessionFactory().openSession();
        int count = s
                .createQuery("from HeritagePost where heritage=?")
                .setParameter(0, heritage).list().size();
        s.close();
        return count;
    }

    @SuppressWarnings("unchecked")
    public List<Heritage> getHeritagesByTag(Tag tag) {
        Session s = getSessionFactory().openSession();
        List<TagHeritage> tagheritages = s
                .createQuery("from TagHeritage where tag=?")
                .setParameter(0, tag).list();
        List<Long> heritageIds = new ArrayList<>();
        for (int i = 0; i < tagheritages.size(); i++) {
            heritageIds.add(tagheritages.get(i).getHeritage().getId());
        }

        if(heritageIds.size() == 0){
            s.close();
            return new ArrayList<Heritage>();
        }

        List<Heritage> heritages = s
                .createQuery("from Heritage where id in (:ids)")
                .setParameterList("ids", heritageIds).list();
        heritages = unproxyHeritageList(heritages);
        s.close();
        return heritages;
    }

    public Heritage saveHeritage(Heritage heritage) {
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.save(heritage);
        s.getTransaction().commit();
        s.close();
        return heritage;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Heritage> unproxyHeritageList(List<Heritage> heritages){
        for(int i = 0; i < heritages.size(); i++){
            Heritage heritage = heritages.get(i);
            heritages.set(i, unproxyHeritage(heritage));
        }
        return heritages;
    }

    public Heritage unproxyHeritage(Heritage heritage){
        Hibernate.initialize(heritage.getPosts());
        for(Post post : heritage.getPosts()){
            Hibernate.initialize(post);
            Hibernate.initialize(post.getOwner());
            Hibernate.initialize(post.getComments());
        }
        Hibernate.initialize(heritage.getFollowers());
        Hibernate.initialize(heritage.getTags());
        heritage = Main.initializeAndUnproxy(heritage);
        if(heritage instanceof HibernateProxy){
            logger.info("WHAT? WHY?");
        }
        return heritage;
    }
}

