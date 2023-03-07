package iut.fr.projet1000km.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUtilisateur;
    @Column
    private String pseudo;
    @Column
    private String motDePasse;
    @Column
    private int nbPartiesJouees;
    @Column
    private int nbPartiesGagnees;

    @ManyToMany
    private List<Utilisateur> amis;

    public Utilisateur() {}

    public Utilisateur(long idUtilisateur, String pseudo, String motDePasse, int nbPartiesJouees, int nbPartiesGagnees) {
        this.idUtilisateur = idUtilisateur;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.nbPartiesJouees = nbPartiesJouees;
        this.nbPartiesGagnees = nbPartiesGagnees;
    }

    public Utilisateur(long idUtilisateur, String pseudo, String motDePasse, int nbPartiesJouees, int nbPartiesGagnees, List<Utilisateur> amis) {
        this.idUtilisateur = idUtilisateur;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.nbPartiesJouees = nbPartiesJouees;
        this.nbPartiesGagnees = nbPartiesGagnees;
        this.amis = amis;
    }

    // GETTERS
    public long getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public int getNbPartiesJouees() {
        return nbPartiesJouees;
    }

    public int getNbPartiesGagnees() {
        return nbPartiesGagnees;
    }

    // SETTERS
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setNbPartiesJouees(int nbPartiesJouees) {
        this.nbPartiesJouees = nbPartiesJouees;
    }

    public void setNbPartiesGagnees(int nbPartiesGagnees) {
        this.nbPartiesGagnees = nbPartiesGagnees;
    }

    public void setIdUtilisateur(long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public List<Utilisateur> getAmis() {
        return amis;
    }

    public void setAmis(List<Utilisateur> amis) {
        this.amis = amis;
    }
}
