package iut.fr.projet1000km.services;

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

        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur modifier(Utilisateur utilisateur) {
        return utilisateurRepository.saveAndFlush(utilisateur);
    }

    public void supprimer(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public Optional<Utilisateur> connexion(String pseudo, String motDePasse) {
        return utilisateurRepository.findByPseudoAndMotDePasse(pseudo, motDePasse);
    }
}
