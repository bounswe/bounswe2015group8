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

    public Tag getTagByText(String text, String context){
        if(context == null)
            return tagDao.getTagByText(text);
        return tagDao.getTagByTextAndContext(text, context);
    }

    public String[] extractTextAndContext(String wholetag){
        String tagContext = null;
        String tagText = wholetag;
        if(wholetag.contains("(") && wholetag.contains(")")){
            tagContext = wholetag.substring(wholetag.indexOf('(')+1, wholetag.indexOf(')'));
            tagText = wholetag.substring(0, wholetag.indexOf('('));
        }
        return new String[]{tagText, tagContext};
    }

    public List<Tag> getTagsByHeritage(Heritage heritage){
        return tagDao.getTagsByHeritage(heritage);
    }

    public List<Tag> getTagsByPost(Post post){
        return tagDao.getTagsByPost(post);
    }

    public TagHeritage getTagHeritage(Heritage heritage, Tag tag) { return tagDao.getTagHeritage(heritage, tag); }

    public TagPost getTagPost(Post post, Tag tag) { return tagDao.getTagPost(post, tag); }

    public boolean doesHeritageHaveTag(Heritage heritage, String tagText, String tagContext){
        if(tagContext == null)
            return tagDao.doesHeritageHaveTag(heritage, tagDao.getTagByText(tagText));
        return tagDao.doesHeritageHaveTag(heritage, tagDao.getTagByTextAndContext(tagText, tagContext));
    }

    public boolean doesPostHaveTag(Post post, String tagText){
        return tagDao.doesPostHaveTag(post, tagDao.getTagByText(tagText));
    }

    public boolean doesPostHaveTag(Post post, String tagText, String tagContext){
        if(tagContext == null)
            return tagDao.doesPostHaveTag(post, tagDao.getTagByText(tagText));
        return tagDao.doesPostHaveTag(post, tagDao.getTagByTextAndContext(tagText, tagContext));
    }

    public TagHeritage addTag(String wholetag, Heritage heritage){
        String[] tagPieces = extractTextAndContext(wholetag);
        String tagText = tagPieces[0];
        String tagContext = tagPieces[1];
        if(doesHeritageHaveTag(heritage, tagText, tagContext))
            return getTagHeritage(heritage, tagDao.getTagByText(tagText));
        Tag tag = getTagByText(tagText, tagContext);
        if(tag == null){
            tag = new Tag(tagText, tagContext);
            tag = tagDao.saveTag(tag);
        }
        return tagDao.saveTagHeritage(tag, heritage);
    }

    public TagPost addTag(String wholetag, Post post){
        String[] tagPieces = extractTextAndContext(wholetag);
        String tagText = tagPieces[0];
        String tagContext = tagPieces[1];
        if(doesPostHaveTag(post, tagText, tagContext))
            return getTagPost(post, tagDao.getTagByText(tagText));
        Tag tag = getTagByText(tagText, tagContext);
        if(tag == null){
            tag = new Tag(tagText, tagContext);
            tag = tagDao.saveTag(tag);
        }
        return tagDao.saveTagPost(tag, post);
    }
}
