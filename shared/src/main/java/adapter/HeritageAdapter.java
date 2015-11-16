package adapter;

import com.google.gson.*;
import model.Heritage;
import model.Post;

import java.lang.reflect.Type;

/**
 * Created by gokcan on 15.11.2015.
 */
public class HeritageAdapter implements JsonSerializer<Heritage> {

    @Override
    public JsonElement serialize(Heritage heritage, Type type,JsonSerializationContext jsc){
        JsonObject jsonObjectHeritage = new JsonObject();
        jsonObjectHeritage.addProperty("id", heritage.getId());
        jsonObjectHeritage.addProperty("name", heritage.getName());
        jsonObjectHeritage.addProperty("place", heritage.getPlace());
        jsonObjectHeritage.addProperty("postDate", heritage.getPostDate().toString());
        jsonObjectHeritage.addProperty("description", heritage.getDescription());
        JsonArray posts = new JsonArray();
        for(Post post : heritage.getPosts()){
            JsonObject jsonObjectPost = new JsonObject();
            jsonObjectPost.addProperty("id", post.getId());
            jsonObjectPost.addProperty("ownerId", post.getOwner().getId());
            jsonObjectPost.addProperty("type", post.getType());
            jsonObjectPost.addProperty("postDate", post.getPostDate().toString());
            jsonObjectPost.addProperty("lastEditedDate", post.getLastEditedDate().toString());
            jsonObjectPost.addProperty("title", post.getTitle());
            jsonObjectPost.addProperty("content", post.getContent());
            posts.add(jsonObjectPost);
        }
        jsonObjectHeritage.add("posts", posts);
        return jsonObjectHeritage;
    }
}
