package iutc3.unicaen.fr.td5.models;

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
}
