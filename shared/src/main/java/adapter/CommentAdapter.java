package adapter;

import com.google.gson.*;
import model.Comment;
import model.CommentVote;
import org.hibernate.Hibernate;

import java.lang.reflect.Type;

/**
 * Created by gokcan on 27.11.2015.
 */
public class CommentAdapter implements JsonSerializer<Comment>{

    @Override
    public JsonElement serialize(Comment comment, Type type,JsonSerializationContext jsc){
        JsonObject jsonObjectComment = new JsonObject();
        jsonObjectComment.addProperty("id", comment.getId());
        jsonObjectComment.addProperty("content", comment.getContent());
        jsonObjectComment.addProperty("ownerId", comment.getOwner().getId());
        jsonObjectComment.addProperty("username", comment.getOwner().getUsername());
        jsonObjectComment.addProperty("postId", comment.getPost().getId());
        jsonObjectComment.addProperty("postDate", comment.getPostDate().toString());
        jsonObjectComment.addProperty("lastEditedDate", comment.getLastEditedDate().toString());
        int voteCount = 0;
        JsonArray votes = new JsonArray();
        for(CommentVote commentVote : comment.getVotes()){
            JsonObject jsonObjectVote = new JsonObject();
            jsonObjectVote.addProperty("ownerId", commentVote.getOwner().getId());
            jsonObjectVote.addProperty("voteType", commentVote.getVoteType());
            if(commentVote.getVoteType()){
                voteCount++;
            }
            else{
                voteCount--;
            }
            votes.add(jsonObjectVote);
        }
        jsonObjectComment.addProperty("netCount", voteCount);
        jsonObjectComment.add("votes", votes);
        return jsonObjectComment;
    }
}
