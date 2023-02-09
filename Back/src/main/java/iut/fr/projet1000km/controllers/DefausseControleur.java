package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Defausse;
import iut.fr.projet1000km.services.DefausseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/defausse")
public class DefausseControleur {

    private final DefausseService defausseService;

    public DefausseControleur(DefausseService defausseService) {
        this.defausseService = defausseService;
    }

    @GetMapping
    public List<Defausse> getAll() {
        return defausseService.getAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Defausse> getOne(@PathVariable Long id) {
        return defausseService.getOne(id)
                .map(defausse -> ResponseEntity.ok(defausse))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public Defausse creer(@RequestBody Defausse defausse) {
        return defausseService.creer(defausse);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Defausse> modifier(@PathVariable Long id, @RequestBody Defausse defausse) {
        return defausseService.getOne(id)
                .map(d -> {
                    d.setIdDefausse(defausse.getIdDefausse());
                    Defausse defausseModifiee = defausseService.modifier(d);
                    return ResponseEntity.ok(defausseModifiee);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        return defausseService.getOne(id)
                .map(d -> {
                    defausseService.supprimer(d.getIdDefausse());
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
