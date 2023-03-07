package iut.fr.projet1000km.models;

import jakarta.persistence.*;

@Entity
public class MainJoueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMain;

    @ManyToOne
    private Partie partie;

    @ManyToOne
    private Utilisateur utilisateur;

    public MainJoueur() {}

    public MainJoueur(Long idMain) { this.idMain = idMain; }

    public MainJoueur(Long idMain, Partie partie, Utilisateur utilisateur) {
        this.idMain = idMain;
        this.partie = partie;
        this.utilisateur = utilisateur;
    }

    public Long getIdMain() {
        return idMain;
    }

    public void setIdMain(Long idMain) {
        this.idMain = idMain;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
}
