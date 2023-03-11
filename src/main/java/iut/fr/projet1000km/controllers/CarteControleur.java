package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Carte;
import iut.fr.projet1000km.services.CarteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carte")
@CrossOrigin
public class CarteControleur {

    private CarteService carteService;

    public CarteControleur(CarteService carteService) {
        this.carteService = carteService;
    }

    /**
     *
     * @return Si existe, renvoie liste des cartes en BDD, sinon liste vide
     */
    @GetMapping
    public List<Carte> getAll() {
        return carteService.getAll();
    }

    /**
     *
     * @param id
     * @return Si existe, renvoie une carte, sinon erreur 404
     */
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

    /**
     *
     * @param id
     * @param carte
     * @return Si existe, modifie carte existante et la renvoie, sinon 404
     */
    @PutMapping("/modifier/{id}")
    public ResponseEntity<Carte> modifier(@PathVariable final Long id, @RequestBody final Carte carte) {

        return carteService.getOne(id)
                .map(c -> {
                    c.setNom(carte.getNom());
                    c.setEffet(carte.getEffet());
                    c.setKm(carte.getKm());
                    c.setNbDispo(carte.getNbDispo());
                    Carte carteModifiee = carteService.modifier(c);
                    return ResponseEntity.ok(carteModifiee);
                }).orElse(ResponseEntity.notFound().build());
    }

    /**
     *
     * @param id
     * @return si existe, supprime et renvoie 200 sinon 404
     */
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable final Long id) {

        return carteService.getOne(id)
                .map(carteBdd -> {
                    carteService.supprimer(carteBdd.getIdCarte());
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
