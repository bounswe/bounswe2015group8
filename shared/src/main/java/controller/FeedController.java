package controller;

import adapter.CommentAdapter;
import adapter.HeritageAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dao.MemberDaoImpl;
import model.*;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.*;

import java.util.*;

/**
 * Created by gokcan on 06.12.2015.
 *
 * The controller class which handles the url mappings related to the news feed of the system
 */
@Controller
public class FeedController {
    private static int MIN_LIMIT = 5; // If search gives less than this limit, we need to search for semantically
    private Logger logger = Logger.getLogger(SearchController.class);

    MemberDetailsService memberService;
    PostService postService;
    HeritageService heritageService;
    VoteService voteService;
    TagService tagService;
    FollowService followService;
    FollowHeritageService followHeritageService;
    FollowTagService followTagService;

    public FeedController(){
        memberService = new MemberDetailsService();
        MemberDaoImpl mdao = new MemberDaoImpl();
        mdao.setSessionFactory(Main.getSessionFactory());
        memberService.setMemberDao(mdao);
        postService = new PostService(Main.getSessionFactory());
        heritageService = new HeritageService(Main.getSessionFactory());
        voteService = new VoteService(Main.getSessionFactory());
        tagService = new TagService(Main.getSessionFactory());
        followService = new FollowService(Main.getSessionFactory());
        followHeritageService = new FollowHeritageService(Main.getSessionFactory());
        followTagService = new FollowTagService(Main.getSessionFactory());
    }


    /** The function which handles the url mapping for getting the recently most populer posts.
     *
     * @return the page for post feed filled with recently most popular posts.
     */
    @RequestMapping(value = "/getRecentlyMostPopular")
    public ModelAndView recentPosts(){
        /* Gets the posts that are created within the last week and sorts them in terms of vote count */
        final Session session = Main.getSession();
        List posts = postService.getRecentlyMostPopularPosts();
        List medias = session.createCriteria(Media.class).list();
        List allTags = session.createCriteria(Tag.class).list();
        Map<String, List> allContent = new HashMap<String, List>();

        allContent.put("posts", posts);
        allContent.put("medias", medias);
        allContent.put("allTags", allTags);
        session.close();
        return new ModelAndView("list_post", "allContent", allContent);
    }

    /** The function which handles the url mapping for getting the recently most populer posts based on a heritage.
     *  It works similar to the recentPosts() function which handles the url "/getRecentlyMostPopular", but this one
     *  only returns the posts associated with the heritage which has the provided id
     *
     * @param heritageId: the id of the heritage whose posts we want to see. (long)
     * @return the page for post feed filled with recently most popular posts under the heritage with the given id.
     */
    @RequestMapping(value = "/getRecentlyMostPopular/{heritageId}")
    public ModelAndView recentPosts(@PathVariable long heritageId){
        /* Gets the posts that are created within the last week and sorts them in terms of vote count */
        final Session session = Main.getSession();
        List heritages = new ArrayList<Heritage>();
        heritages.add(heritageService.getHeritageById(heritageId));
        List posts = postService.getRecentlyMostPopularPosts(heritageService.getHeritageById(heritageId));
        List medias = session.createCriteria(Media.class).list();
        List allTags = session.createCriteria(Tag.class).list();
        Map<String, List> allContent = new HashMap<String, List>();

        allContent.put("posts", posts);
        allContent.put("heritages", heritages);
        allContent.put("medias", medias);
        allContent.put("allTags", allTags);
        session.close();
        return new ModelAndView("list_post", "allContent", allContent);
    }

    /** The url mapping handler function for heritage based feed. If the user is anonymous, only the recently added
     *  heritages will appear sorted by popularity. But if the user is logged in, then the system will take his/her
     *  followed heritages, following members and interested areas into account.
     *
     * @return the heritage based feed page.
     */
    @RequestMapping(value = "/feed")
    public ModelAndView feed(){
        final Session session = Main.getSession();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<Heritage> heritages = new ArrayList<>();
        if(username.equals("anonymousUser")){
            heritages = heritageService.getRecentlyMostPopularHeritages();
        }
        else{
            heritages = followHeritageService.getFollowedHeritagesByMemberId(memberService.getMemberByUsername(username).getId());
            heritages = heritageService.sortByPopularity(heritages);

            // Here we will add the heritages with followed tags (Interested in...)
            List<Tag> tags = followTagService.getFollowedTagsByMemberId(memberService.getMemberByUsername(username).getId());
            List<Tag> sortedTags = tagService.sortByCount(tags);
            for(Tag tag : sortedTags){
                heritages.addAll(heritageService.getHeritagesByTag(tag));
            }

            heritages.addAll(heritageService.getRecentlyMostPopularHeritages());
            heritages = heritageService.removeDuplicates(heritages);
        }
        Map<String, List> allContent = new HashMap<String, List>();
        allContent.put("heritages", heritages);
        allContent.put("medias", session.createCriteria(Media.class).list());
        allContent.put("allTags", session.createCriteria(Tag.class).list());
        session.close();
        return new ModelAndView("list_heritage", "allContent", allContent);
    }

