package adapter;

import com.google.gson.*;
import model.Member;

import java.lang.reflect.Type;

/**
 * Created by Goktug on 16.11.2015.
 */
public class MemberAdapter implements JsonSerializer<Member> {
    @Override
    public JsonElement serialize(Member member, Type type, JsonSerializationContext jsonSerializationContext) {
        Gson gson = new Gson();
        JsonObject memberObject = new JsonObject();
        memberObject.addProperty("id", member.getId());
        memberObject.addProperty("email", member.getEmail());
        memberObject.addProperty("profilePicture", member.getProfilePicture());
        memberObject.addProperty("followers", gson.toJson(member.getFollowers()));
        memberObject.addProperty("followedMembers", gson.toJson(member.getFollowedMembers()));

        return memberObject;
    }
}
