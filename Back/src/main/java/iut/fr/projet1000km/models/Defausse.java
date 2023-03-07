package iut.fr.projet1000km.models;

import jakarta.persistence.*;

@Entity
public class Defausse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDefausse;

    @ManyToOne
    private Partie partie;

    public Defausse() {
    }

    public Defausse(Long idDefausse) {
        this.idDefausse = idDefausse;
    }

    public Defausse(Long idDefausse, Partie partie) {
        this.idDefausse = idDefausse;
        this.partie = partie;
    }

    public Long getIdDefausse() {
        return idDefausse;
    }

    public void setIdDefausse(Long idDefausse) {
        this.idDefausse = idDefausse;
    }
}
