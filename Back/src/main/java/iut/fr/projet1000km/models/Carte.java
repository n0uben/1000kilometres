package iut.fr.projet1000km.models;

import jakarta.persistence.*;

@Entity
public class Carte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String nom;
    @Column
    private int km;
    @Column
    private String effet;
    @Column
    private int nbDispo;

    public Carte() {}

    public Carte(long id, String nom, int km, String effet, int nbDispo) {
        this.id = id;
        this.nom = nom;
        this.km = km;
        this.effet = effet;
        this.nbDispo = nbDispo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public String getEffet() {
        return effet;
    }

    public void setEffet(String effet) {
        this.effet = effet;
    }

    public int getNbDispo() {
        return nbDispo;
    }

    public void setNbDispo(int nbDispo) {
        this.nbDispo = nbDispo;
    }
}
