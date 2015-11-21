package service;

import dao.TagDao;
import dao.TagDaoImpl;
import model.*;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by gokcan on 21.11.2015.
 */
public class TagService {
    private TagDao tagDao;

    public TagService(SessionFactory sessionFactory){
        tagDao = new TagDaoImpl();
        tagDao.setSessionFactory(sessionFactory);
    }

    public Tag getTagById(long id){
        return tagDao.getTagById(id);
    }

    public Tag getTagByText(String text){
        return tagDao.getTagByText(text);
    }

    public List<Tag> getTagsByHeritage(Heritage heritage){
        return tagDao.getTagsByHeritage(heritage);
    }

    public List<Tag> getTagsByPost(Post post){
        return tagDao.getTagsByPost(post);
    }

    public TagHeritage addTag(String tagText, Heritage heritage){
        Tag tag = getTagByText(tagText);
        if(tag == null){
            tag = new Tag(tagText);
            tag = tagDao.saveTag(tag);
        }
        return tagDao.saveTagHeritage(tag, heritage);
    }

    public TagPost addTag(String tagText, Post post){
        Tag tag = getTagByText(tagText);
        if(tag == null){
            tag = new Tag(tagText);
            tag = tagDao.saveTag(tag);
        }
        return tagDao.saveTagPost(tag, post);
    }
}
