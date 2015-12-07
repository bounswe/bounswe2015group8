package api;

import adapter.TagAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * Created by Goktug on 07.12.2015.
 */
@RestController
public class TagApi {
    @Autowired
    private ApplicationContext appContext;

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
}
