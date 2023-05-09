package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.TypeCarte;
import iut.fr.projet1000km.repository.TypeCarteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typecarte")
public class TypeCarteControleur {
    private final TypeCarteRepository typeCarteRepository;

    public TypeCarteControleur(TypeCarteRepository typeCarteRepository) {
        this.typeCarteRepository = typeCarteRepository;
    }

    @GetMapping
    public List<TypeCarte> getAll() {
        return typeCarteRepository.findAll();
    }

    /**
     *
     * @param id id typecarte
     * @return Si existe, renvoie un type de carte, sinon 404
     */
    @GetMapping(value = "{id}")
    public ResponseEntity<TypeCarte> getById(@PathVariable final Long id) {

        // map & orElse  == then & catch en javascript (getOne == promesse => traiter en tant que tel)
        return typeCarteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public TypeCarte creer(@RequestBody final TypeCarte tcarte) {
        return typeCarteRepository.saveAndFlush(tcarte);
    }

    /**
     *
     * @param id id
     * @param tcarte objet type carte
     * @return Si existe, modifie typecarte existant et le renvoie, sinon 404
     */
    @PutMapping("/modifier/{id}")
    public ResponseEntity<TypeCarte> modifier(@PathVariable final Long id, @RequestBody final TypeCarte tcarte) {

        return typeCarteRepository.findById(id)
                .map(tc -> {
                    tc.setNomTypeCarte(tcarte.getNomTypeCarte());
                    TypeCarte tcarteModifiee = typeCarteRepository.saveAndFlush(tc);
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

        return typeCarteRepository.findById(id)
                .map(tcarteBdd -> {
                    typeCarteRepository.deleteById(tcarteBdd.getIdTypeCarte());
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
