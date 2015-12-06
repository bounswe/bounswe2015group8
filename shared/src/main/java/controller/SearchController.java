package controller;

import dao.MemberDaoImpl;
import model.Heritage;
import model.Media;
import model.Post;
import model.Tag;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.*;

import java.util.*;

/**
 * Created by gokcan on 03.12.2015.
 */
@Controller
public class SearchController {
    private static int MIN_LIMIT = 5; // If search gives less than this limit, we need to search for semantically
    private Logger logger = Logger.getLogger(SearchController.class);

    MemberDetailsService memberService;
    PostService postService;
    HeritageService heritageService;
    CommentService commentService;
    VoteService voteService;
    TagService tagService;

    public SearchController(){
        memberService = new MemberDetailsService();
        MemberDaoImpl mdao = new MemberDaoImpl();
        mdao.setSessionFactory(Main.getSessionFactory());
        memberService.setMemberDao(mdao);
        postService = new PostService(Main.getSessionFactory());
        heritageService = new HeritageService(Main.getSessionFactory());
        commentService = new CommentService(Main.getSessionFactory());
        voteService = new VoteService(Main.getSessionFactory());
        tagService = new TagService(Main.getSessionFactory());
    }

    @RequestMapping(value = "/searchByTag/{wholetag}")
    public ModelAndView searchByTag(@PathVariable String wholetag){
        final Session session = Main.getSession();

        String[] tagPieces = tagService.extractTextAndContext(wholetag);
        Tag tag = tagService.getTagByText(tagPieces[0], tagPieces[1]);
        List<Post> posts = postService.getPostsByTag(tag);
        List medias = session.createCriteria(Media.class).list();
        List allTags = session.createCriteria(Tag.class).list();

        List tags = new ArrayList<Tag>();
        tags.add(tag);

        // Check whether there are enough results
        if(posts.size() < MIN_LIMIT){
            List<Tag> additionalTags = tagService.sortByCount(tagService.getSemanticallyRelatedTags(tag));
            for(Tag additionalTag : additionalTags){
                List<Post> additionalPosts = postService.getPostsByTag(additionalTag);
                for(int i = 0; i < additionalPosts.size(); i++){
                    posts.addAll(additionalPosts);
                }
                if(posts.size() >= MIN_LIMIT)
                    break;
            }
        }

        posts = postService.removeDuplicates(posts);

        Map<String, List> allContent = new HashMap<String, List>();
        allContent.put("searchedTags", tags);
        allContent.put("posts", posts);
        allContent.put("medias", medias);
        allContent.put("allTags", allTags);
        return new ModelAndView("list_post", "allContent", allContent);
    }

    @RequestMapping(value = "/searchHeritageByTag/{wholetag}")
    public ModelAndView searchHeritageByTag(@PathVariable String wholetag){
        final Session session = Main.getSession();

        String[] tagPieces = tagService.extractTextAndContext(wholetag);
        Tag tag = tagService.getTagByText(tagPieces[0], tagPieces[1]);
        List heritages = heritageService.getHeritagesByTag(tag);
        List medias = session.createCriteria(Media.class).list();
        List allTags = session.createCriteria(Tag.class).list();

        List tags = new ArrayList<Tag>();
        tags.add(tag);

        Map<String, List> allContent = new HashMap<String, List>();
        allContent.put("searchedTags", tags);
        allContent.put("heritages", heritages);
        allContent.put("medias", medias);
        allContent.put("allTags", allTags);
        return new ModelAndView("list_heritage", "allContent", allContent);
    }

    @RequestMapping(value = "/suggestTagContexts")
    @ResponseBody
    public String[] suggestTagContexts(@RequestParam(value = "tagText") String tagText){
        return tagService.getTagContextsByText(tagText);
    }

    @RequestMapping(value = "/searchByMember/{username}")
    public ModelAndView searchByMember(@PathVariable String username){
        final Session session = Main.getSession();
        List posts = postService.getPostsByMember(memberService.getMemberByUsername(username));
        List medias = session.createCriteria(Media.class).list();
        List allTags = session.createCriteria(Tag.class).list();
        Map<String, List> allContent = new HashMap<String, List>();
        List members = new ArrayList<String>();
        members.add(username);

        allContent.put("posts", posts);
        allContent.put("medias", medias);
        allContent.put("allTags", allTags);
        allContent.put("searchedMembers", members);
        session.close();
        return new ModelAndView("list_post", "allContent", allContent);
    }

    @RequestMapping(value = "/searchByTitle/{title}")
    public ModelAndView searchByTitle(@PathVariable String title){
        final Session session = Main.getSession();
        List posts = postService.getPostsContainTitle(title);
        List medias = session.createCriteria(Media.class).list();
        List allTags = session.createCriteria(Tag.class).list();
        Map<String, List> allContent = new HashMap<String, List>();
        List members = new ArrayList<String>();

        allContent.put("posts", posts);
        allContent.put("medias", medias);
        allContent.put("allTags", allTags);
        allContent.put("searchedMembers", members);
        session.close();
        return new ModelAndView("list_post", "allContent", allContent);
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

}
