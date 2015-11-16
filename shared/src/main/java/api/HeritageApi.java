package api;

import adapter.PostAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Heritage;
import model.Member;
import model.Post;
import adapter.HeritageAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
import java.util.Date;

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

    @RequestMapping(value = "/api/getPostById",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPostById(@RequestBody int id) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.registerTypeAdapter(Post.class, new PostAdapter()).create();
        ArrayList<Post> posts = HeritageUtility.getPostList();
        for (Post p : posts) {
            if (p.getId() == id) {
                return gson.toJson(p);
            }
        }
        return Integer.toString(POST_DOES_NOT_EXIST);
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

    @RequestMapping(value="/api/createPost",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public long createPost(@RequestBody String json) {
        PostApiModel apiModel = gson.fromJson(json, PostApiModel.class);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        Date now = new Date();

        Member m = MemberUtility.getMemberService().getMemberById(apiModel.getOwnerId());
        Heritage heritage = HeritageUtility.getHeritageService().getHeritageById(apiModel.getHeritageId());

        Post post = HeritageUtility.getPostService().savePost(m, 0, new Timestamp(now.getTime()), apiModel.getTitle(), apiModel.getContent(), heritage);

        return post.getId();
    }


}