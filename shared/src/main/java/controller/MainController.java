package controller;

import dao.MemberDao;
import dao.MemberDaoImpl;
import model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.MemberDetailsService;

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
    MemberDetailsService memberService;
    public MainController() {
        memberService = new MemberDetailsService();
        MemberDaoImpl mdao = new MemberDaoImpl();
        mdao.setSessionFactory(Main.getSessionFactory());
        memberService.setMemberDao(mdao);
    }
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
        Member m = memberService.createMember(username,password,email,"");
        Map<String, String> templateVars = new HashMap<String, String>();
        templateVars.put("member_id", ""+m.getId());
        templateVars.put("username", m.getUsername());
        return new ModelAndView("login_success", "username", username);
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
