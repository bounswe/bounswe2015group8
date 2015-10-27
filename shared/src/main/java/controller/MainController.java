package controller;

import model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping(value = "/")
    public ModelAndView home(){
        return new ModelAndView("home");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value="username") String username,
                              @RequestParam(value="password") String password){
        final Session session = Main.getSession();
        int numberUsers = 0;
        try{

            Criteria criteria = session.createCriteria(Member.class)
                    .add(Restrictions.eq("username", username))
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

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(@RequestParam(value="username") String username,
                               @RequestParam(value="password") String password,
                               @RequestParam(value="email") String email){
        final Session session = Main.getSession();
        try{
            session.getTransaction().begin();
            Member m = new Member(username,password, email,"");
            session.save(m);
            session.getTransaction().commit();
            Map<String, String> templateVars = new HashMap<String, String>();
            templateVars.put("member_id", ""+m.getId());
            templateVars.put("username", m.getUsername());

        } finally{
            session.close();
            //return new ModelAndView("list", templateVars);
            return new ModelAndView("login_success", "username", username); // login_success.jsp will be created soon.
        }

    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView signup(){
        return new ModelAndView("signup");
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
