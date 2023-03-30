package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Pioche;
import iut.fr.projet1000km.repository.PiocheRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pioche")
public class PiocheControleur {

    private final PiocheRepository piocheRepository;

    public PiocheControleur(PiocheRepository piocheRepository) {
        this.piocheRepository = piocheRepository;
    }

    /**
     *
     * @return si existe, renvoie liste pioches en BDD sinon liste vide
     */
    @GetMapping
    public List<Pioche> getAll() {
        return piocheRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Pioche> getById(@PathVariable final Long id) {
        return piocheRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public Pioche creer(@RequestBody Pioche pioche) {
        return piocheRepository.saveAndFlush(pioche);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Pioche> modifier(@PathVariable Long id, @RequestBody Pioche pioche) {
        return piocheRepository.findById(id)
                .map(p -> {
                    p.setIdPioche(pioche.getIdPioche());
                    Pioche piocheModifiee = piocheRepository.saveAndFlush(p);
                    return ResponseEntity.ok(piocheModifiee);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable Long id) {
        return piocheRepository.findById(id)
                .map(p -> {
                    piocheRepository.deleteById(p.getIdPioche());
                    return ResponseEntity.ok().build();
                } )
                .orElse(ResponseEntity.notFound().build());
    }

}
