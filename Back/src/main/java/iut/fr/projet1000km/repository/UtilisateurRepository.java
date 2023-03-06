package iut.fr.projet1000km.repository;

import iut.fr.projet1000km.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> getUtilisateurByPseudo(String pseudo);
}
