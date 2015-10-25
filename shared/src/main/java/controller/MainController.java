package controller;

import model.*;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gokcan on 25.10.2015.
 */

@Controller
public class MainController {

    @RequestMapping(value = "test")
    public ModelAndView test(){
        String member_id = "-1";
        String username = "";
        final Session session = Main.getSession();
            session.get
            session.getTransaction().begin();
            Member m = new Member("root","1234","link","lonk");
            /*
            Heritage h = new Heritage("türbe","somewhere","something",Timestamp.valueOf(LocalDateTime.now()));
            //Member m = session.get(Member.class,8l);
            //Heritage h = session.get(Heritage.class,4l);
            Tag t = new Tag("smt");
            Post p = new Post(m,1, Timestamp.valueOf(LocalDateTime.now()),"title","content");
            p.addTags(t);
            h.addTags(t);
            m.postPost(p,h);
            Comment c = new Comment(m,p,"content",Timestamp.valueOf(LocalDateTime.now()));
            m.postComment(p,c);
            m.voteComment(c,true);
            m.votePost(p,false);
            //m.follow((Member)session.get(Member.class,6l));
            session.save(c);
            session.save(h);
            Serializable id = session.save(m);

            session.getTransaction().commit();
            System.out.println("Member posts: "+m.getPosts());
            System.out.println("Heritage posts: "+h.getPosts());
            //Member m2 = ((Member)session.get(Member.class,id));
            member_id = Long.toString(m.getId());
            username = m.getUsername();
            //System.out.println(m2.getPosts());

            */
            session.save(m);
            session.getTransaction().commit();
            session.close();
            Map<String, String> templateVars = new HashMap<String, String>();
            templateVars.put("member_id", member_id);
            templateVars.put("username", username);
            return new ModelAndView("list", templateVars);



    }
}
