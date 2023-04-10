package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Utilisateur;
import iut.fr.projet1000km.repository.UtilisateurRepository;
import iut.fr.projet1000km.services.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateur")
@CrossOrigin("http://localhost:5173")
public class UtilisateurControleur {

    private final UtilisateurService utilisateurService;
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurControleur(UtilisateurService utilisateurService, UtilisateurRepository utilisateurRepository) {
        this.utilisateurService = utilisateurService;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping
    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Utilisateur> getById(@PathVariable final Long id) {
        return utilisateurRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {return utilisateurRepository.saveAndFlush(utilisateur);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Utilisateur> modifier(@PathVariable final Long id, @RequestBody Utilisateur utilisateur) {
        return utilisateurRepository.findById(id)
                .map(u -> {
                    u.setPseudo(utilisateur.getPseudo());
                    u.setMotDePasse(utilisateur.getMotDePasse());
                    u.setNbPartiesJouees(utilisateur.getNbPartiesJouees());
                    u.setNbPartiesGagnees(utilisateur.getNbPartiesGagnees());
                    Utilisateur utilisateurModifie = utilisateurRepository.saveAndFlush(u);
                    return  ResponseEntity.ok(utilisateurModifie);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable final Long id) {
        return utilisateurRepository.findById(id)
                .map(utilisateurBdd -> {
                    utilisateurRepository.deleteById(utilisateurBdd.getIdUtilisateur());
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("connexion")
    public ResponseEntity<Utilisateur> connexion(@RequestBody Utilisateur utilisateurLight) {
        return utilisateurService.connexion(utilisateurLight.getPseudo(), utilisateurLight.getMotDePasse())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
