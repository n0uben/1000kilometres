package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Joueur;
import iut.fr.projet1000km.models.Utilisateur;
import iut.fr.projet1000km.services.JoueurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/joueur")
public class JoueurControleur {
    private final JoueurService joueurService;

    public JoueurControleur(JoueurService joueurService) {
        this.joueurService = joueurService;
    }

    @GetMapping
    public List<Joueur> getAll() {
        return joueurService.getAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Joueur> getById(@PathVariable final Long id) {
        return joueurService.getOne(id)
                .map(joueur -> ResponseEntity.ok(joueur))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public Joueur create(@RequestBody Joueur joueur) {return joueurService.creer(joueur);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Joueur> modifier(@PathVariable final Long id, @RequestBody Joueur joueur) {
        return joueurService.getOne(id)
                .map(j -> {
                    j.setKmParcouru(joueur.getKmParcouru());
                    j.setPeutAvancer(joueur.isPeutAvancer());
                    Joueur joueurModifie = joueurService.modifier(j);
                    return  ResponseEntity.ok(joueurModifie);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<?> supprimer(@PathVariable final Long id) {
        return joueurService.getOne(id)
                .map(joueurBdd -> {
                    joueurService.supprimer(joueurBdd.getIdJoueur());
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
