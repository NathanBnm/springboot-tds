package s4.spring.td5.models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Script {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;
    private String description;
    private String content;
    private Date creationDate;

    @ManyToOne
    private Category category;

    @OneToMany
    private List<History> history;

    @ManyToOne
    private User user;

    @ManyToOne
    private Language language;
}
