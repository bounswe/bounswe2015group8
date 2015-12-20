package controller;

import com.cloudinary.utils.ObjectUtils;
import dao.MemberDaoImpl;
import model.Media;
import org.hibernate.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by gokcan on 20.12.2015.
 */
@Controller
public class ProfileController {

    MemberDetailsService memberService;
    PostService postService;
    HeritageService heritageService;
    TagService tagService;

    public ProfileController(){
        memberService = new MemberDetailsService();
        MemberDaoImpl mdao = new MemberDaoImpl();
        mdao.setSessionFactory(Main.getSessionFactory());
        memberService.setMemberDao(mdao);
        postService = new PostService(Main.getSessionFactory());
        heritageService = new HeritageService(Main.getSessionFactory());
        tagService = new TagService(Main.getSessionFactory());
    }

    @RequestMapping(value = "/uploadProfilePicture", method = RequestMethod.POST)
    public ModelAndView uploadProfilePicture(@RequestParam("picture") MultipartFile pictureMedia) throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        try{
            String mediaName = pictureMedia.getOriginalFilename();

            File toUpload = new File(mediaName);
            toUpload.createNewFile();
            FileOutputStream fos = new FileOutputStream(toUpload);
            fos.write(pictureMedia.getBytes());
            fos.close();

            try {
                Map utilsMap = ObjectUtils.asMap("resource_type", "auto");
                Map uploadResult = CloudinaryController.getCloudinary().uploader().upload(toUpload, utilsMap);
                memberService.updateProfilePicture(username, uploadResult.get("url").toString());
            } catch (RuntimeException e) {
                e.printStackTrace();
                return new ModelAndView("list_post", "error", "Cloudinary upload failed..");
            }
        }
        catch(IOException e){
            e.printStackTrace();
            return new ModelAndView("profile", "error", "The file could not be uploaded!");
        }
        return new ModelAndView("redirect:/profile/"+username);

    }

}
