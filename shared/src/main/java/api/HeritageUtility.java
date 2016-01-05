package api;

import controller.Main;
import model.Heritage;
import model.Post;
import model.Tag;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.proxy.HibernateProxy;
import service.FollowHeritageService;
import service.HeritageService;
import service.TagService;
import service.VoteService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Goktug on 13.11.2015.
 */
public class HeritageUtility {
    private static Logger logger = Logger.getLogger(HeritageUtility.class);

    private static ArrayList<Heritage> heritageList;
    static HeritageService heritageService;
    static FollowHeritageService followHeritageService;
    static TagService tagService;
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
        if(followHeritageService == null){
            followHeritageService = new FollowHeritageService(Main.getSessionFactory());
        }
        List<Heritage> heritages = new ArrayList<>();

        heritages = followHeritageService.getFollowedHeritagesByMemberId(id);
        heritages = getHeritageService().sortByPopularity(heritages);

        // Here we will add the heritages with followed tags (Interested in...)

        heritages.addAll(getHeritageService().getRecentlyMostPopularHeritages());
        heritages = getHeritageService().removeDuplicates(heritages);
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

    public static TagService getTagService() {
        if (tagService == null) {
            tagService = new TagService(Main.getSessionFactory());
        }
        return tagService;
    }

    public static ArrayList<Heritage> getSemanticallyRelatedHeritages(String wholetag){
        if(tagService == null){
            tagService = new TagService(Main.getSessionFactory());
        }
        String[] tagPieces = tagService.extractTextAndContext(wholetag);
        Tag tag = tagService.getTagByText(tagPieces[0], tagPieces[1]);
        ArrayList<Heritage> heritages;
        if(tag != null)
            heritages = (ArrayList<Heritage>)getHeritageService().getHeritagesByTag(tag);
        else
            heritages = new ArrayList<>();


        // Check whether there are enough results
        if(heritages.size() < SearchApi.MIN_LIMIT){
            List<Tag> additionalTags = tagService.sortByCount(tagService.getSemanticallyRelatedTags(tagPieces[0], tagPieces[1]));
            for(Tag additionalTag : additionalTags){
                List<Heritage> additionalHeritages = getHeritageService().getHeritagesByTag(additionalTag);
                for(int i = 0; i < additionalHeritages.size(); i++){
                    heritages.addAll(additionalHeritages);
                }
                if(heritages.size() >= SearchApi.MIN_LIMIT)
                    break;
            }
        }

        return (ArrayList<Heritage>)getHeritageService().removeDuplicates(heritages);
    }
}
