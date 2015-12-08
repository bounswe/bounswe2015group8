package api;

import adapter.TagAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.Main;
import model.Heritage;
import model.Post;
import model.Tag;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.HeritageService;
import service.PostService;
import service.TagService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Goktug on 07.12.2015.
 */
@RestController
public class TagApi {
    @Autowired
    private ApplicationContext appContext;
    private TagService tagService = new TagService(Main.getSessionFactory());
    private PostService postService = new PostService(Main.getSessionFactory());
    private HeritageService heritageService = new HeritageService(Main.getSessionFactory());

    @RequestMapping(value = "/api/getAllTags",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllTags() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Tag.class, new TagAdapter()).create();
        ArrayList<Tag> tags = TagUtility.getTagList();
        return gson.toJson(tags);
    }

    @RequestMapping(value = "/api/getTagsStartingWith",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllTags(@RequestBody String startingPart) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Tag.class, new TagAdapter()).create();
        ArrayList<Tag> tags = TagUtility.getTagList();
        ArrayList<Tag> startsWith = new ArrayList<>();
        for (Tag tag : tags) {
            if (tag.getTagText().toLowerCase().startsWith(startingPart.toLowerCase())) {
                startsWith.add(tag);
            }
        }
        return gson.toJson(startsWith);
    }

    @RequestMapping(value = "/api/updatePostTags",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updatePostTags(@RequestBody Post post) {
        Collection<Tag> postTags = post.getTags();
        Post dbPost = postService.getPostById(post.getId());
        for (Tag tag : postTags) {
            if (tagService.getTagById(tag.getId()) == null) {
                tagService.addTag(tag.getTagText(), tag.getTagContext(), dbPost);
            }
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Tag.class, new TagAdapter()).create();
        return gson.toJson(dbPost.getTags());
    }

    @RequestMapping(value = "/api/updateHeritageTags",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateHeritageTags(@RequestBody Heritage heritage) {
        Collection<Tag> heritageTags = heritage.getTags();
        Heritage dbHeritage = heritageService.getHeritageById(heritage.getId());
        for (Tag tag : heritageTags) {
            if (tagService.getTagById(tag.getId()) == null) {
                tagService.addTag(tag.getTagText(), tag.getTagContext(), dbHeritage);
            }
        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Tag.class, new TagAdapter()).create();
        return gson.toJson(dbHeritage.getTags());
    }

    @RequestMapping(value = "/api/editTag",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String editTag(@RequestBody Tag tag) {
        Tag dbTag = tagService.getTagById(tag.getId());
        if (!(dbTag.getTagText().equals(tag.getTagText()))) {
            dbTag.setTagText(tag.getTagText());
        }
        final Session session = Main.getSession();
        session.getTransaction().begin();
        session.update(dbTag);
        session.getTransaction().commit();
        session.close();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Tag.class, new TagAdapter()).create();
        return gson.toJson(dbTag);
    }
}
