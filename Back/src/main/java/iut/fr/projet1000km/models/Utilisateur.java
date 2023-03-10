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

    public List<Utilisateur> getAmis() {
        return amis;
    }

    public void setAmis(List<Utilisateur> amis) {
        this.amis = amis;
    }
}
