package iut.fr.projet1000km.models;

import jakarta.persistence.*;

@Entity
public class Partie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPartie;

    @Column
    private int nombreJoueur;

    @Column
    private int dureeTour;

    @Column
    private String codePartie;

    @OneToOne
    private Pioche pioche;

    @OneToOne
    private Defausse defausse;

    public Partie() {
    }

    public Partie(int nombreJoueur, int dureeTour, String codePartie, Pioche pioche, Defausse defausse) {
        this.nombreJoueur = nombreJoueur;
        this.dureeTour = dureeTour;
        this.codePartie = codePartie;
        this.pioche = pioche;
        this.defausse = defausse;
    }

    public Partie(long idPartie, int nombreJoueur, int dureeTour, String codePartie, Pioche pioche, Defausse defausse) {
        this.idPartie = idPartie;
        this.nombreJoueur = nombreJoueur;
        this.dureeTour = dureeTour;
        this.codePartie = codePartie;
        this.pioche = pioche;
        this.defausse = defausse;
    }

    public long getIdPartie() {
        return idPartie;
    }

    public void setIdPartie(long idPartie) {
        this.idPartie = idPartie;
    }

    public int getNombreJoueur() {
        return nombreJoueur;
    }

    public void setNombreJoueur(int nombreJoueur) {
        this.nombreJoueur = nombreJoueur;
    }

    public int getDureeTour() {
        return dureeTour;
    }

    public void setDureeTour(int dureeTour) {
        this.dureeTour = dureeTour;
    }

    public String getCodePartie() {
        return codePartie;
    }

    public void setCodePartie(String codePartie) {
        this.codePartie = codePartie;
    }

    public Pioche getPioche() {
        return pioche;
    }

    public void setPioche(Pioche pioche) {
        this.pioche = pioche;
    }

    public Defausse getDefausse() {
        return defausse;
    }

    public void setDefausse(Defausse defausse) {
        this.defausse = defausse;
    }
}
