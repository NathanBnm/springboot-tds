package s4.spring.td2.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Boolean suspended;
    
    @ManyToOne
    private Organization organization;
    
    @ManyToMany
    private List<Group> groups;
}
