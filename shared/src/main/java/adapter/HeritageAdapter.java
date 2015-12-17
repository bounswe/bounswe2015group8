package adapter;

import api.MediaUtility;
import com.google.gson.*;
import model.Heritage;
import model.Media;
import model.Post;
import model.Tag;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by gokcan on 15.11.2015.
 */
public class HeritageAdapter implements JsonSerializer<Heritage> {
    private MediaAdapter mediaAdapter = new MediaAdapter();
    private TagAdapter tagAdapter = new TagAdapter();

    @Override
    public JsonElement serialize(Heritage heritage, Type type,JsonSerializationContext jsc){
        Gson gson = new Gson();
        JsonObject jsonObjectHeritage = new JsonObject();
        jsonObjectHeritage.addProperty("id", heritage.getId());
        jsonObjectHeritage.addProperty("name", heritage.getName());
        jsonObjectHeritage.addProperty("place", heritage.getPlace());
        jsonObjectHeritage.addProperty("postDate", heritage.getPostDate().toString());
        jsonObjectHeritage.addProperty("description", heritage.getDescription());
        JsonArray mediasJsonArray = new JsonArray();
        ArrayList<Media> medias = MediaUtility.getMedias(heritage.getId(), true);
        for (Media media : medias) {
            mediasJsonArray.add(mediaAdapter.serialize(media, type, jsc));
        }
        jsonObjectHeritage.add("media", mediasJsonArray);
        JsonArray posts = new JsonArray();
        for(Post post : heritage.getPosts()){
            JsonObject jsonObjectPost = new JsonObject();
            jsonObjectPost.addProperty("id", post.getId());
            jsonObjectPost.addProperty("ownerId", post.getOwner().getId());
            jsonObjectPost.addProperty("username",post.getOwner().getUsername());
            jsonObjectPost.addProperty("type", post.getType());
            jsonObjectPost.addProperty("postDate", post.getPostDate().toString());
            jsonObjectPost.addProperty("lastEditedDate", post.getLastEditedDate().toString());
            jsonObjectPost.addProperty("title", post.getTitle());
            jsonObjectPost.addProperty("content", post.getContent());
            JsonArray postMediasJsonArray = new JsonArray();
            ArrayList<Media> postMedias = MediaUtility.getMedias(post.getId(), false);
            for (Media media : postMedias) {
                postMediasJsonArray.add(mediaAdapter.serialize(media, type, jsc));
            }
            jsonObjectPost.add("media", postMediasJsonArray);
            posts.add(jsonObjectPost);
        }
        jsonObjectHeritage.add("posts", posts);
        JsonArray tags = new JsonArray();
        for (Tag tag : heritage.getTags()) {
            tags.add(tagAdapter.serialize(tag, type, jsc));
        }
        jsonObjectHeritage.add("tags", tags);
        return jsonObjectHeritage;
    }
}
