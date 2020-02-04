package s4.spring.td2.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String domain;
    private String aliases;
    
    @OneToMany
    private List<Group> groups;
    
    @OneToMany
    private List<User> users;
}
