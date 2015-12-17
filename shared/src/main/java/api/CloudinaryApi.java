package api;

import controller.Main;
import model.Media;
import org.hibernate.Session;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Goktug on 08.12.2015.
 */
@RestController
public class CloudinaryApi {

    @RequestMapping(value = "/api/uploadCloudinary",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public long getCommentsOfFollowedUsers(@RequestBody Media media) {
        final Session session = Main.getSession();
        session.getTransaction().begin();
        Media newMedia = new Media(media.getPostOrHeritageId(), media.getMediaLink(), media.getMediaType(), media.getPostOrHeritage());
        session.save(newMedia);
        session.getTransaction().commit();
        session.close();
        return newMedia.getId();
    }
}
