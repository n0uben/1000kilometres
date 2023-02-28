package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Partie;
import iut.fr.projet1000km.services.PartieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partie")
public class PartieControleur {

    private PartieService partieService;

    public PartieControleur(PartieService partieService) {
        this.partieService = partieService;
    }

    @GetMapping
    public List<Partie> getAll() {
        return partieService.getAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Partie> getById(@PathVariable final Long id) {
        return partieService.getOne(id)
                .map(partie -> ResponseEntity.ok(partie))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public Partie creer(@RequestBody final Partie partie) {
        return partieService.creer(partie);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Partie> modifier(@PathVariable final Long id, @RequestBody final Partie partie) {
        return partieService.getOne(id)
                .map(p -> {
                    p.setIdPartie(partie.getIdPartie());
                    p.setNombreJoueur(partie.getNombreJoueur());
                    p.setDuréeTour(partie.getDuréeTour());
                    p.setCodePartie(partie.getCodePartie());
                    Partie partiemodifiee = partieService.modifier(p);
                    return ResponseEntity.ok(partiemodifiee);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<?> supprimer(@PathVariable final Long id) {

        return partieService.getOne(id)
                .map(partieBdd -> {
                    partieService.supprimer(partieBdd.getIdPartie());
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
