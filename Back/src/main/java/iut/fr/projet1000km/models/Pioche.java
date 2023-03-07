package iut.fr.projet1000km.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pioche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPioche;

    @ManyToOne
    private Partie partie;

    @ManyToMany
    private List<Carte> cartes;

    public Pioche() {
    }

    public Pioche(Long idPioche) {
        this.idPioche = idPioche;
    }

    public Pioche(Long idPioche, Partie partie) {
        this.idPioche = idPioche;
        this.partie = partie;
    }

    public Pioche(Long idPioche, Partie partie, List<Carte> cartes) {
        this.idPioche = idPioche;
        this.partie = partie;
        this.cartes = cartes;
    }

    public Long getIdPioche() {
        return idPioche;
    }

    public void setIdPioche(Long id) {
        this.idPioche = id;
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
