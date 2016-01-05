package api;

import controller.Main;
import model.Media;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * Created by Goktug on 06.12.2015.
 */
public class MediaUtility {
    private static ArrayList<Media> mediaList;

    /**
     * Creates and/or updates media list.
     *
     * @return the list of media objects in the database.
     */
    public static ArrayList<Media> getMediaList() {
        if (mediaList == null) {
            mediaList = new ArrayList<Media>();
        }
        final Session session = Main.getSession();
        try{
            mediaList = (ArrayList<Media>) session.createCriteria(Media.class).list();
        }
        catch(Exception e){}
        finally{
            session.close();
            return mediaList;
        }
    }

    public static ArrayList<Media> getMedias(long id, boolean postOrHeritage) {
        ArrayList<Media> mediaList = getMediaList();
        ArrayList<Media> medias = new ArrayList<>();
        for (Media media : mediaList) {
            if (media.getPostOrHeritageId() == id && media.getPostOrHeritage() == postOrHeritage) {
                medias.add(media);
            }
        }

        return medias;
    }
}
