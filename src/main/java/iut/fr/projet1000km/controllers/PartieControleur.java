package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.models.Partie;
import iut.fr.projet1000km.models.Utilisateur;
import iut.fr.projet1000km.repository.PartieRepository;
import iut.fr.projet1000km.repository.UtilisateurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partie")
public class PartieControleur {

    private final PartieRepository partieRepository;
    private final UtilisateurRepository utilisateurRepository;

    public PartieControleur(PartieRepository partieRepository, UtilisateurRepository utilisateurRepository) {
        this.partieRepository = partieRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping
    public List<Partie> getAll() {
        return partieRepository.findAll();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Partie> getById(@PathVariable final Long id) {
        return partieRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/creer")
    public Partie creer(@RequestBody final Partie partie) {

        //on récupère l'id du joueur créateur fourni dans la requete
        Long idCreateur = partie.getJoueurs().get(0).getIdUtilisateur();

        //on va chercher en bdd les infos completes
        Utilisateur utilisateurBdd = this.utilisateurRepository.findById(idCreateur).orElse(new Utilisateur());

        //on supprime et remplace l'utilisateur créateur dans la partie reçue par les infos en bdd
        partie.getJoueurs().remove(0);
        partie.getJoueurs().add(utilisateurBdd);

        //on sauvegarde
        return this.partieRepository.saveAndFlush(partie);
    }

    @PutMapping("/modifier/{id}")
    public ResponseEntity<Partie> modifier(@PathVariable final Long id, @RequestBody final Partie partie) {
        return partieRepository.findById(id)
                .map(p -> {
                    p.setIdPartie(partie.getIdPartie());
                    p.setNombreJoueurs(partie.getNombreJoueurs());
                    p.setDureeTour(partie.getDureeTour());
                    p.setCodePartie(partie.getCodePartie());
                    Partie partiemodifiee = partieRepository.saveAndFlush(p);
                    return ResponseEntity.ok(partiemodifiee);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable final Long id) {

        return partieRepository.findById(id)
                .map(partieBdd -> {
                    partieRepository.deleteById(partieBdd.getIdPartie());
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
