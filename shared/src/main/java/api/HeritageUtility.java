package api;

import controller.Main;
import model.Heritage;
import model.Post;
import service.FollowHeritageService;
import service.HeritageService;
import service.VoteService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Goktug on 13.11.2015.
 */
public class HeritageUtility {
    private static ArrayList<Heritage> heritageList;
    static HeritageService heritageService;
    static FollowHeritageService followHeritageService;
    static VoteService voteService;

    /**
     * Creates and/or updates heritage list.
     *
     * @return the list of heritage objects in the database.
     */
    public static ArrayList<Heritage> getHeritageList() {
        if (heritageList == null) {
            heritageList = new ArrayList<Heritage>();
        }
        heritageList = (ArrayList<Heritage>) Main.getSession().createCriteria(Heritage.class).list();
        return heritageList;
    }

    /**
     * Finds heritage with the given id and returns it
     * @param id of heritage
     * @return heritage with given id
     */
    public static Heritage getHeritageById(long id) {
        heritageList = getHeritageList();
        for (Heritage heritage : heritageList) {
            if (heritage.getId() == id) {
                return heritage;
            }
        }
        return null;
    }

    /**
     * Returns post belonging to heritage
     * @param id of the heritage
     * @return all posts related to heritage
     */
    public static Collection<Post> getPostsByHeritageId(long id) {
        heritageList = getHeritageList();
        for (Heritage heritage : heritageList) {
            if (id == heritage.getId()) {
                return heritage.getPosts();
            }
        }
        return null;
    }

    /**
     * Gets heritage service
     * @return heritage service
     */
    public static HeritageService getHeritageService() {
        if (heritageService == null) {
            heritageService = new HeritageService(Main.getSessionFactory());
        }
        return heritageService;
    }

    /**
     * Searches for the heritages with names that contain the parameter
     * @param name the search string
     * @return the heritages with names containing parameter
     */
    public static ArrayList<Heritage> searchHeritageByName(String name) {
        heritageList = getHeritageList();
        ArrayList<Heritage> heritagesWithName = new ArrayList<>();
        name = name.toLowerCase();
        for (Heritage heritage : heritageList) {
            if (heritage.getName().toLowerCase().contains(name)) {
                heritagesWithName.add(heritage);
            }
        }
        return heritagesWithName;
    }

    public static List<Heritage> heritageNewsfeed(long id) {
        List<Heritage> heritages = new ArrayList<>();

        heritages = followHeritageService.getFollowedHeritagesByMemberId(id);
        heritages = heritageService.sortByPopularity(heritages);

        // Here we will add the heritages with followed tags (Interested in...)

        heritages.addAll(heritageService.getRecentlyMostPopularHeritages());
        heritages = heritageService.removeDuplicates(heritages);
        return heritages;
    }

    /**
     * Gets vote service
     * @return vote service
     */
    public static VoteService getVoteService() {
        if (voteService == null) {
            voteService = new VoteService(Main.getSessionFactory());
        }
        return voteService;
    }

}
