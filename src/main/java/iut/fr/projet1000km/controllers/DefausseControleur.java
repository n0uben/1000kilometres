package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Defausse;
import iut.fr.projet1000km.repository.DefausseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/defausse")
public class DefausseControleur {

    private final DefausseRepository defausseRepository;


    public DefausseControleur(DefausseRepository defausseRepository) {
        this.defausseRepository = defausseRepository;
    }

    @GetMapping
    public List<Defausse> getAll() {
        return defausseRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Defausse> getOne(@PathVariable Long id) {
        return defausseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public Defausse creer(@RequestBody Defausse defausse) {
        return defausseRepository.saveAndFlush(defausse);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Defausse> modifier(@PathVariable Long id, @RequestBody Defausse defausse) {
        return defausseRepository.findById(id)
                .map(d -> {
                    d.setIdDefausse(defausse.getIdDefausse());
                    Defausse defausseModifiee = defausseRepository.saveAndFlush(d);
                    return ResponseEntity.ok(defausseModifiee);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable Long id) {
        return defausseRepository.findById(id)
                .map(d -> {
                    defausseRepository.deleteById(d.getIdDefausse());
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
