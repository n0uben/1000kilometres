package iut.fr.projet1000km.models;

import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Partie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPartie;

    @Column
    private Integer nombreJoueurs;

    @Column
    private Integer dureeTour;

    @Column
    private String codePartie;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Utilisateur> joueurs = new ArrayList<>();

    public Long getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(Long idPartie) {
        this.idPartie = idPartie;
    }

    public Integer getNombreJoueurs() {
        return nombreJoueurs;
    }

    public void setNombreJoueurs(Integer nombreJoueurs) {
        if (nombreJoueurs < Constants.MIN_NB_JOUEURS) {
            this.nombreJoueurs = Constants.MIN_NB_JOUEURS;
        } else if (nombreJoueurs > Constants.MAX_NB_JOUEURS) {
            this.nombreJoueurs = Constants.MAX_NB_JOUEURS;
        } else {
            this.nombreJoueurs = nombreJoueurs;
        }
    }

    public Integer getDureeTour() {
        return dureeTour;
    }

    public void setDureeTour(Integer dureeTour) {

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
}