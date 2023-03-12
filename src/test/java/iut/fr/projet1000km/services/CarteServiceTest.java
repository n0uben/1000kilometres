package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Carte;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class CarteServiceTest {

    @Autowired
    private CarteService carteService;

    @Test
    void getOneTest() {
        Optional<Carte> carte = this.carteService.getOne(2L);
        Assertions.assertTrue(carte.isPresent());
    }

    @Test
    void getOneTestFail() {
        Optional<Carte> carte = this.carteService.getOne(54L);
        Assertions.assertTrue(carte.isEmpty());
    }

    @Test
    void getAllTest() {
        List<Carte> cartes = this.carteService.getAll();
        Assertions.assertEquals(19, cartes.size());
    }

    @Test
    void creerTest() {
        Carte carte = new Carte();
        carte.setNom("la carte");

        Carte carteBdd = this.carteService.creer(carte);

        Assertions.assertNotNull(this.carteService.creer(carte));
        Assertions.assertEquals(carte.getNom(), carteBdd.getNom());
    }

    @Test
    void modifierTest() {
        Carte carte = new Carte();
        carte.setNom("la carte");

        Carte carteBdd = this.carteService.creer(carte);
        carteBdd.setNom("un nom différent");
        carteBdd = this.carteService.modifier(carteBdd);

        Assertions.assertNotNull(carteBdd);
        Assertions.assertEquals("un nom différent", carteBdd.getNom());
    }

    @Test
    void supprimerTest() {
        this.carteService.supprimer(1L);
        Assertions.assertTrue(this.carteService.getOne(1L).isEmpty());
    }
}
