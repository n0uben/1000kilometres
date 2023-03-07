package iut.fr.projet1000km.models;

import jakarta.persistence.*;

@Entity
public class ZoneDeJeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idZoneDeJeu;

    @ManyToOne
    private Partie partie;

    @ManyToOne
    private Utilisateur utilisateur;

    public ZoneDeJeu () {}

    public ZoneDeJeu (Long idZoneDeJeu) { this.idZoneDeJeu = idZoneDeJeu; }

    public ZoneDeJeu(Long idZoneDeJeu, Partie partie, Utilisateur utilisateur) {
        this.idZoneDeJeu = idZoneDeJeu;
        this.partie = partie;
        this.utilisateur = utilisateur;
    }

    public Long getIdZoneDeJeu() {
        return idZoneDeJeu;
    }

    public void setIdZoneDeJeu(Long idZoneDeJeu) {
        this.idZoneDeJeu = idZoneDeJeu;
    }
}
