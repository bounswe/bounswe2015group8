package controller;

import dao.MemberDao;
import dao.MemberDaoImpl;
import model.*;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import service.MemberDetailsService;
import service.PostService;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gokcan on 25.10.2015.
 */

@Controller
public class MainController {
    @Autowired
    private ApplicationContext appContext;
    private Logger logger = Logger.getLogger(MainController.class);

    MemberDetailsService memberService;
    PostService postService;
    public MainController() {
        memberService = new MemberDetailsService();
        MemberDaoImpl mdao = new MemberDaoImpl();
        mdao.setSessionFactory(Main.getSessionFactory());
        memberService.setMemberDao(mdao);
        postService = new PostService(Main.getSessionFactory());
    }
    @RequestMapping(value = "/")
    public ModelAndView home(){ return new ModelAndView("home"); }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value="username") String username,
                              @RequestParam(value="password") String password){

        final Session session = Main.getSession();
        int numberUsers = 0;
        try{

            Criteria criteria = session.createCriteria(Member.class)
                    .add(Restrictions.eq("username", "xascsanlcs"))
                    .add(Restrictions.eq("password", password));
            numberUsers = criteria.list().size();

        } finally{
            session.close();
            if(numberUsers == 0){
                return new ModelAndView("login", "doesUserExist", false);
            }
            else{
                return new ModelAndView("login_success", "username", username); // login_success.jsp will be created soon.
            }
        }


    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("login");
    }
    @RequestMapping(value = "/login_success", method = RequestMethod.GET)
    public ModelAndView login_success() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return new ModelAndView("login_success","username",name);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(@RequestParam(value="username") String username,
                               @RequestParam(value="password") String password,
                               @RequestParam(value="email") String email){
        Member m = memberService.createMember(username, password, email, "");
        Map<String, String> templateVars = new HashMap<String, String>();
        templateVars.put("member_id", ""+m.getId());
        templateVars.put("username", m.getUsername());
        return new ModelAndView("login_success", "username", username);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup(){
        return new ModelAndView("signup");
    }

    @RequestMapping(value = "/post")
    public ModelAndView post(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return new ModelAndView("post", "username", username);
    }

    @RequestMapping(value = "/upload_post", method = RequestMethod.POST)
    public ModelAndView upload_post(@RequestParam("title") String title,
                                    @RequestParam("content") String content,
                                    @RequestParam("media") MultipartFile media){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        /*
        final Session session = Main.getSession();
        session.getTransaction().begin();
        Member m = memberService.getMemberByUsername(username);

        java.util.Date now = new java.util.Date();

        Post post = new Post(m, 0, new Timestamp(now.getTime()), title, content);
        session.save(post);
        */
        java.util.Date now = new java.util.Date();
        Member m = memberService.getMemberByUsername(username);
        Post post = postService.savePost(m, 0, new Timestamp(now.getTime()), title, content);

        if(!media.isEmpty()){
            try {
                // Creating the directory to store file
                String mediaName = media.getOriginalFilename();
                String filePath = "/media/" + mediaName;
                final Session session = Main.getSession();
                session.getTransaction().begin();
                Media mediaObject = new Media(post.getId(), filePath, 0, false);

                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "webapps"
                        + File.separator + appContext.getApplicationName().substring(1)
                        + File.separator + "static");
                if (!dir.exists())
                    dir.mkdirs();

                File serverFile = new File(dir.getAbsolutePath() + File.separator + mediaName);

                try {
                    media.transferTo(serverFile);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                    return new ModelAndView("list_post", "error", "File uploaded failed:" + mediaName);
                }
                session.save(mediaObject);
                session.getTransaction().commit();
            } catch (Exception e) {
                return new ModelAndView("list_post", "error", "You failed to upload the file" + e.getMessage());
            }
        }

        /*
        Criteria criteria = session.createCriteria(Post.class)
                .add(Restrictions.eq("owner", m));
        List posts = criteria.list();
        session.close();
        */

        List<Post> posts = postService.getPostsByMember(m);
        return new ModelAndView("list_post","posts", posts);
    }

    @RequestMapping(value = "/my_posts")
    public ModelAndView show_posts(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        final Session session = Main.getSession();

        logger.info("The current user is: " + username);
        Member m = memberService.getMemberByUsername(username);
        Criteria criteria = session.createCriteria(Post.class)
                .add(Restrictions.eq("owner", m));
        List posts = criteria.list();
        session.close();

        return new ModelAndView("list_post","posts", posts);
    }


    /*
    @RequestMapping(value = "test")
    public ModelAndView test(){
        String member_id = "-1";
        String username = "";
        final Session session = Main.getSession();
        session.getTransaction().begin();
        Member m = new Member("root","1234","link","lonk");


        session.save(m);
        session.getTransaction().commit();
        session.close();
        Map<String, String> templateVars = new HashMap<String, String>();
        templateVars.put("member_id", ""+m.getId());
        templateVars.put("username", m.getUsername());
        return new ModelAndView("list", templateVars);
    }
    */
}