    /** The url mapping handler function for post based feed. It works really similar to the function 'feed()' but this one
     *  returns posts instaad of heritages. The logic of which posts would appear on the feed is identical to heritage based
     *  feed.
     *
     * @return the post based feed page.
     */
    @RequestMapping(value = "/feedPosts")
    public ModelAndView feedPosts(){
        final Session session = Main.getSession();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        List<Post> posts = new ArrayList<>();
        if(username.equals("anonymousUser")){
            posts = postService.getRecentlyMostPopularPosts();
        }
        else{
            //logger.info("follow heritage service null: " + followHeritageService == null);
            //logger.info("member service null: " + memberService == null);
            //logger.info("get user name: " + memberService.getMemberByUsername(username) == null);
            List<Heritage> followedHeritages = followHeritageService.getFollowedHeritagesByMemberId(memberService.getMemberByUsername(username).getId());
            for(int i = 0; i < followedHeritages.size(); i++){
                posts.addAll(postService.getRecentlyMostPopularPosts(followedHeritages.get(i)));
            }
            posts = postService.sortByPopularity(posts);

            // Here we will add the heritages with followed tags (Interested in...)

            posts.addAll(postService.getRecentlyMostPopularPosts());
            posts = postService.removeDuplicates(posts);
        }
        Map<String, List> allContent = new HashMap<String, List>();
        allContent.put("posts", posts);
        allContent.put("medias", session.createCriteria(Media.class).list());
        allContent.put("allTags", session.createCriteria(Tag.class).list());
        session.close();
        return new ModelAndView("list_post", "allContent", allContent);
    }

    /** The function which handles the recommendation system. This url is called by an AJAX request and the function
     *  returns some heritages which the user might want to follow. The function takes the user's following members into account
     *  by browsing through their followed heritages and exclude the ones which the user is already following. Also, considering
     *  the user's interested areas, they are sorted and recommended to the user.
     *
     * @return a string in JSON format which contains the name and shortened version of the recommended heritages.
     */
    @RequestMapping(value = "/recommendHeritage", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getRecommendedHeritage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        long memberId = memberService.getMemberByUsername(username).getId();

        List<Heritage> myHeritages = followHeritageService.getFollowedHeritagesByMemberId(memberId);
        List<Heritage> heritagesToRecommend = new ArrayList<>();

        // The heritages of the following members...
        List<Member> followingMembers = followService.getFollowingById(memberId);
        for(int i = 0; i < followingMembers.size(); i++){
            Member followee = followingMembers.get(i);
            for(Heritage heritage : followHeritageService.getFollowedHeritagesByMemberId(followee.getId())){
                if(!myHeritages.contains(heritage)){
                    heritagesToRecommend.add(heritage);
                }
            }
        }

        // The heritages of the heritages that are tagged with the user's following tags...
        List<Tag> followingTags = followTagService.getFollowedTagsByMemberId(memberId);
        for(int i = 0; i < followingTags.size(); i++){
            Tag tag = followingTags.get(i);
            for(Heritage heritage : heritageService.getHeritagesByTag(tag)){
                if(!myHeritages.contains(heritage)){
                    heritagesToRecommend.add(heritage);
                }
            }
        }

        heritagesToRecommend = heritageService.removeDuplicates(heritagesToRecommend);
        heritagesToRecommend = heritageService.sortByPopularity(heritagesToRecommend);
        JsonArray jsonHeritagesRecommend = new JsonArray();
        for(int i = 0; i < heritagesToRecommend.size(); i++){
            JsonObject jsonHeritage = new JsonObject();
            jsonHeritage.addProperty("id", heritagesToRecommend.get(i).getId());
            jsonHeritage.addProperty("title", heritagesToRecommend.get(i).getName());
            String shortDescription = heritagesToRecommend.get(i).getDescription();
            if(shortDescription.length() > 30){
                shortDescription = shortDescription.substring(0,30) + "...";
            }
            jsonHeritage.addProperty("description", shortDescription);
            jsonHeritagesRecommend.add(jsonHeritage);
        }
        return jsonHeritagesRecommend.toString();
    }

}
