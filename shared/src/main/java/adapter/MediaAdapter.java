package adapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import model.Media;

import java.lang.reflect.Type;

/**
 * Created by Goktug on 06.12.2015.
 */
public class MediaAdapter implements JsonSerializer<Media> {
    @Override
    public JsonElement serialize(Media media, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject mediaObject = new JsonObject();
        mediaObject.addProperty("id", media.getId());
        mediaObject.addProperty("postOrHeritageId", media.getPostOrHeritageId());
        mediaObject.addProperty("mediaLink", media.getMediaLink());
        mediaObject.addProperty("mediaType", media.getMediaType());
        mediaObject.addProperty("postOrHeritage", media.getPostOrHeritage());
        return mediaObject;
    }
}
