package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Joueur;
import iut.fr.projet1000km.repository.JoueurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JoueurService {
    private final JoueurRepository joueurRepository;

    public JoueurService(JoueurRepository joueurRepository) {
        this.joueurRepository = joueurRepository;
    }

    public Optional<Joueur> getOne(Long id) {
        return joueurRepository.findById(id);
    }

    public List<Joueur> getAll() {
        return joueurRepository.findAll();
    }

    public Joueur creer(Joueur joueur) {

        Joueur joueurACreer = new Joueur(joueur.getKmParcouru(),joueur.isPeutAvancer());

        return joueurRepository.save(joueurACreer);
    }


    public Joueur modifier(Joueur joueur) {
        Joueur joueurAModifier = new Joueur(joueur.getIdJoueur(),joueur.getKmParcouru(),joueur.isPeutAvancer());
        return joueurRepository.saveAndFlush(joueurAModifier);
    }

    public void supprimer(Long id) {
        joueurRepository.deleteById(id);
    }
}
