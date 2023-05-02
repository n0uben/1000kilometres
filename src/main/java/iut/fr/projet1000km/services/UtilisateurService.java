package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Partie;
import iut.fr.projet1000km.models.Utilisateur;
import iut.fr.projet1000km.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Optional<Utilisateur> connexion(String pseudo, String motDePasse) {
        return utilisateurRepository.findByPseudoAndMotDePasse(pseudo, motDePasse);
    }

}
