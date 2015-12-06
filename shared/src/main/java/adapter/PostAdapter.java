package adapter;

import com.google.gson.*;
import model.*;
import org.apache.log4j.Logger;

import java.lang.reflect.Type;

/**
 * Created by gokcan on 15.11.2015.
 */
public class PostAdapter implements JsonSerializer<Post> {
    private CommentAdapter commentAdapter = new CommentAdapter();

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

        return jsonObjectPost;
    }

}
