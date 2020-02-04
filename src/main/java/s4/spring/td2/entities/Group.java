package s4.spring.td2.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String email;
    private String aliases;
    
    @ManyToOne
    private Organization organization;
    
    @ManyToMany
    private List<User> users;
}
