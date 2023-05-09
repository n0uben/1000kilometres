package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.ZoneDeJeu;
import iut.fr.projet1000km.repository.ZoneDeJeuRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zone-de-jeu")
public class ZoneDeJeuControleur {

    private final ZoneDeJeuRepository zoneDeJeuRepository;

    public ZoneDeJeuControleur(ZoneDeJeuRepository zoneDeJeuRepository) {
        this.zoneDeJeuRepository = zoneDeJeuRepository;
    }

    /**
     *
     * @return si existe, renvoie liste zoneDeJeu en BDD sinon liste vide
     */
    @GetMapping
    public List<ZoneDeJeu> getAll() {
        return zoneDeJeuRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ZoneDeJeu> getById(@PathVariable final Long id) {
        return zoneDeJeuRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public ZoneDeJeu creer(@RequestBody ZoneDeJeu zoneDeJeu) {
        return zoneDeJeuRepository.saveAndFlush(zoneDeJeu);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<ZoneDeJeu> modifier(@PathVariable Long id, @RequestBody ZoneDeJeu zoneDeJeu) {
        return zoneDeJeuRepository.findById(id)
                .map(zdj -> {
                    zdj.setIdZoneDeJeu(zoneDeJeu.getIdZoneDeJeu());
                    ZoneDeJeu zoneDeJeuModifiee = zoneDeJeuRepository.saveAndFlush(zdj);
                    return ResponseEntity.ok(zoneDeJeuModifiee);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable Long id) {
        return zoneDeJeuRepository.findById(id)
                .map(zdj -> {
                    zoneDeJeuRepository.deleteById(zdj.getIdZoneDeJeu());
                    return ResponseEntity.ok().build();
                } )
                .orElse(ResponseEntity.notFound().build());
    }

}
