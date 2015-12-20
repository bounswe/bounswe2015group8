package controller;

import com.cloudinary.Cloudinary;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Goktug on 28.11.2015.
 */
public class CloudinaryController {
    private static Cloudinary cloudinary;
    private static String[] imageFormats = {"gif", "png", "jpg", "bmp", "ico", "pdf", "tiff", "eps", "jpc", "jp2", "psd", "svg", "webp", "jpeg-xr"};
    private static String[] audioFormats = {"mp3", "ogg", "wav"};
    private static String[] videoFormats = {"mp4", "webm", "flv", "mov", "ogv", "3gp", "3g2", "wmv", "mpeg", "flv", "avi"};

    /// The controller for get Cloudinary, helps storing the media content
    /**
     * @return cloudinary
     */
    public static Cloudinary getCloudinary() {
        if (cloudinary == null) {
            Map config = new HashMap();
            config.put("cloud_name", "bounswe2015group8");
            config.put("api_key", "819376674485966");
            config.put("api_secret", "eR8MLdI3no8saqFbbKDdqnr3TD4");
            cloudinary = new Cloudinary(config);
        }
        return cloudinary;
    }

    /// The controller for get Media type
    /**
     * @param fileName the name of the file (String)
     * @return the type of the name, 0 for image, 1 for audio, 2 for video, -1 for no media.
     */
    public static int getMediaType(String fileName) {
        String fileFormat = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        Logger logger = Logger.getLogger(MainController.class);
        logger.info("file format is" + fileFormat);
        if (Arrays.asList(imageFormats).contains(fileFormat)) {
            return 0;
        } else if (Arrays.asList(audioFormats).contains(fileFormat)) {
            return 1;
        } else if (Arrays.asList(videoFormats).contains(fileFormat)) {
            return 2;
        } else {
            return -1;
        }
    }
}
