package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Carte;
import iut.fr.projet1000km.services.CarteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carte")

public class CarteControleur {

    private CarteService carteService;

    public CarteControleur(CarteService carteService) {
        this.carteService = carteService;
    }

    @GetMapping
    public List<Carte> getAll() {
        return carteService.getAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Carte> getById(@PathVariable final Long id) {

        // map & orElse  == then & catch en javascript (getOne == promesse => traiter en tant que tel)
        return carteService.getOne(id)
                .map(carte -> ResponseEntity.ok(carte))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public Carte creer(@RequestBody final Carte carte) {
        return carteService.creer(carte);
    }

    @PostMapping("/modifier/{id}")
    public ResponseEntity<Carte> modifier(@PathVariable final Long id, @RequestBody final Carte carte) {

        return carteService.getOne(id)
                .map(carte1 -> {
                    Carte carteModifiee = carteService.modifier(carte);
                    return ResponseEntity.ok(carteModifiee);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/supprimer/{id}")
    public ResponseEntity<?> supprimer(@PathVariable final Long id) {

        return carteService.getOne(id)
                .map(carteBdd -> {
                    carteService.supprimer(carteBdd.getId());
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
