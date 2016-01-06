package api;

import controller.Main;
import model.Tag;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * Created by Goktug on 06.12.2015.
 */
public class TagUtility {
    private static ArrayList<Tag> tagList;

    /**
     * Creates and/or updates media list.
     *
     * @return the list of media objects in the database.
     */
    public static ArrayList<Tag> getTagList() {
        if (tagList == null) {
            tagList = new ArrayList<Tag>();
        }
        final Session session = Main.getSession();
        try {
            tagList = (ArrayList<Tag>) session.createCriteria(Tag.class).list();
        }
        catch(Exception e) {}
        finally {
            session.close();
            return tagList;
        }
    }
}
