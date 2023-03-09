package iut.fr.projet1000km.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class MainJoueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMain;

    @ManyToOne
    private Partie partie;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToMany
    private List<Carte> cartes;

    public MainJoueur() {}

    public MainJoueur(Long idMain) { this.idMain = idMain; }

    public MainJoueur(Long idMain, Partie partie, Utilisateur utilisateur) {
        this.idMain = idMain;
        this.partie = partie;
        this.utilisateur = utilisateur;
    }
    public MainJoueur(Long idMain, Partie partie, Utilisateur utilisateur, List<Carte> cartes) {
        this.idMain = idMain;
        this.partie = partie;
        this.utilisateur = utilisateur;
        this.cartes = cartes;
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Carte> getCartes() {
        return cartes;
    }

    public void setCartes(List<Carte> cartes) {
        this.cartes = cartes;
    }
}
