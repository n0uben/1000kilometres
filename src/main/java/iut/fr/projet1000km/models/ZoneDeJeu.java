package iut.fr.projet1000km.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ZoneDeJeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idZoneDeJeu;

    @ManyToOne
    private Partie partie;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToMany
    private List<Carte> cartes;

    public Long getIdZoneDeJeu() {
        return idZoneDeJeu;
    }

    public void setIdZoneDeJeu(Long idZoneDeJeu) {
        this.idZoneDeJeu = idZoneDeJeu;
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
