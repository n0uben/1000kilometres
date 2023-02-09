package iut.fr.projet1000km.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pioche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPioche;

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
