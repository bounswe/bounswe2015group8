package api;

import adapter.HeritageAdapter;
import adapter.PostAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Heritage;
import model.Post;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Goktug on 13.11.2015.
 */
@RestController
public class HeritageApi implements ErrorCodes {
    Gson gson = new Gson();

    /**
     * Returns heritage with given id as a json
     * @param id of the heritage
     * @return heritage's json representation
     */
    @RequestMapping(value = "/api/getHeritageById",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHeritageById(@RequestBody int id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Heritage.class, new HeritageAdapter()).create();
        Heritage heritage = HeritageUtility.getHeritageById(id);
        if (heritage == null) {
            return Integer.toString(HERITAGE_DOES_NOT_EXIST);
        }
        String json = gson.toJson(heritage);
        return json;
    }

    /**
     * Gets all heritages from the database
     * @return all heritages as json
     */
    @RequestMapping(value = "/api/getAllHeritages",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAll() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Heritage.class, new HeritageAdapter()).create();
        ArrayList<Heritage> heritages = HeritageUtility.getHeritageList();
        String json = gson.toJson(heritages);
        return json;
    }

    /**
     * Gets given heritage's posts as JSON
     * @param id of the heritage
     * @return heritage's posts as JSON
     */
    @RequestMapping(value = "/api/getHeritagePostsById",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPostsByHeritageId(@RequestBody int id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Post.class, new PostAdapter()).create();

        Collection<Post> posts = HeritageUtility.getPostsByHeritageId(id);
        if (posts == null) {
            return Integer.toString(HERITAGE_DOES_NOT_EXIST);
        }
        return gson.toJson(posts);
    }

    /**
     * Creates heritage by getting necessary parameters from the user
     * @param apiHeritage heritage object containing information
     * @return created heritage's id
     */
    @RequestMapping(value = "/api/createHeritage",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public long createHeritage(@RequestBody Heritage apiHeritage) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        java.util.Date now = new java.util.Date();

        String name = apiHeritage.getName();
        String place = apiHeritage.getPlace();
        String description = apiHeritage.getDescription();
        MultipartFile media = null;
        Heritage heritage = HeritageUtility.getHeritageService().saveHeritage(name, place, description, new Timestamp(now.getTime()));

        return heritage.getId();
    }

    /**
     * Gets a heritage as parameter, adds new posts to the current heritage in the database
     * @param heritage with new heritage list
     * @return updated post in the database
     */
    @RequestMapping(value = "/api/addPostsToHeritage",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String addPostsToHeritage(@RequestBody Heritage heritage) {
        Heritage currentHeritage = HeritageUtility.getHeritageById(heritage.getId());
        if (currentHeritage == null) {
            return "Heritage does not exist.";
        }
        ArrayList<Long> postIds = currentHeritage.getPostIds();
        for (Post post : heritage.getPosts()) {
            if (!postIds.contains(post.getId())) {
                currentHeritage.getPosts().add(post);
            }
        }
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Heritage.class, new HeritageAdapter()).create();
        return gson.toJson(currentHeritage);
    }

    /**
     * Gets the JSON representation of heritages with given name
     * @param name given by user
     * @return the heritage list
     */
    @RequestMapping(value = "/api/searchByHeritageName",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String searchByHeritageName(@RequestBody String name) {
        ArrayList<Heritage> heritagesWithName = HeritageUtility.searchHeritageByName(name);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Heritage.class, new HeritageAdapter()).create();
        String json = gson.toJson(heritagesWithName);
        return json;
    }

}
