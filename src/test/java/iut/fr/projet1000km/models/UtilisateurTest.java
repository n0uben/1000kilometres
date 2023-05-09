package iut.fr.projet1000km.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UtilisateurTest {

    Utilisateur utilisateur = new Utilisateur();

    @Test
    void setKmParcourusTestBelowMin() {
        utilisateur.setKmParcourus(-200);
        Assertions.assertEquals(0, utilisateur.getKmParcourus());
    }

    @Test
    void setKmParcourusTest200() {
        utilisateur.setKmParcourus(200);
        Assertions.assertEquals(200, utilisateur.getKmParcourus());
    }

    @Test
    void setKmParcourusTestAboveMax() {
        utilisateur.setKmParcourus(1200);
        Assertions.assertEquals(0, utilisateur.getKmParcourus());
    }

    @Test
    void setNbPartiesJoueesBelowMin() {
        utilisateur.setNbPartiesJouees(10);
        utilisateur.setNbPartiesGagnees(8);
        utilisateur.setNbPartiesJouees(5);
        Assertions.assertEquals(10, utilisateur.getNbPartiesJouees());
    }

    @Test
    void setNbPartiesJouees20() {
        utilisateur.setNbPartiesJouees(20);
        Assertions.assertEquals(20, utilisateur.getNbPartiesJouees());
    }

    @Test
    void setNbPartiesGagneesTestBelowMin() {
        utilisateur.setNbPartiesGagnees(-1);
        Assertions.assertEquals(0, utilisateur.getNbPartiesGagnees());
    }

    @Test
    void setNbPartiesGagneesTest10() {
        utilisateur.setNbPartiesJouees(12);
        utilisateur.setNbPartiesGagnees(10);
        Assertions.assertEquals(10, utilisateur.getNbPartiesGagnees());
    }

    @Test
    void setNbPartiesGagneesTestAboveMax() {
        utilisateur.setNbPartiesJouees(5);
        utilisateur.setNbPartiesGagnees(10);
        Assertions.assertEquals(0, utilisateur.getNbPartiesGagnees());
    }
}
