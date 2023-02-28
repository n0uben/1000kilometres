package iut.fr.projet1000km.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MainJoueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMain;

    public MainJoueur() {}

    public MainJoueur(Long idMain) { this.idMain = idMain; }

    public Long getIdMain() {
        return idMain;
    }

    public void setIdMain(Long idMain) {
        this.idMain = idMain;
    }
}
