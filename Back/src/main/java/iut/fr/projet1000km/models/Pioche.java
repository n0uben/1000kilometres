package iut.fr.projet1000km.models;

import jakarta.persistence.*;

@Entity
public class Pioche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPioche;

    @ManyToOne
    private Partie partie;

    public Pioche() {
    }

    public Pioche(Long idPioche) {
        this.idPioche = idPioche;
    }

    public Pioche(Long idPioche, Partie partie) {
        this.idPioche = idPioche;
        this.partie = partie;
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
}
