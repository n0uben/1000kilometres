package iut.fr.projet1000km.models;

import iut.fr.projet1000km.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PartieTest {

    Partie partie = new Partie();

    @Test
    void setNombreJoueurs1() {
        partie.setNombreJoueurs(1);
        Assertions.assertEquals(Constants.MIN_NB_JOUEURS, partie.getNombreJoueurs());
    }

    @Test
    void setNombreJoueurs2() {
        partie.setNombreJoueurs(2);
        Assertions.assertEquals(2, partie.getNombreJoueurs());
    }

    @Test
    void setNombreJoueurs12() {
        partie.setNombreJoueurs(12);
        Assertions.assertEquals(Constants.MAX_NB_JOUEURS, partie.getNombreJoueurs());
    }

    @Test
    void setDureeTour20() {
        partie.setDureeTour(20);
        Assertions.assertEquals(Constants.MIN_DUREE_TOUR, partie.getDureeTour());
    }

    @Test
    void setDureeTour45() {
        partie.setDureeTour(45);
        Assertions.assertEquals(45, partie.getDureeTour());
    }

    @Test
    void setDureeTour120() {
        partie.setDureeTour(120);
        Assertions.assertEquals(Constants.MAX_DUREE_TOUR, partie.getDureeTour());
    }
}
