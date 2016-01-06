package api;

import controller.Main;
import model.Heritage;
import model.HeritagePost;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * Created by Goktug on 06.12.2015.
 */
public class HeritagePostUtility {
    private static ArrayList<HeritagePost> heritagePostList;

    public static ArrayList<HeritagePost> getHeritagePostList() {
        if (heritagePostList == null) {
            heritagePostList = new ArrayList<>();
        }
        final Session session = Main.getSession();
        try{
            heritagePostList = (ArrayList<HeritagePost>) session.createCriteria(HeritagePost.class).list();
            for(HeritagePost heritagePost : heritagePostList){
                Main.initializeAndUnproxy(heritagePost);
                Main.initializeAndUnproxy(heritagePost.getHeritage());
                Main.initializeAndUnproxy(heritagePost.getPost());
            }
        }
        catch(Exception e){}
        finally {
            session.close();
            return heritagePostList;
        }
    }

    public static ArrayList<Heritage> getHeritageIdOfPost(long postId) {
        ArrayList<Heritage> heritages = new ArrayList<>();
        heritagePostList = getHeritagePostList();
        for (HeritagePost heritagePost : heritagePostList) {
            if (heritagePost.getPost().getId() == postId) {
                Heritage heritage = heritagePost.getHeritage();
                //Hibernate.initialize(heritage);
                heritages.add(heritage);
            }
        }
        return heritages;
    }
}
