package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Pioche;
import iut.fr.projet1000km.services.PiocheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pioche")
public class PiocheControleur {

    private final PiocheService piocheService;

    public PiocheControleur(PiocheService piocheService) {
        this.piocheService = piocheService;
    }

    /**
     *
     * @return si existe, renvoie liste pioches en BDD sinon liste vide
     */
    @GetMapping
    public List<Pioche> getAll() {
        return piocheService.getAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Pioche> getById(@PathVariable final Long id) {
        return piocheService.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public Pioche creer(@RequestBody Pioche pioche) {
        return piocheService.creer(pioche);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Pioche> modifier(@PathVariable Long id, @RequestBody Pioche pioche) {
        return piocheService.getOne(id)
                .map(p -> {
                    p.setIdPioche(pioche.getIdPioche());
                    Pioche piocheModifiee = piocheService.modifier(p);
                    return ResponseEntity.ok(piocheModifiee);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable Long id) {
        return piocheService.getOne(id)
                .map(p -> {
                    piocheService.supprimer(p.getIdPioche());
                    return ResponseEntity.ok().build();
                } )
                .orElse(ResponseEntity.notFound().build());
    }

}
