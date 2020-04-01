package s4.spring.td5.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Date date;
    private String content;
    private String comment;

    @ManyToOne
    private Script script;
}
