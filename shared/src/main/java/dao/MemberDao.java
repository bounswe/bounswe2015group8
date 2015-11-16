package dao;

import model.Member;

/**
 * Created by xyllan on 30.10.2015.
 */
public interface MemberDao {
    public Member getMemberById(long id);
    public Member getMemberByUsername(String username);
    public Member saveMember(Member m);
    public Member updateMember(Member m);
}
