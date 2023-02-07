package iut.fr.projet1000km.services;

import iut.fr.projet1000km.models.Utilisateur;
import iut.fr.projet1000km.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur creer(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateur> lire() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur modifier(Long id, Utilisateur utilisateur) {
        return utilisateurRepository.findById(id)
                .map(u -> {
                    u.setMotDePasse(utilisateur.getMotDePasse());
                    u.setNbPartiesJouees(utilisateur.getNbPartiesJouees());
                    u.setNbPartiesGagnees(utilisateur.getNbPartiesGagnees());
                    u.setPseudo(utilisateur.getPseudo());
                    return utilisateurRepository.save(u);
                }).orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    @Override
    public String supprimer(Long id) {
        utilisateurRepository.deleteById(id);
        return "Utilisateur supprimé";
    }
}
