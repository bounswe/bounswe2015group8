package adapter;

import com.google.gson.*;
import model.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

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

        ArrayList<Long> followedMemberIds = new ArrayList<>();
        for (Member followed : member.getFollowedMembers()) {
            followedMemberIds.add(followed.getId());
        }
        memberObject.addProperty("followedMembers", gson.toJson(followedMemberIds));

        ArrayList<Long> followerIds = new ArrayList<>();
        for (Member follower : member.getFollowers()) {
            followerIds.add(follower.getId());
        }
        memberObject.addProperty("followerMembers", gson.toJson(followerIds));

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

        memberObject.addProperty("followers", gson.toJson(member.getFollowers()));
        memberObject.addProperty("followedMembers", gson.toJson(member.getFollowedMembers()));

        return memberObject;
    }
}
