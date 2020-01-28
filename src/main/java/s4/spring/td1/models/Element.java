package s4.spring.td1.models;

import java.util.Objects;

public class Element {
    private String nom;
    private int evaluation;

    public Element() {}

    public Element(String nom) {
        this.nom = nom;
    }

    public Element(String nom, int evaluation) {
        this.nom = nom;
        this.evaluation = evaluation;
    }

    public void setNom(String nom) {
        this.nom = nom;
        this.evaluation = 0;
    }
    public String getNom() {
        return nom;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return getEvaluation() == element.getEvaluation() &&
                Objects.equals(getNom(), element.getNom());
    }
}