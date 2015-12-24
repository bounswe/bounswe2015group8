package dao;

import model.*;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by gokcan on 21.11.2015.
 */
public interface TagDao {
    Tag getTagById(long id);
    Tag getTagByText(String tagText);
    List<Tag> getTagsByText(String tagText);
    Tag getTagByTextAndContext(String tagText, String tagContext);
    String[] getTagContextsByText(String text);
    List<Tag> getTagsByContext(String context);
    boolean doesTagExist(String tagText, String tagContext);

    List<Tag> getTagsByHeritage(Heritage heritage);
    List<Tag> getTagsByPost(Post post);

    TagHeritage getTagHeritage(Heritage heritage, Tag tag);
    TagPost getTagPost(Post post, Tag tag);

    boolean doesHeritageHaveTag(Heritage heritage, Tag tag);
    boolean doesPostHaveTag(Post post, Tag tag);

    Tag saveTag(Tag tag);
    TagHeritage saveTagHeritage(Tag tag, Heritage heritage);
    TagPost saveTagPost(Tag tag, Post post);

    int countHeritagesForTag(Tag tag);
    int countPostsForTag(Tag tag);

    SessionFactory getSessionFactory();
    void setSessionFactory(SessionFactory sessionFactory);
}
