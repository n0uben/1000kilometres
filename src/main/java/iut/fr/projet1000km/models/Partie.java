package iut.fr.projet1000km.models;

import iut.fr.projet1000km.Constants;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Partie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartie;

    @Column
    private int nombreJoueurs;

    @Column
    private int dureeTour;

    @Column
    private String codePartie;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Utilisateur> joueurs = new ArrayList<>();
    public Partie() {
    }

    public Partie(Long idPartie, int nombreJoueurs, int dureeTour, String codePartie) {
        this.idPartie = idPartie;
        this.nombreJoueurs = nombreJoueurs;
        this.dureeTour = dureeTour;
        this.codePartie = codePartie;
    }

    public Long getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(Long idPartie) {
        this.idPartie = idPartie;
    }

    public int getNombreJoueurs() {
        return nombreJoueurs;
    }

    public void setNombreJoueurs(int nombreJoueurs) {
        if (nombreJoueurs < Constants.MIN_NB_JOUEURS) {
            this.nombreJoueurs = Constants.MIN_NB_JOUEURS;
        } else if (nombreJoueurs > Constants.MAX_NB_JOUEURS) {
            this.nombreJoueurs = Constants.MAX_NB_JOUEURS;
        } else {
            this.nombreJoueurs = nombreJoueurs;
        }
    }

    public int getDureeTour() {
        return dureeTour;
    }

    public void setDureeTour(int dureeTour) {

        if (dureeTour < Constants.MIN_DUREE_TOUR) {
            this.dureeTour = Constants.MIN_DUREE_TOUR;
        } else if (dureeTour > Constants.MAX_DUREE_TOUR) {
            this.dureeTour = Constants.MAX_DUREE_TOUR;
        } else {
            this.dureeTour = dureeTour;
        }
    }

    public String getCodePartie() {
        return codePartie;
    }

    public void setCodePartie(String codePartie) {
        this.codePartie = codePartie;
    }

    public List<Utilisateur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Utilisateur> joueurs) {
        this.joueurs = joueurs;
    }

    @Override
    public String toString() {
        return "Partie{" +
                "idPartie=" + idPartie +
                ", nombreJoueurs=" + nombreJoueurs +
                ", dureeTour=" + dureeTour +
                ", codePartie='" + codePartie + '\'' +
                ", joueurs=" + joueurs +
                '}';
    }
}