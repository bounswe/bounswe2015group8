package service;

import dao.TagDao;
import dao.TagDaoImpl;
import model.*;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gokcan on 21.11.2015.
 * The class for handling the tag related functionality. The layer between the controller and DAO
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

    public String[] getTagContextsByText(String text){
        return tagDao.getTagContextsByText(text);
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
        return addTag(tagText,tagContext,heritage);
    }
    public TagHeritage addTag(String tagText, String tagContext, Heritage heritage){
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
        return addTag(tagText, tagContext, post);
    }
    public TagPost addTag(String tagText, String tagContext, Post post){
        if(doesPostHaveTag(post, tagText, tagContext))
            return getTagPost(post, tagDao.getTagByText(tagText));
        Tag tag = getTagByText(tagText, tagContext);
        if(tag == null){
            tag = new Tag(tagText, tagContext);
            tag = tagDao.saveTag(tag);
        }
        return tagDao.saveTagPost(tag, post);
    }

    /// The service function for getting the semantically related tags of a tag
    /**
     *
     * @param tagText: the text of the tag (String)
     * @param tagContext: the context of the tag (String)
     * @return a list of the tags who are semantically related to the tag with specified text and contexts (list of strings)
     */
    public List<Tag> getSemanticallyRelatedTags(String tagText, String tagContext){
        //String tagText = tag.getTagText();
        //String tagContext = tag.getTagContext();
        List<Tag> relatedTags = new ArrayList<Tag>();
        List<Tag> tagsSameContext = tagDao.getTagsByContext(tagContext);
        if(tagsSameContext == null){
            return relatedTags;
        }
        for(Tag relatedTag : tagsSameContext){
            if(!tagText.equals(relatedTag.getTagText()) && !relatedTags.contains(relatedTag)){
                relatedTags.add(relatedTag);
            }
        }
        return relatedTags;
    }

    /// The service function for getting the number of heritages which are tagged with the specified tag
    /**
     * @param tag: the tag (Tag object)
     * @return the number of heritages which are tagged with the specified tag (int)
     */
    public int countHeritagesForTag(Tag tag){
        return tagDao.countHeritagesForTag(tag);
    }

    /// The service function for getting the number of posts which are tagged with the specified tag
    /**
     * @param tag: the tag (Tag object)
     * @return the number of posts which are tagged with the specified tag (int)
     */
    public int countPostsForTag(Tag tag){
        return tagDao.countPostsForTag(tag);
    }

    /// The service function for getting the total number of heritages and posts which are tagged with the specified tag
    /**
     * @param tag: the tag (Tag object)
     * @return the total number of heritages and posts which are tagged with the specified tag (int)
     */
    public int countTag(Tag tag){
        return countHeritagesForTag(tag) + countPostsForTag(tag);
    }

    /// The service function for sorting a list of tags based on total number of posts and heritages which are tagged with them
    /**
     * @param tags: the list of tags which we want to sort (list of tags)
     * @return the sorted list of tags (list of tags)
     */
    public List<Tag> sortByCount(List<Tag> tags){
        int size = tags.size();
        List<Tag> sortedTags = new ArrayList<Tag>();

        for(int i = 0; i < size; i++){
            int maxCount = 0;
            Tag mostPopularTag = null;
            for(int j = 0; j < tags.size(); j++){
                Tag tag = tags.get(j);
                int count = countTag(tags.get(j));
                if(count > maxCount){
                    mostPopularTag = tag;
                    maxCount = count;
                }
            }
            sortedTags.add(mostPopularTag);
            tags.remove(mostPopularTag);
        }
        return sortedTags;
    }
}
