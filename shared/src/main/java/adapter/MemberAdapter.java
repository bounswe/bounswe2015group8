package adapter;

import com.google.gson.*;
import model.*;

import java.lang.reflect.Type;

/**
 * Created by Goktug on 16.11.2015.
 */
public class MemberAdapter implements JsonSerializer<Member> {
    public static boolean IS_EXTENSIVE = false;

    @Override
    public JsonElement serialize(Member member, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject memberObject = new JsonObject();
        memberObject.addProperty("id", member.getId());
        memberObject.addProperty("email", member.getEmail());
        memberObject.addProperty("profilePicture", member.getProfilePicture());
        if(IS_EXTENSIVE){
            memberObject.addProperty("username", member.getUsername());

            JsonArray posts = new JsonArray();
            for(Post post : member.getPosts()){
                JsonObject jsonObjectPost = new JsonObject();
                jsonObjectPost.addProperty("id", post.getId());
                jsonObjectPost.addProperty("type", post.getType());
                jsonObjectPost.addProperty("postDate", post.getPostDate().toString());
                jsonObjectPost.addProperty("lastEditedDate", post.getLastEditedDate().toString());
                jsonObjectPost.addProperty("title", post.getTitle());
                jsonObjectPost.addProperty("content", post.getContent());
                posts.add(jsonObjectPost);
            }
            memberObject.add("posts", posts);

            JsonArray comments = new JsonArray();
            for(Comment comment : member.getComments()){
                JsonObject jsonObjectComment = new JsonObject();
                jsonObjectComment.addProperty("id", comment.getId());
                jsonObjectComment.addProperty("postId", comment.getPost().getId());
                jsonObjectComment.addProperty("postDate", comment.getPostDate().toString());
                jsonObjectComment.addProperty("lastEditedDate", comment.getLastEditedDate().toString());
                jsonObjectComment.addProperty("content", comment.getContent());
                comments.add(jsonObjectComment);
            }
            memberObject.add("comments", comments);

            JsonArray postVotes = new JsonArray();
            for(PostVote postvote : member.getPostVotes()){
                JsonObject jsonObjectPostVote = new JsonObject();
                jsonObjectPostVote.addProperty("postId", postvote.getPost().getId());
                jsonObjectPostVote.addProperty("voteType", postvote.getVoteType());
                postVotes.add(jsonObjectPostVote);
            }
            memberObject.add("postVotes", postVotes);

            JsonArray commentVotes = new JsonArray();
            for(CommentVote commentvote : member.getCommentVotes()){
                JsonObject jsonObjectCommentVote = new JsonObject();
                jsonObjectCommentVote.addProperty("commentId", commentvote.getComment().getId());
                jsonObjectCommentVote.addProperty("voteType", commentvote.getVoteType());
                commentVotes.add(jsonObjectCommentVote);
            }
            memberObject.add("commentVotes", commentVotes);
        }
        return memberObject;
    }
}
