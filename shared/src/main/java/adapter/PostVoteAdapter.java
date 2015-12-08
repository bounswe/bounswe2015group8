package adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.PostVote;

import java.lang.reflect.Type;

/**
 * Created by Goktug on 06.12.2015.
 */
public class PostVoteAdapter implements JsonSerializer<PostVote> {

        @Override
        public JsonElement serialize(PostVote postVote, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObjectPostVote = new JsonObject();
            jsonObjectPostVote.addProperty("memberId", postVote.getMemberId());
            jsonObjectPostVote.addProperty("postId", postVote.getPostId());
            jsonObjectPostVote.addProperty("voteType", postVote.getVoteType());

            return null;
        }
}
