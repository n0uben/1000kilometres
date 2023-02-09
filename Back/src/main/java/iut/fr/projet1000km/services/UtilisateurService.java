package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Carte;
import iut.fr.projet1000km.models.Utilisateur;
import iut.fr.projet1000km.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Optional<Utilisateur> getOne(Long id) {
        return utilisateurRepository.findById(id);
    }

    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur creer(Utilisateur utilisateur) {

        Utilisateur utilisateurACreer = new Utilisateur(utilisateur.getIdUtilisateur(), utilisateur.getPseudo(), utilisateur.getMotDePasse(), utilisateur.getNbPartiesJouees(), utilisateur.getNbPartiesGagnees());

        return utilisateurRepository.save(utilisateurACreer);
    }


    public Utilisateur modifier(Utilisateur utilisateur) {
        Utilisateur utilisateurAModifier = new Utilisateur(utilisateur.getIdUtilisateur(), utilisateur.getPseudo(), utilisateur.getMotDePasse(), utilisateur.getNbPartiesJouees(), utilisateur.getNbPartiesGagnees());
        return utilisateurRepository.saveAndFlush(utilisateurAModifier);
    }

    public void supprimer(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
