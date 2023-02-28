package iut.fr.projet1000km.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ZoneDeJeu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idZoneDeJeu;

    public ZoneDeJeu () {}

    public ZoneDeJeu (Long idZoneDeJeu) { this.idZoneDeJeu = idZoneDeJeu; }

    public Long getIdZoneDeJeu() {
        return idZoneDeJeu;
    }

    public void setIdZoneDeJeu(Long idZoneDeJeu) {
        this.idZoneDeJeu = idZoneDeJeu;
    }
}
