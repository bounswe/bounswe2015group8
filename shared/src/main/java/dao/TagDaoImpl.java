package dao;

import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gokcan on 21.11.2015.
 */
public class TagDaoImpl implements TagDao {
    private SessionFactory sessionFactory;

    public Tag getTagById(long id){
        Session s = getSessionFactory().openSession();
        Tag tag = (Tag) s
                .createQuery("from Tag where id=?")
                .setParameter(0, id).uniqueResult();
        s.close();
        return tag;
    }

    public Tag getTagByText(String tagText){
        Session s = getSessionFactory().openSession();
        Tag tag = (Tag) s
                .createQuery("from Tag where tagText=? and tagContext is null")
                .setParameter(0, tagText).uniqueResult();
        s.close();
        return tag;
    }

    public Tag getTagByTextAndContext(String tagText, String tagContext){
        Session s = getSessionFactory().openSession();
        Tag tag = (Tag) s
                .createQuery("from Tag where tagText=? and tagContext=?")
                .setParameter(0, tagText)
                .setParameter(1, tagContext).uniqueResult();
        s.close();
        return tag;
    }

    @SuppressWarnings("unchecked")
    public List<Tag> getTagsByHeritage(Heritage heritage){
        Session s = getSessionFactory().openSession();
        List<TagHeritage> tagheritages = s
                .createQuery("from TagHeritage where heritage=?")
                .setParameter(0, heritage).list();
        List<Tag> tags = new ArrayList<Tag>();
        for(int i = 0; i < tagheritages.size(); i++){
            tags.add(tagheritages.get(i).getTag());
        }
        s.close();
        return tags;
    }

    @SuppressWarnings("unchecked")
    public List<Tag> getTagsByPost(Post post){
        Session s = getSessionFactory().openSession();
        List<TagPost> tagposts = s
                .createQuery("from TagPost where post=?")
                .setParameter(0, post).list();
        List<Tag> tags = new ArrayList<Tag>();
        for(int i = 0; i < tagposts.size(); i++){
            tags.add(tagposts.get(i).getTag());
        }
        s.close();
        return tags;
    }

    public TagHeritage getTagHeritage(Heritage heritage, Tag tag){
        Session s = getSessionFactory().openSession();
        TagHeritage tagheritage = (TagHeritage)s
                .createQuery("from TagHeritage where heritage=? and tag=?")
                .setParameter(0, heritage)
                .setParameter(1, tag).uniqueResult();
        s.close();
        return tagheritage;
    }

    public TagPost getTagPost(Post post, Tag tag){
        Session s = getSessionFactory().openSession();
        TagPost tagpost = (TagPost)s
                .createQuery("from TagPost where post=? and tag=?")
                .setParameter(0, post)
                .setParameter(1, tag).uniqueResult();
        s.close();
        return tagpost;
    }

    public boolean doesHeritageHaveTag(Heritage heritage, Tag tag){
        Session s = getSessionFactory().openSession();
        int count = s
                .createQuery("from TagHeritage where heritage=? and tag=?")
                .setParameter(0, heritage)
                .setParameter(1, tag).list().size();
        if(count == 0)
            return false;
        else
            return true;
    }

    public boolean doesPostHaveTag(Post post, Tag tag){
        Session s = getSessionFactory().openSession();
        int count = s
                .createQuery("from TagPost where post=? and tag=?")
                .setParameter(0, post)
                .setParameter(1, tag).list().size();
        if(count == 0)
            return false;
        else
            return true;
    }

    public Tag saveTag(Tag tag){
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        s.save(tag);
        s.getTransaction().commit();
        s.close();
        return tag;
    }

    public TagHeritage saveTagHeritage(Tag tag, Heritage heritage){
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        TagHeritage tagHeritage = new TagHeritage(tag, heritage);
        s.save(tagHeritage);
        s.getTransaction().commit();
        s.close();
        return tagHeritage;
    }

    public TagPost saveTagPost(Tag tag, Post post){
        Session s = getSessionFactory().openSession();
        s.getTransaction().begin();
        TagPost tagPost = new TagPost(tag, post);
        s.save(tagPost);
        s.getTransaction().commit();
        s.close();
        return tagPost;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
