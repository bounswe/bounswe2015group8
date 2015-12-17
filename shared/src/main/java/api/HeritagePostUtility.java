package api;

import controller.Main;
import model.Heritage;
import model.HeritagePost;

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
        heritagePostList = (ArrayList<HeritagePost>) Main.getSession().createCriteria(HeritagePost.class).list();
        return heritagePostList;
    }

    public static ArrayList<Heritage> getHeritageIdOfPost(long postId) {
        ArrayList<Heritage> heritages = new ArrayList<>();
        heritagePostList = getHeritagePostList();
        for (HeritagePost heritagePost : heritagePostList) {
            if (heritagePost.getPost().getId() == postId) {
                heritages.add(heritagePost.getHeritage());
            }
        }
        return heritages;
    }
}
