package s4.spring.td6.models;

import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany
    private List<Script> scripts;

    public Category() {
    }

    @Ignore
    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
