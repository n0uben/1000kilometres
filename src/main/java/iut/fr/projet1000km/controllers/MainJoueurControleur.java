package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.MainJoueur;
import iut.fr.projet1000km.services.MainJoueurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
public class MainJoueurControleur {
    private final MainJoueurService mainJoueurService;

    public MainJoueurControleur(MainJoueurService mainJoueurService) {
        this.mainJoueurService = mainJoueurService;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<MainJoueur> getById(@PathVariable final Long id) {
        return mainJoueurService.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public MainJoueur creer(@RequestBody MainJoueur mainJoueur) {
        return mainJoueurService.creer(mainJoueur);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<MainJoueur> modifier(@PathVariable Long id, @RequestBody MainJoueur mainJoueur) {
        return mainJoueurService.getOne(id)
                .map(m -> {
                    m.setIdMain(mainJoueur.getIdMain());
                    MainJoueur mainJoueurModifiee = mainJoueurService.modifier(m);
                    return ResponseEntity.ok(mainJoueurModifiee);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable Long id) {
        return mainJoueurService.getOne(id)
                .map(m -> {
                    mainJoueurService.supprimer(m.getIdMain());
                    return ResponseEntity.ok().build();
                } )
                .orElse(ResponseEntity.notFound().build());
    }
}
