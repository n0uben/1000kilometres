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
    private Integer nbPartiesJouees;
    @Column
    private Integer nbPartiesGagnees;

    @Column
    private Integer kmParcourus;

    @Column
    private Boolean peutAvancer;

    @ManyToMany
    private List<Utilisateur> amis;

    public Utilisateur() {}

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

    public Integer getNbPartiesJouees() {
        return nbPartiesJouees;
    }

    public void setNbPartiesJouees(Integer nbPartiesJouees) {
        this.nbPartiesJouees = nbPartiesJouees;
    }

    public Integer getNbPartiesGagnees() {
        return nbPartiesGagnees;
    }

    public void setNbPartiesGagnees(Integer nbPartiesGagnees) {
        this.nbPartiesGagnees = nbPartiesGagnees;
    }

    public Integer getKmParcourus() {
        return kmParcourus;
    }

    public void setKmParcourus(Integer kmParcourus) {
        this.kmParcourus = kmParcourus;
    }

    public Boolean getPeutAvancer() {
        return peutAvancer;
    }

    public void setPeutAvancer(Boolean peutAvancer) {
        this.peutAvancer = peutAvancer;
    }

    public List<Utilisateur> getAmis() {
        return amis;
    }

    public void setAmis(List<Utilisateur> amis) {
        this.amis = amis;
    }
}
