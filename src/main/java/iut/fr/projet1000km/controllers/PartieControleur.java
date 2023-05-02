package iut.fr.projet1000km.controllers;

import iut.fr.projet1000km.Constants;
import iut.fr.projet1000km.models.Partie;
import iut.fr.projet1000km.models.Utilisateur;
import iut.fr.projet1000km.repository.PartieRepository;
import iut.fr.projet1000km.repository.UtilisateurRepository;
import iut.fr.projet1000km.services.PartieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/partie")
@CrossOrigin
public class PartieControleur {

    private static final Logger LOGGER = Logger.getLogger("PartieControleur.class");

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

    @GetMapping(value = "/code/{code}")
    public ResponseEntity<Partie> getByCode(@PathVariable final String code) {
        return partieRepository.getPartieByCodePartie(code)
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
        partie.setCodePartie(PartieService.generateCodePartie(Constants.LONGUEUR_CODE_PARTIE));
        LOGGER.info(partie.toString());

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

    /**
     * METHODE TEST POUR VOIR SI LA JOINTURE SE FAIT BIEN ENTRE PARTIE ET JOUEURS
     * >>>>>>>> CA MARCHE
     * @param id
     * @return
     */
    @GetMapping(value = "/test/{id}")
    public Partie getPartieByJoueurId(@PathVariable Long id) {
        return partieRepository.findByJoueurId(id);
    }
}
