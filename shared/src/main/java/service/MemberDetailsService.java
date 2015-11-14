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

    public Member getMemberByUsername(String username) {
        return memberDao.getMemberByUsername(username);
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
