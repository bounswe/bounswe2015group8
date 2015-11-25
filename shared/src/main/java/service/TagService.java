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

    public TagHeritage getTagHeritage(Heritage heritage, Tag tag) { return tagDao.getTagHeritage(heritage, tag); }

    public TagPost getTagPost(Post post, Tag tag) { return tagDao.getTagPost(post, tag); }

    public boolean doesHeritageHaveTag(Heritage heritage, String tagText){
        return tagDao.doesHeritageHaveTag(heritage, tagDao.getTagByText(tagText));
    }

    public boolean doesPostHaveTag(Post post, String tagText){
        return tagDao.doesPostHaveTag(post, tagDao.getTagByText(tagText));
    }

    public TagHeritage addTag(String tagText, Heritage heritage){
        if(doesHeritageHaveTag(heritage, tagText))
            return getTagHeritage(heritage, tagDao.getTagByText(tagText));
        Tag tag = getTagByText(tagText);
        if(tag == null){
            tag = new Tag(tagText);
            tag = tagDao.saveTag(tag);
        }
        return tagDao.saveTagHeritage(tag, heritage);
    }

    public TagPost addTag(String tagText, Post post){
        if(doesPostHaveTag(post, tagText))
            return getTagPost(post, tagDao.getTagByText(tagText));
        Tag tag = getTagByText(tagText);
        if(tag == null){
            tag = new Tag(tagText);
            tag = tagDao.saveTag(tag);
        }
        return tagDao.saveTagPost(tag, post);
    }
}
