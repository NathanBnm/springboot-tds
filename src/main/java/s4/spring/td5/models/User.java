package s4.spring.td5.models;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String login;
    private String password;
    private String email;
    private String identity;

    @OneToMany
    private List<Script> scripts;

    public User() {
    }

    @Ignore
    public User(String login, String password, String email, String identity) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.identity = identity;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
}
