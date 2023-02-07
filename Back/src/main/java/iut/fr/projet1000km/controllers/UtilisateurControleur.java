package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Utilisateur;
import iut.fr.projet1000km.services.UtilisateurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurControleur {

    private final UtilisateurService utilisateurService;

    public UtilisateurControleur(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/create")
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.creer(utilisateur);
    }

    @GetMapping
    public List<Utilisateur> read() {
        return utilisateurService.lire();
    }

    @PutMapping("/update/{id}")
    public Utilisateur update(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.modifier(id, utilisateur);
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        return utilisateurService.supprimer(id);
    }
}
