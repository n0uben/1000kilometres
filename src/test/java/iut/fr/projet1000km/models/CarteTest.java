package iut.fr.projet1000km.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarteTest {

    private final Carte carte = new Carte();

    @Test
    void testSetGetId() {

        carte.setIdCarte(1);

        Assertions.assertEquals(1, carte.getIdCarte());
    }

    @Test
    void testSetGetNom() {

        carte.setNom("nom de la carte");

        Assertions.assertEquals("nom de la carte", carte.getNom());
    }

    @Test
    void testSetGetKm() {
        carte.setKm(20);
        Assertions.assertEquals(20, carte.getKm());
    }

    @Test
    void testSetGetEffet() {
        carte.setEffet("stop");
        Assertions.assertEquals("stop", carte.getEffet());
    }

    @Test
    void testSetGetNbDispo() {
        carte.setNbDispo(10);
        Assertions.assertEquals(10, carte.getNbDispo());
    }


}
