package iut.fr.projet1000km.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Defausse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDefausse;

    @ManyToOne
    private Partie partie;

    @ManyToMany
    private List<Carte> cartes;

    public Long getIdDefausse() {
        return idDefausse;
    }

    public void setIdDefausse(Long idDefausse) {
        this.idDefausse = idDefausse;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public List<Carte> getCartes() {
        return cartes;
    }

    public void setCartes(List<Carte> cartes) {
        this.cartes = cartes;
    }
}
