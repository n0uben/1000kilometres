package iut.fr.projet1000km.repository;

import iut.fr.projet1000km.models.Utilisateur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    void findByPseudoAndMotDePasseTest() {
        Optional<Utilisateur> user = utilisateurRepository.findByPseudoAndMotDePasse("test", "blabla");
        Assertions.assertTrue(user.isPresent());
    }

    @Test
    void findByPseudoAndMotDePasseFailTest() {
        Optional<Utilisateur> user = utilisateurRepository.findByPseudoAndMotDePasse("tezaeazeest", "blabla");
        Assertions.assertTrue(user.isEmpty());
    }


}
