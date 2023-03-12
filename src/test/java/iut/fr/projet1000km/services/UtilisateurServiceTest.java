package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Utilisateur;
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

    @Test
    void getOneTest() {
        Optional<Utilisateur> utilisateur = this.utilisateurService.getOne(2L);
        Assertions.assertTrue(utilisateur.isPresent());
    }

    @Test
    void getOneTestFail() {
        Optional<Utilisateur> utilisateur = this.utilisateurService.getOne(54L);
        Assertions.assertTrue(utilisateur.isEmpty());
    }

    @Test
    void getAllTest() {
        List<Utilisateur> utilisateurs = this.utilisateurService.getAll();
        Assertions.assertEquals(4, utilisateurs.size());
    }

    @Test
    void creerTest() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo("le pseudo");

        Utilisateur utilisateurBdd = this.utilisateurService.creer(utilisateur);

        Assertions.assertNotNull(this.utilisateurService.creer(utilisateur));
        Assertions.assertEquals(utilisateur.getPseudo(), utilisateurBdd.getPseudo());
    }

    @Test
    void modifierTest() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo("le pseudo");

        Utilisateur utilisateurBdd = this.utilisateurService.creer(utilisateur);
        utilisateurBdd.setPseudo("un pseudo différent");
        utilisateurBdd = this.utilisateurService.modifier(utilisateurBdd);

        Assertions.assertNotNull(utilisateurBdd);
        Assertions.assertEquals("un pseudo différent", utilisateurBdd.getPseudo());
    }

    @Test
    void supprimerTest() {
        this.utilisateurService.supprimer(1L);
        Assertions.assertTrue(this.utilisateurService.getOne(1L).isEmpty());
    }


    @Test
    void connexionTest() {
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setPseudo("pseudo1");
        newUtilisateur.setMotDePasse("mdp1");
        this.utilisateurService.creer(newUtilisateur);

        Optional<Utilisateur> utilisateur = this.utilisateurService.connexion("pseudo1", "mdp1");
        Assertions.assertTrue(utilisateur.isPresent());
    }

    @Test
    void connexionFailTest() {
        Utilisateur newUtilisateur = new Utilisateur();
        newUtilisateur.setPseudo("pseudo2");
        newUtilisateur.setMotDePasse("mdp2");
        this.utilisateurService.creer(newUtilisateur);

        Optional<Utilisateur> utilisateur = this.utilisateurService.connexion("pseudo2", "mdp1");
        Assertions.assertTrue(utilisateur.isEmpty());
    }
}
