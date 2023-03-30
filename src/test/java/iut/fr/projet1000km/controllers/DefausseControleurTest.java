package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Defausse;
import iut.fr.projet1000km.models.Partie;
import iut.fr.projet1000km.repository.DefausseRepository;
import iut.fr.projet1000km.repository.PartieRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DefausseControleurTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DefausseRepository defausseRepository;
    @Autowired
    private PartieRepository partieRepository;

    @Test
    void getAllTest() {
        ResponseEntity<Defausse[]> response = restTemplate.getForEntity(
                "/defausse",
                Defausse[].class);
        List<Defausse> defausseList = Arrays.asList(response.getBody());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(defausseList);
        Assertions.assertFalse(defausseList.isEmpty());
    }

    @Test
    void getOneTest() {
        Long idDefausse = 2L;
        Long idPartie = 2L;

        ResponseEntity<Defausse> response = restTemplate.getForEntity(
                "/defausse/" + idDefausse,
                Defausse.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(idDefausse, response.getBody().getIdDefausse());
        Assertions.assertEquals(idPartie, response.getBody().getPartie().getIdPartie());
    }

    @Test
    @Transactional
    void creerTest() {
        Defausse defausse = new Defausse();
        defausse.setIdDefausse(1L);
        Partie partie = partieRepository.findById(1L).get();
        defausse.setPartie(partie);

        ResponseEntity<Defausse> response = restTemplate.postForEntity(
                "/defausse/creer",
                defausse,
                Defausse.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("code1", response.getBody().getPartie().getCodePartie());
    }
}
