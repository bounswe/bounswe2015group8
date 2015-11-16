package adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.Member;

import java.lang.reflect.Type;

/**
 * Created by Goktug on 16.11.2015.
 */
public class MemberAdapter implements JsonSerializer<Member> {
    @Override
    public JsonElement serialize(Member member, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject memberObject = new JsonObject();
        memberObject.addProperty("id", member.getId());
        memberObject.addProperty("email", member.getEmail());
        memberObject.addProperty("profilePicture", member.getProfilePicture());
        return memberObject;
    }
}
