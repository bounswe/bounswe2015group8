package adapter;

import api.HeritagePostUtility;
import api.MediaUtility;
import com.google.gson.*;
import model.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by gokcan on 15.11.2015.
 */
public class PostAdapter implements JsonSerializer<Post> {
    private CommentAdapter commentAdapter = new CommentAdapter();
    private TagAdapter tagAdapter = new TagAdapter();
    private MediaAdapter mediaAdapter = new MediaAdapter();

    @Override
    public JsonElement serialize(Post post, Type type,JsonSerializationContext jsc){
        Gson gson = new Gson();
        JsonObject jsonObjectPost = new JsonObject();
        jsonObjectPost.addProperty("id", post.getId());
        jsonObjectPost.addProperty("username", post.getOwner().getUsername());
        ArrayList<Heritage> heritages = HeritagePostUtility.getHeritageIdOfPost(post.getId());
        JsonArray jsonHeritages = new JsonArray();
        for (Heritage heritage : heritages) {
            JsonObject jsonObjectHeritage = new JsonObject();
            jsonObjectHeritage.addProperty("id", heritage.getId());
            jsonObjectHeritage.addProperty("name", heritage.getName());
            jsonObjectHeritage.addProperty("place", heritage.getPlace());
            jsonObjectHeritage.addProperty("postDate", heritage.getPostDate().toString());
            jsonObjectHeritage.addProperty("description", heritage.getDescription());
            jsonHeritages.add(jsonObjectHeritage);
        }
        jsonObjectPost.add("heritages", jsonHeritages);
        jsonObjectPost.addProperty("ownerId", post.getOwner().getId());
        jsonObjectPost.addProperty("type", post.getType());
        jsonObjectPost.addProperty("postDate", post.getPostDate().toString());
        jsonObjectPost.addProperty("lastEditedDate", post.getLastEditedDate().toString());
        jsonObjectPost.addProperty("title", post.getTitle());
        jsonObjectPost.addProperty("content", post.getContent());

        int voteCountPost = 0;
        JsonArray votesPost = new JsonArray();
        for(PostVote postVote : post.getVotes()){
            JsonObject jsonObjectVote = new JsonObject();
            jsonObjectVote.addProperty("ownerId", postVote.getOwner().getId());
            jsonObjectVote.addProperty("voteType", postVote.getVoteType());
            if(postVote.getVoteType()){
                voteCountPost++;
            }
            else{
                voteCountPost--;
            }
            votesPost.add(jsonObjectVote);
        }
        jsonObjectPost.addProperty("netCount", voteCountPost);
        jsonObjectPost.add("votes", votesPost);

        JsonArray comments = new JsonArray();
        for(Comment comment : post.getComments()){
            comments.add(commentAdapter.serialize(comment, type, jsc));
        }
        jsonObjectPost.add("comments", comments);

        JsonArray postMediasJsonArray = new JsonArray();
        ArrayList<Media> postMedias = MediaUtility.getMedias(post.getId(), false);
        for (Media media : postMedias) {
            postMediasJsonArray.add(mediaAdapter.serialize(media, type, jsc));
        }
        jsonObjectPost.add("media", postMediasJsonArray);

        JsonArray tags = new JsonArray();
        for (Tag tag : post.getTags()) {
            tags.add(tagAdapter.serialize(tag, type, jsc));
        }
        jsonObjectPost.add("tags", tags);

        return jsonObjectPost;
    }

}
