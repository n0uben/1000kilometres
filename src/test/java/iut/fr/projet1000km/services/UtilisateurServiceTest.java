package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Utilisateur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class UtilisateurServiceTest {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private iut.fr.projet1000km.repository.UtilisateurRepository utilisateurRepository;

    @Test
    void connexionTest() {
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setPseudo("pseudo1");
        newUtilisateur.setMotDePasse("mdp1");
        this.utilisateurService.saveAndFlush(newUtilisateur);

        Optional<Utilisateur> utilisateur = this.utilisateurService.connexion("pseudo1", "mdp1");
        Assertions.assertTrue(utilisateur.isPresent());
    }

    @Test
    void connexionFailTest() {
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setPseudo("pseudo2");
        newUtilisateur.setMotDePasse("mdp2");
        this.utilisateurService.saveAndFlush(newUtilisateur);

        Optional<Utilisateur> utilisateur = this.utilisateurService.connexion("pseudo2", "mdp1");
        Assertions.assertTrue(utilisateur.isEmpty());
    }
}
