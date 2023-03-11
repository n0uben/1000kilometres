package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.TypeCarte;
import iut.fr.projet1000km.services.TypeCarteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typecarte")
public class TypeCarteControleur {
    private final TypeCarteService typeCarteService;

    public TypeCarteControleur(TypeCarteService typeCarteService) {
        this.typeCarteService = typeCarteService;
    }

    @GetMapping
    public List<TypeCarte> getAll() {
        return typeCarteService.getAll();
    }

    /**
     *
     * @param id id typecarte
     * @return Si existe, renvoie une carte, sinon 404
     */
    @GetMapping(value = "{id}")
    public ResponseEntity<TypeCarte> getById(@PathVariable final Long id) {

        // map & orElse  == then & catch en javascript (getOne == promesse => traiter en tant que tel)
        return typeCarteService.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public TypeCarte creer(@RequestBody final TypeCarte tcarte) {
        return typeCarteService.creer(tcarte);
    }

    /**
     *
     * @param id id typecarteservice
     * @param tcarte objet type carte
     * @return Si existe, modifie carte existante et la renvoie, sinon 404
     */
    @PutMapping("/modifier/{id}")
    public ResponseEntity<TypeCarte> modifier(@PathVariable final Long id, @RequestBody final TypeCarte tcarte) {

        return typeCarteService.getOne(id)
                .map(tc -> {
                    tc.setNomTypeCarte(tcarte.getNomTypeCarte());
                    TypeCarte tcarteModifiee = typeCarteService.modifier(tc);
                    return ResponseEntity.ok(tcarteModifiee);
                }).orElse(ResponseEntity.notFound().build());
    }

    /**
     *
     * @param id id typeCarte
     * @return si existe, supprime et renvoie 200 sinon 404
     */
    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable final Long id) {

        return typeCarteService.getOne(id)
                .map(tcarteBdd -> {
                    typeCarteService.supprimer(tcarteBdd.getIdTypeCarte());
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
