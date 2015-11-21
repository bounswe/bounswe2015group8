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

    List<Tag> getTagsByHeritage(Heritage heritage);
    List<Tag> getTagsByPost(Post post);

    Tag saveTag(Tag tag);
    TagHeritage saveTagHeritage(Tag tag, Heritage heritage);
    TagPost saveTagPost(Tag tag, Post post);

    SessionFactory getSessionFactory();
    void setSessionFactory(SessionFactory sessionFactory);
}
