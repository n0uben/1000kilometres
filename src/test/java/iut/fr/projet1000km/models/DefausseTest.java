package iut.fr.projet1000km.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DefausseTest {

    Defausse defausse = new Defausse();
    Partie partie = new Partie();

    @Test
    void defausseSetGetId() {
        defausse.setIdDefausse(1L);
        Assertions.assertEquals(1, defausse.getIdDefausse());
    }

    @Test
    void testSetPartie() {
        this.defausse.setPartie(this.partie);
        Assertions.assertNotNull(defausse.getPartie());
    }

    @Test
    void testGetPropFromPartie() {

        this.partie.setNombreJoueur(2);
        this.defausse.setPartie(partie);

        Assertions.assertEquals(2, defausse.getPartie().getNombreJoueur());

    }

    @Test
    void testSetListCarte() {
        Carte carte = new Carte();
        List<Carte> cartes = new ArrayList<>();
        cartes.add(carte);

        this.defausse.setCartes(cartes);

        Assertions.assertNotNull(defausse.getCartes());
    }

    @Test
    void testGetPropFromCarte() {
        Carte carte = new Carte();
        carte.setIdCarte(1);
        List<Carte> cartes = new ArrayList<>();
        cartes.add(carte);

        this.defausse.setCartes(cartes);

        Assertions.assertEquals(1, defausse.getCartes().get(0).getIdCarte());
    }

}
