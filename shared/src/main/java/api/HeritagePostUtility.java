package api;

import controller.Main;
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

    public static ArrayList<Long> getHeritageIdOfPost(long postId) {
        ArrayList<Long> heritageIds = new ArrayList<>();
        heritagePostList = getHeritagePostList();
        for (HeritagePost heritagePost : heritagePostList) {
            if (heritagePost.getPost().getId() == postId) {
                heritageIds.add(heritagePost.getHeritage().getId());
            }
        }
        return heritageIds;
    }
}
