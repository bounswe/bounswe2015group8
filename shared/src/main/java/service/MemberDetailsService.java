package service;

import dao.MemberDao;
import model.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by xyllan on 30.10.2015.
 */
public class MemberDetailsService implements UserDetailsService{
    private MemberDao memberDao;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member m = memberDao.getMemberByUsername(username);
        return new User(m.getUsername(),m.getPassword(),true,true,true,true,new ArrayList<GrantedAuthority>());
    }
    public Member createMember(String username, String password, String email,
                               String profilePic) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        Member m = new Member(username,hashedPassword, email,profilePic);
        return memberDao.saveMember(m);
    }

    public Member updatePassword(String username, String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        Member m = memberDao.getMemberByUsername(username);
        m.setPassword(hashedPassword);
        return memberDao.updateMember(m);
    }

    public Member saveInterest(String username, String interest){

            Member m = memberDao.getMemberByUsername(username);
            m.saveInterest(interest);
            return memberDao.updateMember(m);

    }
    public Member getMemberByUsername(String username) {
        return memberDao.getMemberByUsername(username);
    }

    public Member getMemberById(long id) { return memberDao.getMemberById(id); }

    public MemberDao getMemberDao() {
        return memberDao;
    }
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
