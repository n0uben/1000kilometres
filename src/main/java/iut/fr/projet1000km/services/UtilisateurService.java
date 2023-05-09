package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    private final iut.fr.projet1000km.repository.UtilisateurRepository utilisateurRepository;

    public UtilisateurService(iut.fr.projet1000km.repository.UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Optional<Utilisateur> connexion(String pseudo, String motDePasse) {
        return utilisateurRepository.findByPseudoAndMotDePasse(pseudo, motDePasse);
    }

}
