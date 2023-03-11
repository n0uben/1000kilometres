package iut.fr.projet1000km.models;

import jakarta.persistence.*;

@Entity
public class TypeCarte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTypeCarte;

    @Column
    private String nomTypeCarte;

    public long getIdTypeCarte() {
        return idTypeCarte;
    }

    public void setIdTypeCarte(long idTypeCarte) {
        this.idTypeCarte = idTypeCarte;
    }

    public String getNomTypeCarte() {
        return nomTypeCarte;
    }

    public void setNomTypeCarte(String nomTypeCarte) {
        this.nomTypeCarte = nomTypeCarte;
    }
}
