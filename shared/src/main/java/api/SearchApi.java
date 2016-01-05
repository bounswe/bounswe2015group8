package api;

import adapter.HeritageAdapter;
import adapter.PostAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.Main;
import controller.SearchController;
import model.Heritage;
import model.Post;
import model.Tag;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.HeritageService;
import service.PostService;
import service.TagService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gokcan on 05.01.2016.
 */
@RestController
public class SearchApi {
    private static Logger logger = Logger.getLogger(SearchApi.class);

    public static int MIN_LIMIT = 5; // If search gives less than this limit, we need to search for semantically
    private TagService tagService = new TagService(Main.getSessionFactory());
    private PostService postService = new PostService(Main.getSessionFactory());
    Gson gson = new Gson();

    @RequestMapping(value = "/api/searchPostsByTag/{wholetag}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchPostsByTag(@PathVariable String wholetag){
        List<Post> posts = PostUtility.getSemanticallyRelatedPosts(wholetag);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Post.class, new PostAdapter()).create();
        return gson.toJson(posts).toString();
    }

    @RequestMapping(value = "/api/searchHeritagesByTag/{wholetag}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchHeritagesByTag(@PathVariable String wholetag){
        //final Session session = Main.getSession();
        List<Heritage> heritages = HeritageUtility.getSemanticallyRelatedHeritages(wholetag);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Heritage.class, new HeritageAdapter()).create();
        return gson.toJson(heritages);
    }

}
