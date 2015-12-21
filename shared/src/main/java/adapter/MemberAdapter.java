package adapter;

import com.google.gson.*;
import model.*;

import java.lang.reflect.Type;

/**
 * Created by Goktug on 16.11.2015.
 */
public class MemberAdapter implements JsonSerializer<Member> {
    private PostAdapter postAdapter = new PostAdapter();
    private CommentAdapter commentAdapter = new CommentAdapter();
    private CommentVoteAdapter commentVoteAdapter = new CommentVoteAdapter();
    private PostVoteAdapter postVoteAdapter = new PostVoteAdapter();

    @Override
    public JsonElement serialize(Member member, Type type, JsonSerializationContext jsonSerializationContext) {
        Gson gson = new Gson();
        JsonObject memberObject = new JsonObject();
        memberObject.addProperty("id", member.getId());
        memberObject.addProperty("username", member.getUsername());
        memberObject.addProperty("email", member.getEmail());
        memberObject.addProperty("profilePicture", member.getProfilePicture());

        JsonArray comments = new JsonArray();
        for(Comment comment : member.getComments()){
            comments.add(commentAdapter.serialize(comment, type, jsonSerializationContext));
        }
        memberObject.add("comments", comments);

        JsonArray commentVotes = new JsonArray();
        for(CommentVote commentVote : member.getCommentVotes()){
            commentVotes.add(commentVoteAdapter.serialize(commentVote, type, jsonSerializationContext));
        }
        memberObject.add("commentVotes", commentVotes);

        JsonArray followedMembers = new JsonArray();
        for (Member followed : member.getFollowedMembers()) {
            JsonObject memberJson = new JsonObject();
            memberJson.addProperty("id", followed.getId());
            memberJson.addProperty("username", followed.getUsername());
            followedMembers.add(memberJson);
        }
        memberObject.add("followedMembers", followedMembers);

        JsonArray followers = new JsonArray();
        for (Member follower : member.getFollowers()) {
            JsonObject memberJson = new JsonObject();
            memberJson.addProperty("id", follower.getId());
            memberJson.addProperty("username", follower.getUsername());
            followers.add(memberJson);
        }
        memberObject.add("followers", followers);

        JsonArray followedHeritages = new JsonArray();
        for (Heritage heritage : member.getFollowedHeritages()) {
            JsonObject heritageJson = new JsonObject();
            heritageJson.addProperty("id", heritage.getId());
            followedHeritages.add(heritageJson);
        }
        memberObject.add("followedHeritages", followedHeritages);

        JsonArray posts = new JsonArray();
        for (Post post : member.getPosts()) {
            posts.add(postAdapter.serialize(post, type, jsonSerializationContext));
        }
        memberObject.add("posts", posts);

        JsonArray postVotes = new JsonArray();
        for(PostVote postVote : member.getPostVotes()){
            postVotes.add(postVoteAdapter.serialize(postVote, type, jsonSerializationContext));
        }
        memberObject.add("postVotes", postVotes);

        return memberObject;
    }
}
