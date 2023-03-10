package iut.fr.projet1000km.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Partie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartie;

    @Column
    private Integer nombreJoueur;

    @Column
    private Integer dureeTour;

    @Column
    private String codePartie;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE} )
    private List<Utilisateur> joueurs = new ArrayList<>();

    public Partie() {
    }

    public Long getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(Long idPartie) {
        this.idPartie = idPartie;
    }

    public Integer getNombreJoueur() {
        return nombreJoueur;
    }

    public void setNombreJoueur(Integer nombreJoueur) {
        this.nombreJoueur = nombreJoueur;
    }

    public Integer getDureeTour() {
        return dureeTour;
    }

    public void setDureeTour(Integer dureeTour) {
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