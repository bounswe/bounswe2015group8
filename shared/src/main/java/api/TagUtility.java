package api;

import controller.Main;
import model.Tag;

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
        tagList = (ArrayList<Tag>) Main.getSession().createCriteria(Tag.class).list();
        return tagList;
    }
}
