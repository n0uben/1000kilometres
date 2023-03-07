package iut.fr.projet1000km.models;

import jakarta.persistence.*;

@Entity
public class Carte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCarte;
    @Column
    private String nom;
    @Column
    private int km;
    @Column
    private String effet;
    @Column
    private int nbDispo;

    @ManyToOne
    private TypeCarte typeCarte;

    public Carte() {}

    public Carte(long idCarte, String nom, int km, String effet, int nbDispo) {
        this.idCarte = idCarte;
        this.nom = nom;
        this.km = km;
        this.effet = effet;
        this.nbDispo = nbDispo;
    }

    public long getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(long id) {
        this.idCarte = id;
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
