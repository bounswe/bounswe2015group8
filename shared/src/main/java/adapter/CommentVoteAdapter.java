package adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.CommentVote;

import java.lang.reflect.Type;

/**
 * Created by Goktug on 06.12.2015.
 */
public class CommentVoteAdapter implements JsonSerializer<CommentVote> {

    @Override
    public JsonElement serialize(CommentVote commentVote, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObjectCommentVote = new JsonObject();
        jsonObjectCommentVote.addProperty("ownerId", commentVote.getOwnerId());
        jsonObjectCommentVote.addProperty("commentId", commentVote.getCommentId());
        jsonObjectCommentVote.addProperty("voteType", commentVote.getVoteType());

        return jsonObjectCommentVote;
    }
}
