package iut.fr.projet1000km.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Defausse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDefausse;

    public Defausse() {
    }

    public Defausse(Long idDefausse) {
        this.idDefausse = idDefausse;
    }

    public Long getIdDefausse() {
        return idDefausse;
    }

    public void setIdDefausse(Long idDefausse) {
        this.idDefausse = idDefausse;
    }
}
