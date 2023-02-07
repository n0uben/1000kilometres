package iut.fr.projet1000km.modeles;

import jakarta.persistence.*;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUtilisateur;
    @Column
    private String pseudo;
    @Column
    private String motDePasse;
    @Column
    private int nbPartiesJouees;
    @Column
    private int nbPartiesGagnees;
}
