package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Carte;
import iut.fr.projet1000km.repository.CarteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarteControleurTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarteRepository carteRepository;

    @Test
    void getAllTest() {
        ResponseEntity<Carte[]> response = restTemplate.getForEntity("/carte", Carte[].class);
        List<Carte> cartes = Arrays.asList(response.getBody());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(cartes);
        Assertions.assertFalse(cartes.isEmpty());
    }

    @Test
    void getByIdTest() {
        Long id = 2L;
        ResponseEntity<Carte> response = restTemplate.getForEntity("/carte/" + id, Carte.class);
        Carte carte = response.getBody();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(carte);
        Assertions.assertEquals(id, carte.getIdCarte());
    }

    @Test
    void creerTest() {
        Carte newCarte = new Carte();
        newCarte.setEffet("effet");
        ResponseEntity<Carte> response = restTemplate.postForEntity("/carte/creer", newCarte, Carte.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("effet", response.getBody().getEffet());
    }

    @Test
    void modifierTest() {
        Carte carte = new Carte();
        carte.setEffet("effet");
        carte.setNom("nom");
        carte.setKm(500);
        carte.setNbDispo(2);
        //on cree notre carte en BDD
        carte = carteRepository.saveAndFlush(carte);

        //on prepare notre carte modifiee
        Carte carteModifiee = new Carte();
        carteModifiee.setEffet("nouvel effet");
        carteModifiee.setNom("nouveau nom");
        carteModifiee.setKm(400);
        carteModifiee.setNbDispo(4);

        //on fait notre requete put pour modifier la carte créée précédemment
        ResponseEntity<Carte> response = restTemplate.exchange(
                "/carte/modifier/" + carte.getIdCarte(),
                HttpMethod.PUT,
                new HttpEntity<>(carteModifiee),
                Carte.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        //on vérifie que la carte renvoyée a bien été modifiée
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("nouvel effet", response.getBody().getEffet());
        Assertions.assertEquals("nouveau nom", response.getBody().getNom());
        Assertions.assertEquals(400, response.getBody().getKm());
        Assertions.assertEquals(4, response.getBody().getNbDispo());
    }

    @Test
    void supprimerTest() {
        Carte carte = new Carte();
        carte.setNom("super carte");
        carte = carteRepository.saveAndFlush(carte);

        ResponseEntity<Void> response = restTemplate.exchange(
                "/carte/supprimer/" + carte.getIdCarte(),
                HttpMethod.DELETE,
                null,
                Void.class
        );

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        //on vérifie que la carte a bien été supprimée
        Assertions.assertFalse(carteRepository.findById(carte.getIdCarte()).isPresent());
    }
}
