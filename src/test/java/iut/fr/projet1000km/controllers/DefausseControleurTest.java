package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Defausse;
import iut.fr.projet1000km.repository.DefausseRepository;
import iut.fr.projet1000km.repository.PartieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
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
    @Autowired
    private DefausseControleur defausseControleur;

//    @Test
//    void getAllTest() {
//        ResponseEntity<Defausse[]> response = restTemplate.getForEntity(
//                "/defausse",
//                Defausse[].class);
//        List<Defausse> defausseList = Arrays.asList(response.getBody());
//
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertNotNull(defausseList);
//        Assertions.assertFalse(defausseList.isEmpty());
//    }
//
//    @Test
//    void getOneTest() {
//        Long idDefausse = 2L;
//        Long idPartie = 2L;
//
//        ResponseEntity<Defausse> response = restTemplate.getForEntity(
//                "/defausse/" + idDefausse,
//                Defausse.class);
//
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(idDefausse, response.getBody().getIdDefausse());
//        Assertions.assertEquals(idPartie, response.getBody().getPartie().getIdPartie());
//    }

    @Test
    void creerTest() {
        Defausse defausse = new Defausse();

        ResponseEntity<Defausse> response = restTemplate.postForEntity(
                "/defausse/creer",
                defausse,
                Defausse.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

//    @Test
//    void supprimerTest() {
//        ResponseEntity<Void> response = restTemplate.exchange(
//                "/defausse/supprimer/3",
//                HttpMethod.DELETE,
//                null,
//                Void.class
//        );
//
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertFalse(defausseRepository.findById(3L).isPresent());
//    }
}
