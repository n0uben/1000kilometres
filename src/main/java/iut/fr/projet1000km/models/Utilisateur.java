package iut.fr.projet1000km.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
    @Column
    private String pseudo;
    @Column
    private String motDePasse;
    @Column
    private int nbPartiesJouees = 0;
    @Column
    private int nbPartiesGagnees = 0;
    @Column
    private int kmParcourus = 0;
    @Column
    private boolean peutAvancer = false;


    @ManyToMany
    private List<Utilisateur> amis;

    public Long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getNbPartiesJouees() {
        return nbPartiesJouees;
    }

    public void setNbPartiesJouees(int nbPartiesJouees) {
        if (nbPartiesJouees >= 0 && nbPartiesJouees >= this.nbPartiesGagnees) {
            this.nbPartiesJouees = nbPartiesJouees;
        }
    }

    public int getNbPartiesGagnees() {
        return nbPartiesGagnees;
    }

    public void setNbPartiesGagnees(int nbPartiesGagnees) {
        if (nbPartiesGagnees >= 0 && nbPartiesGagnees <= nbPartiesJouees) {
            this.nbPartiesGagnees = nbPartiesGagnees;
        }
    }

    public int getKmParcourus() {
        return kmParcourus;
    }

    public void setKmParcourus(Integer kmParcourus) {
        if (kmParcourus >= Constants.MIN_KM_PARCOURUS && kmParcourus <= Constants.MAX_KM_PARCOURUS) {
            this.kmParcourus = kmParcourus;
        }
    }

    public boolean getPeutAvancer() {
        return peutAvancer;
    }

    public void setPeutAvancer(boolean peutAvancer) {
        this.peutAvancer = peutAvancer;
    }

    public List<Utilisateur> getAmis() {
        return amis;
    }

    public void setAmis(List<Utilisateur> amis) {
        this.amis = amis;
    }
}
