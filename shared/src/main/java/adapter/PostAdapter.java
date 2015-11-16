package adapter;

import com.google.gson.*;
import model.Heritage;
import model.Post;

import java.lang.reflect.Type;

/**
 * Created by gokcan on 15.11.2015.
 */
public class PostAdapter implements JsonSerializer<Post> {

    @Override
    public JsonElement serialize(Post post, Type type,JsonSerializationContext jsc){
        JsonObject jsonObjectPost = new JsonObject();
        jsonObjectPost.addProperty("id", post.getId());
        jsonObjectPost.addProperty("ownerId", post.getOwner().getId());
        jsonObjectPost.addProperty("type", post.getType());
        jsonObjectPost.addProperty("postDate", post.getPostDate().toString());
        jsonObjectPost.addProperty("lastEditedDate", post.getLastEditedDate().toString());
        jsonObjectPost.addProperty("title", post.getTitle());
        jsonObjectPost.addProperty("content", post.getContent());
        return jsonObjectPost;
    }

}
