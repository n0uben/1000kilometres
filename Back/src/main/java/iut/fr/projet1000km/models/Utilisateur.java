package iut.fr.projet1000km.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
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
}
