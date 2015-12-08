package api;

import controller.Main;
import dao.MemberDaoImpl;
import model.Member;
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
        memberList = (ArrayList<Member>) Main.getSession().createCriteria(Member.class).list();
        return memberList;
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
