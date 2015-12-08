package adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.Tag;

import java.lang.reflect.Type;

/**
 * Created by Goktug on 06.12.2015.
 */
public class TagAdapter implements JsonSerializer<Tag> {
    @Override
    public JsonElement serialize(Tag tag, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObjectTag = new JsonObject();
        jsonObjectTag.addProperty("id", tag.getId());
        jsonObjectTag.addProperty("tagText", tag.getTagText());
        jsonObjectTag.addProperty("tagContext", tag.getTagContext());
        return jsonObjectTag;
    }
}
