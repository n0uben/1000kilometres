package iut.fr.projet1000km.models;

import jakarta.persistence.*;

@Entity
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idJoueur;

    @Column
    private int kmParcouru;

    @Column
    private boolean peutAvancer;

    public Joueur(){}

    public Joueur(long id,int km,boolean pa){
        this.idJoueur = id;
        this.kmParcouru = km;
        this.peutAvancer = pa;
    }

    public long getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(long idJoueur) {
        this.idJoueur = idJoueur;
    }

    public int getKmParcouru() {
        return kmParcouru;
    }

    public void setKmParcouru(int kmParcouru) {
        this.kmParcouru = kmParcouru;
    }

    public boolean isPeutAvancer() {
        return peutAvancer;
    }

    public void setPeutAvancer(boolean peutAvancer) {
        this.peutAvancer = peutAvancer;
    }
}
