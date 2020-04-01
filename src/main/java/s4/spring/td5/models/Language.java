package s4.spring.td5.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany
    private List<Script> scripts;
}
