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
    private int duréeTour;

    @Column
    private String codePartie;

    public Partie() {}
    public Partie(long idPartie, int nombreJoueur, int duréeTour, String codePartie) {
        this.idPartie = idPartie;
        this.nombreJoueur = nombreJoueur;
        this.duréeTour = duréeTour;
        this.codePartie = codePartie;
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

    public int getDuréeTour() {
        return duréeTour;
    }

    public void setDuréeTour(int duréeTour) {
        this.duréeTour = duréeTour;
    }

    public String getCodePartie() {
        return codePartie;
    }

    public void setCodePartie(String codePartie) {
        this.codePartie = codePartie;
    }
}
