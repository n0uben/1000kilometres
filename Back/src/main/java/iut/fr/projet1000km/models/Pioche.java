package iut.fr.projet1000km.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Pioche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPioche;

    @ManyToMany
    private List<Carte> cartes;

    public Pioche() {
    }

    public Pioche(Long idPioche) {
        this.idPioche = idPioche;

    }

    public Long getIdPioche() {
        return idPioche;
    }

    public void setIdPioche(Long id) {
        this.idPioche = id;
    }
}
