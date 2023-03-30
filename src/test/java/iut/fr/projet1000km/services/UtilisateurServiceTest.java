package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Utilisateur;
import iut.fr.projet1000km.repository.UtilisateurRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class UtilisateurServiceTest {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    void connexionTest() {
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setPseudo("pseudo1");
        newUtilisateur.setMotDePasse("mdp1");
        this.utilisateurRepository.saveAndFlush(newUtilisateur);

        Optional<Utilisateur> utilisateur = this.utilisateurService.connexion("pseudo1", "mdp1");
        Assertions.assertTrue(utilisateur.isPresent());
    }

    @Test
    void connexionFailTest() {
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setPseudo("pseudo2");
        newUtilisateur.setMotDePasse("mdp2");
        this.utilisateurRepository.saveAndFlush(newUtilisateur);

        Optional<Utilisateur> utilisateur = this.utilisateurService.connexion("pseudo2", "mdp1");
        Assertions.assertTrue(utilisateur.isEmpty());
    }
}
