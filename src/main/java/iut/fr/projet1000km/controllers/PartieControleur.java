package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Partie;
import iut.fr.projet1000km.models.Utilisateur;
import iut.fr.projet1000km.services.PartieService;
import iut.fr.projet1000km.services.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partie")
public class PartieControleur {

    private final PartieService partieService;
    private final UtilisateurService utilisateurService;

    public PartieControleur(PartieService partieService, UtilisateurService utilisateurService) {
        this.partieService = partieService;
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<Partie> getAll() {
        return partieService.getAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Partie> getById(@PathVariable final Long id) {
        return partieService.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public Partie creer(@RequestBody final Partie partie) {

        //on recup l'id du joueur créateur fourni dans la requete
        Long idCreateur = partie.getJoueurs().get(0).getIdUtilisateur();

        //on va chercher en bdd les infos completes
        Utilisateur utilisateurBdd = this.utilisateurService.getOne(idCreateur).orElse(new Utilisateur());

        //on supprimer et remplace l'utilisateur créateur dans la partie reçue par les infos en bdd
        partie.getJoueurs().remove(0);
        partie.getJoueurs().add(utilisateurBdd);

        //on sauvegarde
        return this.partieService.creer(partie);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Partie> modifier(@PathVariable final Long id, @RequestBody final Partie partie) {
        return partieService.getOne(id)
                .map(p -> {
                    p.setIdPartie(partie.getIdPartie());
                    p.setNombreJoueur(partie.getNombreJoueur());
                    p.setDureeTour(partie.getDureeTour());
                    p.setCodePartie(partie.getCodePartie());
                    Partie partiemodifiee = partieService.modifier(p);
                    return ResponseEntity.ok(partiemodifiee);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable final Long id) {

        return partieService.getOne(id)
                .map(partieBdd -> {
                    partieService.supprimer(partieBdd.getIdPartie());
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
