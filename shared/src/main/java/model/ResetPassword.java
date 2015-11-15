package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by gokcan on 15.11.2015.
 */

public class ResetPassword {
    private long id;
    private Member member;
    private String token;
    private Timestamp requestedDate;

    public ResetPassword(){

    }

    public ResetPassword(Member member, String token, Timestamp requestedDate){
        this.member = member;
        this.token = token;
        this.requestedDate = requestedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Member getMember() { return member; }

    public void setMember(Member member) { this.member = member; }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Timestamp requestedDate) {
        this.requestedDate = requestedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResetPassword that = (ResetPassword) o;

        if (id != that.id) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;
        if (requestedDate != null ? !requestedDate.equals(that.requestedDate) : that.requestedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (token != null ? token.hashCode() : 0);
        result = 31 * result + (requestedDate != null ? requestedDate.hashCode() : 0);
        return result;
    }
}
