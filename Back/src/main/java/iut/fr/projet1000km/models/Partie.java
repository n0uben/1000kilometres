package iut.fr.projet1000km.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Partie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPartie;

    @Column
    private int nombreJoueur;

    @Column
    private int dureeTour;

    @Column
    private String codePartie;

    @ManyToMany
    private List<Utilisateur> joueurs;

    public Partie() {
    }

    public Partie(int nombreJoueur, int dureeTour, String codePartie, Pioche pioche, Defausse defausse) {
        this.nombreJoueur = nombreJoueur;
        this.dureeTour = dureeTour;
        this.codePartie = codePartie;
    }

    public Partie(long idPartie, int nombreJoueur, int dureeTour, String codePartie, Pioche pioche, Defausse defausse) {
        this.idPartie = idPartie;
        this.nombreJoueur = nombreJoueur;
        this.dureeTour = dureeTour;
        this.codePartie = codePartie;
    }

    public Partie(long idPartie, int nombreJoueur, int dureeTour, String codePartie, List<Utilisateur> joueurs) {
        this.idPartie = idPartie;
        this.nombreJoueur = nombreJoueur;
        this.dureeTour = dureeTour;
        this.codePartie = codePartie;
        this.joueurs = joueurs;
    }

    public long getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(long idPartie) {
        this.idPartie = idPartie;
    }

    public int getNombreJoueur() {
        return nombreJoueur;
    }

    public void setNombreJoueur(int nombreJoueur) {
        this.nombreJoueur = nombreJoueur;
    }

    public int getDureeTour() {
        return dureeTour;
    }

    public void setDureeTour(int dureeTour) {
        this.dureeTour = dureeTour;
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
}