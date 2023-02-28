package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.ZoneDeJeu;
import iut.fr.projet1000km.services.ZoneDeJeuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zone-de-jeu")
public class ZoneDeJeuControleur {

    private final ZoneDeJeuService zoneDeJeuService;

    public ZoneDeJeuControleur(ZoneDeJeuService zoneDeJeuService) {
        this.zoneDeJeuService = zoneDeJeuService;
    }

    /**
     *
     * @return si existe, renvoie liste zoneDeJeu en BDD sinon liste vide
     */
    @GetMapping
    public List<ZoneDeJeu> getAll() {
        return zoneDeJeuService.getAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ZoneDeJeu> getById(@PathVariable final Long id) {
        return zoneDeJeuService.getOne(id)
                .map(zoneDeJeu -> ResponseEntity.ok(zoneDeJeu))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public ZoneDeJeu creer(@RequestBody ZoneDeJeu zoneDeJeu) {
        return zoneDeJeuService.creer(zoneDeJeu);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<ZoneDeJeu> modifier(@PathVariable Long id, @RequestBody ZoneDeJeu zoneDeJeu) {
        return zoneDeJeuService.getOne(id)
                .map(zdj -> {
                    zdj.setIdZoneDeJeu(zoneDeJeu.getIdZoneDeJeu());
                    ZoneDeJeu zoneDeJeuModifiee = zoneDeJeuService.modifier(zdj);
                    return ResponseEntity.ok(zoneDeJeuModifiee);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        return zoneDeJeuService.getOne(id)
                .map(zdj -> {
                    zoneDeJeuService.supprimer(zdj.getIdZoneDeJeu());
                    return ResponseEntity.ok().build();
                } )
                .orElse(ResponseEntity.notFound().build());
    }

}
