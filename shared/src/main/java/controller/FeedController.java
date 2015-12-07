package controller;

import dao.MemberDaoImpl;
import model.Heritage;
import model.Media;
import model.Post;
import model.Tag;
import org.apache.log4j.Logger;
import org.hibernate.Session;
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
    FollowHeritageService followHeritageService;

    public FeedController(){
        memberService = new MemberDetailsService();
        MemberDaoImpl mdao = new MemberDaoImpl();
        mdao.setSessionFactory(Main.getSessionFactory());
        memberService.setMemberDao(mdao);
        postService = new PostService(Main.getSessionFactory());
        heritageService = new HeritageService(Main.getSessionFactory());
        voteService = new VoteService(Main.getSessionFactory());
        tagService = new TagService(Main.getSessionFactory());
        followHeritageService = new FollowHeritageService(Main.getSessionFactory());
    }


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

            heritages.addAll(heritageService.getRecentlyMostPopularHeritages());
        }
        Map<String, List> allContent = new HashMap<String, List>();
        allContent.put("heritages", heritages);
        allContent.put("medias", session.createCriteria(Media.class).list());
        allContent.put("allTags", session.createCriteria(Tag.class).list());
        session.close();
        return new ModelAndView("list_heritage", "allContent", allContent);
    }

}
