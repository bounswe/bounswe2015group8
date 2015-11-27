package adapter;

import api.CommentUtility;
import com.google.gson.*;
import model.Comment;
import model.CommentVote;
import model.Post;
import org.hibernate.Session;

import java.lang.reflect.Type;

/**
 * Created by gokcan on 27.11.2015.
 */
public class CommentAdapter implements JsonSerializer<Comment>{

    @Override
    public JsonElement serialize(Comment comment, Type type,JsonSerializationContext jsc){
        JsonObject jsonObjectHeritage = new JsonObject();
        jsonObjectHeritage.addProperty("id", comment.getId());
        jsonObjectHeritage.addProperty("content", comment.getContent());
        jsonObjectHeritage.addProperty("ownerId", comment.getOwner().getId());
        jsonObjectHeritage.addProperty("postId", comment.getPost().getId());
        jsonObjectHeritage.addProperty("postDate", comment.getPostDate().toString());
        jsonObjectHeritage.addProperty("lastEditedDate", comment.getLastEditedDate().toString());
        int voteCount = 0;
        JsonArray votes = new JsonArray();
        for(CommentVote commentVote : comment.getVotes()){
            JsonObject jsonObjectPost = new JsonObject();
            jsonObjectPost.addProperty("ownerId", commentVote.getOwner().getId());
            jsonObjectPost.addProperty("voteType", commentVote.getVoteType());
            if(commentVote.getVoteType()){
                voteCount++;
            }
            else{
                voteCount--;
            }
            votes.add(jsonObjectPost);
        }
        jsonObjectHeritage.addProperty("netCount", voteCount);
        jsonObjectHeritage.add("votes", votes);
        return jsonObjectHeritage;
    }
}
