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
 * The class for handling the requests related to searching
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

     /// The controller for searching posts by tag
	 /**
	 *
	 * @param wholetag: the tagtext with tagcontext (String)
	 * @return the view for listing posts with only the relevant tags
	 */
    @RequestMapping(value = "/searchByTag/{wholetag}")
    public ModelAndView searchByTag(@PathVariable String wholetag){
        final Session session = Main.getSession();

        String[] tagPieces = tagService.extractTextAndContext(wholetag);
        Tag tag = tagService.getTagByText(tagPieces[0], tagPieces[1]);
        List<Post> posts;
        if(tag != null)
            posts = postService.getPostsByTag(tag);
        else
            posts = new ArrayList<>();
        List medias = session.createCriteria(Media.class).list();
        List allTags = session.createCriteria(Tag.class).list();

        List tags = new ArrayList<Tag>();
        List wholetags = new ArrayList<String>();
        tags.add(tag);
        wholetags.add(wholetag);

        // Check whether there are enough results
        if(posts.size() < MIN_LIMIT){
            List<Tag> additionalTags = tagService.sortByCount(tagService.getSemanticallyRelatedTags(tagPieces[0], tagPieces[1]));
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
        allContent.put("wholeTags", wholetags);
        allContent.put("posts", posts);
        allContent.put("medias", medias);
        allContent.put("allTags", allTags);
        session.close();
        return new ModelAndView("list_post", "allContent", allContent);
    }

    /// The controller for searching heritages by tag
    /**
     *
     * @param wholetag: the tagtext with tagcontext (String)
     * @return the view for listing heritages with only the relevant tags
     */
    @RequestMapping(value = "/searchHeritageByTag/{wholetag}")
    public ModelAndView searchHeritageByTag(@PathVariable String wholetag){
        final Session session = Main.getSession();


        List tags = new ArrayList<Tag>();
        List wholetags = new ArrayList<String>();
        String[] tagPieces = tagService.extractTextAndContext(wholetag);
        Tag tag = tagService.getTagByText(tagPieces[0], tagPieces[1]);
        List<Heritage> heritages;
        if(tag != null)
            heritages = heritageService.getHeritagesByTag(tag);
        else
            heritages = new ArrayList<>();
        List medias = session.createCriteria(Media.class).list();
        List allTags = session.createCriteria(Tag.class).list();

        tags.add(tag);
        wholetags.add(wholetag);

        // Check whether there are enough results
        if(heritages.size() < MIN_LIMIT){
            List<Tag> additionalTags = tagService.sortByCount(tagService.getSemanticallyRelatedTags(tagPieces[0], tagPieces[1]));
            for(Tag additionalTag : additionalTags){
                List<Heritage> additionalHeritages = heritageService.getHeritagesByTag(additionalTag);
                for(int i = 0; i < additionalHeritages.size(); i++){
                    heritages.addAll(additionalHeritages);
                }
                if(heritages.size() >= MIN_LIMIT)
                    break;
            }
        }

        heritages = heritageService.removeDuplicates(heritages);

        Map<String, List> allContent = new HashMap<String, List>();
        allContent.put("searchedTags", tags);
        allContent.put("wholeTags", wholetags);
        allContent.put("heritages", heritages);
        allContent.put("medias", medias);
        allContent.put("allTags", allTags);
        session.close();
        return new ModelAndView("list_heritage", "allContent", allContent);
    }

    /// The controller for requesting tag context suggestions based on a tag text
    /**
     * @param tagText: the text of a tag without its context (String)
     * @return an array of the suggested contexts based on the specified tag text. (array of strings)
     */
    @RequestMapping(value = "/suggestTagContexts")
    @ResponseBody
    public String[] suggestTagContexts(@RequestParam(value = "tagText") String tagText){
        return tagService.getTagContextsByText(tagText);
    }

    /// The controller for searching posts by member
    /**
     *
     * @param username: the username of the posts we want to see (String)
     * @return the view for listing posts only whose owner is the specified user
     */
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

    /// The controller for searching posts by title
    /**
     *
     * @param title: the title of the posts we want to see (String)
     * @return the view for listing posts only which has the specified title
     */
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

}
