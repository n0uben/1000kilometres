package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Partie;
import iut.fr.projet1000km.repository.PartieRepository;
import iut.fr.projet1000km.repository.UtilisateurRepository;
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
class PartieControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PartieRepository partieRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    void getAllTest() {
        ResponseEntity<Partie[]> response = this.restTemplate.getForEntity("/partie", Partie[].class);
        List<Partie> parties = Arrays.asList(response.getBody());

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(parties);
        Assertions.assertFalse(parties.isEmpty());
    }

    @Test
    void getByIdTest() {
        Long id = 1L;

        ResponseEntity<Partie> response = this.restTemplate.getForEntity("/partie/" + id, Partie.class);
        Partie partie = response.getBody();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(partie);
        Assertions.assertEquals(id, partie.getIdPartie());
    }


}
