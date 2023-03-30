package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.MainJoueur;
import iut.fr.projet1000km.repository.MainJoueurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
public class MainJoueurControleur {
    private final MainJoueurRepository mainJoueurRepository;

    public MainJoueurControleur(MainJoueurRepository mainJoueurRepository) {
        this.mainJoueurRepository = mainJoueurRepository;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<MainJoueur> getById(@PathVariable final Long id) {
        return mainJoueurRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public MainJoueur creer(@RequestBody MainJoueur mainJoueur) {
        return mainJoueurRepository.saveAndFlush(mainJoueur);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<MainJoueur> modifier(@PathVariable Long id, @RequestBody MainJoueur mainJoueur) {
        return mainJoueurRepository.findById(id)
                .map(m -> {
                    m.setIdMain(mainJoueur.getIdMain());
                    MainJoueur mainJoueurModifiee = mainJoueurRepository.saveAndFlush(m);
                    return ResponseEntity.ok(mainJoueurModifiee);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable Long id) {
        return mainJoueurRepository.findById(id)
                .map(m -> {
                    mainJoueurRepository.deleteById(m.getIdMain());
                    return ResponseEntity.ok().build();
                } )
                .orElse(ResponseEntity.notFound().build());
    }
}
