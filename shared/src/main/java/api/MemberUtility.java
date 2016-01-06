package api;

import controller.Main;
import dao.MemberDaoImpl;
import model.Member;
import org.hibernate.Session;
import service.MemberDetailsService;

import java.util.ArrayList;

/**
 * Created by Goktug on 12.11.2015.
 */
public class MemberUtility {
    private static ArrayList<Member> memberList;
    static MemberDetailsService memberService;

    /**
     * Creates and/or updates member list.
     *
     * @return the list of member objects in the database.
     */
    public static ArrayList<Member> getUserList() {
        if (memberList == null) {
            memberList = new ArrayList<Member>();
        }
        final Session session = Main.getSession();
        try{
            memberList = (ArrayList<Member>) session.createCriteria(Member.class).list();
            memberList = (ArrayList<Member>) getMemberService().unproxyMemberList(memberList);
        }
        catch(Exception e) {}
        finally {
            session.close();
            return memberList;
        }
    }

    public static Member getMemberById(long id) {
        memberList = getUserList();
        for (Member member : memberList) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    public static MemberDetailsService getMemberService() {
        if (memberService == null) {
            memberService = new MemberDetailsService();
            MemberDaoImpl mdao = new MemberDaoImpl();
            mdao.setSessionFactory(Main.getSessionFactory());
            memberService.setMemberDao(mdao);
        }
        return memberService;
    }
}
