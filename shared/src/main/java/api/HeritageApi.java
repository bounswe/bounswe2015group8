package api;

import adapter.HeritageAdapter;
import adapter.PostAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Heritage;
import model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Goktug on 13.11.2015.
 */
@RestController
public class HeritageApi implements ErrorCodes {
    @Autowired
    private ApplicationContext appContext;
    Gson gson = new Gson();

    @RequestMapping(value = "/api/getHeritageById",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getById(@RequestBody int id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Heritage.class, new HeritageAdapter()).create();
        ArrayList<Heritage> heritages = HeritageUtility.getHeritageList();
        for (Heritage h : heritages) {
            if (id == h.getId()) {
                String json = gson.toJson(h);
                return json;
            }
        }
        return Integer.toString(HERITAGE_DOES_NOT_EXIST);
    }

    @RequestMapping(value = "/api/getAllHeritages",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAll() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Heritage.class, new HeritageAdapter()).create();
        ArrayList<Heritage> heritages = HeritageUtility.getHeritageList();
        String json = gson.toJson(heritages);
        return json;
    }

    @RequestMapping(value = "/api/getHeritagePostsById",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPostsByHeritageId(@RequestBody int id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Post.class, new PostAdapter()).create();

        ArrayList<Heritage> heritages = HeritageUtility.getHeritageList();
        for (Heritage h : heritages) {
            if (id == h.getId()) {
                return gson.toJson(h.getPosts());
            }
        }

        return Integer.toString(HERITAGE_DOES_NOT_EXIST);
    }

    @RequestMapping(value = "/api/getAllPosts",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllPosts() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Post.class, new PostAdapter()).create();
        ArrayList<Post> posts = HeritageUtility.getPostList();
        return gson.toJson(posts);
    }

}
